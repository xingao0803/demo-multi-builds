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

* Modify the Artifactory server and repository in conan_ci_conf.yml
  ```
  artifactory:
   name: demo-arti
   repo: demo-conan-local
   ```
   
* Create a "SimpleBuild" pipeline in Jenkins with the contents of ``single_build.groovy``
* Create a "MultiBuild" pipeline in Jenkins with contents of ``multi_build.groovy``
