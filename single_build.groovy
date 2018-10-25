properties([parameters([string(description: 'Build label', name: 'build_label', defaultValue: 'Unamed'),
                        string(description: 'Channel', name: 'channel', defaultValue: 'stable'),
                        string(description: 'Name/Version', name: 'name_version', defaultValue: 'LIB_A/1.0'),
                        string(description: 'Profile', name: 'profile', defaultValue: './profiles/64bits'),
                        string(description: 'Config repository branch', name: 'conf_repo_branch', defaultValue: 'master'),
                        string(description: 'Config repository url', name: 'conf_repo_url', defaultValue: 'https://github.com/xingao0803/demo-multi-builds.git'),
                       ])])

node {
    
    currentBuild.displayName = params.build_label
    def buildInfo
    def data
    def conf_repo_dir
    def client
    def serverName
  
    // User/Pass for Artifactory server
    def user_name = 'admin'
    def user_apikey = 'APBG9f7okturGUX3EH6LhxhbBmR'

    withEnv(['PATH+JENKINSHOME=/usr/local/bin']) {
            def server
        stage("Configure/Get repositories"){

            dir("_conf_repo"){
                git branch: params.conf_repo_branch, url: params.conf_repo_url
                data = readYaml file: "conan_ci_conf.yml"
                conf_repo_dir = pwd()
            }
            server = Artifactory.server data.artifactory.name
            client = Artifactory.newConanClient()
                 
            serverName = client.remote.add server: server, repo: data.artifactory.repo

            // Create a local build-info instance:
            buildInfo = Artifactory.newBuildInfo()      
            
            // Login the new conan remote server
            def command = "user -r ${serverName} -p ${user_apikey} ${user_name}"
            client.run(command: command.toString(), buildInfo: buildInfo)     
              
            dir("_lib_repo"){
                git branch: data.repos[params.name_version].branch, url: data.repos[params.name_version].url
            }
        }
        stage("Build packages"){
            // For each profile
            dir("_lib_repo"){
                dir(data.repos[params.name_version].dir){
                    client.run(command: "create . jfrog/stable -pr \"" + conf_repo_dir + "/" + params.profile + "\"", buildInfo: buildInfo)
                }
            }
        }
        
        stage("Upload Artifactory"){
            String command = "upload * -r ${serverName} --all -c"
            client.run(command: command.toString(), buildInfo: buildInfo)
            buildInfo.env.collect()
            server.publishBuildInfo buildInfo
        }
        
        stage("Test"){
               
        }
        
        stage("Promote"){
            def promotionConfig = [
            // Mandatory parameters
            'buildName'          : buildInfo.name,
            'buildNumber'        : buildInfo.number,
            'targetRepo'         : 'demo-conan-prod',
    
            // Optional parameters
            'comment'            : 'ready for prod',
            'sourceRepo'         : 'demo-conan-local',
            'status'             : 'Released',
            'includeDependencies': true,
            'copy'               : false
        ]

        // Promote build
        server.promote promotionConfig   
        
        }
        
    }
}
