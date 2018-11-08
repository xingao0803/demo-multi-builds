# demo-multi-builds

- Install in Jenkins the "Pipeline Utility Steps": plugin, which is necessary to parse Yaml

- Upload all recipes to Artifactory repository at first
```
$ git clone <this repo> && cd demo-multi-builds
$ conan remote add arti-pro <your artifactory url>
$ python upload_recipes.py # This assumes your artifactory remote is called "arti-pro"
```

- Modify the Artifactory server and repository in conan_ci_conf.yml
  ```
  artifactory:
   name: demo-arti
   repo: demo-conan-local
   ```
   
- Create a "SimpleBuild" pipeline in Jenkins with the contents of ``single_build.groovy``
- Create a "MultiBuild" pipeline in Jenkins with contents of ``multi_build.groovy``


