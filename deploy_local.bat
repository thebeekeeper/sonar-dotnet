
set OUT_DIR=F:\Sonar\sonar-3.5.1\extensions\plugins
copy /Y sonar\dotnet\sonar-dotnet-vstest-plugin\target\sonar-dotnet-vstest-plugin-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y sonar\dotnet\sonar-dotnet-fxcop-plugin\target\sonar-dotnet-fxcop-plugin-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y sonar\dotnet\sonar-dotnet-gendarme-plugin\target\sonar-dotnet-gendarme-plugin-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y sonar\dotnet\sonar-dotnet-ndeps-plugin\target\sonar-dotnet-ndeps-plugin-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y sonar\dotnet\sonar-dotnet-plugin\target\sonar-dotnet-plugin-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y sonar\csharp\sonar-csharp-plugin\target\sonar-csharp-plugin-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y sonar\csharp\sonar-csharp-squid\target\sonar-csharp-squid-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y sonar\csharp\sonar-csharp-stylecop-plugin\target\sonar-csharp-stylecop-plugin-2.2-SNAPSHOT.jar %OUT_DIR%

copy /Y tools\vstest-runner\target\vstest-runner-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y tools\fxcop-runner\target\fxcop-runner-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y tools\gendarme-runner\target\gendarme-runner-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y tools\ndeps-runner\target\ndeps-runner-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y tools\stylecop-runner\target\stylecop-runner-2.2-SNAPSHOT.jar %OUT_DIR%
copy /Y tools\stester-runner\target\stester-runner-2.2-SNAPSHOT.jar %OUT_DIR%
