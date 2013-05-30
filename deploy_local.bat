
set OUT_DIR=F:\Sonar\sonar-3.5.1\extensions\plugins
copy /Y sonar\dotnet\sonar-dotnet-vstest-plugin\target\sonar-dotnet-vstest-plugin-2.2-SNAPSHOT.jar %OUT_DIR%

copy /Y tools\vstest-runner\target\vstest-runner-2.2-SNAPSHOT.jar %OUT_DIR%
