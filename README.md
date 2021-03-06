# demo-multi-builds

* Install in Jenkins the "Pipeline Utility Steps": plugin, which is necessary to parse Yaml

* Generate and upload all recipes and packages to Artifactory repository at first

    + Run it locally
    ```
    $ git clone <this repo> && cd demo-multi-builds
    $ conan remote add <remote_server> <your artifactory url>   
    $ conan user -p <api_key> -r <remote_server> admin
    $ python upload_packages.py <remote_server> 
    ```  
    + Or Run it in Jenkins
    ```
    Jenkins_Init:  Jenkins pipeline to do this work. Modify all parameters to your settings
    ```

* JenkinsFile_Multi_Builds:   Jenkins pipeline to launch multi builds based on build order   

* JenkinsFile_Single_Build:   Jenkins pipeline to package and upload single build   

* conan_ci_conf.yml:   Config file for builds   

* recipes/:  Folder for all package recipes

* profiles/:  Profiles for cross build

* build_order.txt:  How to generate build order   

* dependencies.html:  Dependencies tree, generated by   
    ```
    cd recipes/PROJECT
    conan info . -g dependencies.html
    ```





