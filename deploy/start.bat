@echo off
echo ============ Acooly Coder ==============
cd %~dp0
echo CLI模式所有的配置参数以application.properties配置文件配置为准
:settingTables
set /p tables=Tables or views names(split:','):
if "%tables%" == "" (echo [INFO] please input table or view names. &goto settingTables)

echo [info] Your setting arguments:
echo Tables or views names：%tables%

echo [info] start generate code...
call java -classpath .;acooly-module-coder-1.1.0.jar -Djava.ext.dirs=libs com.acooly.module.coder.Generate -t %tables%
pause