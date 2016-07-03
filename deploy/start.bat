@echo off
echo ============ Acooly Coder 自动代码生成工具 ==============
cd %~dp0
echo 配置参数以application.properties配置文件配置为准
:settingTables
set /p tables=Tables or views names(split:','):
if "%tables%" == "" (echo [INFO] 请输入表名或视图名，多个使用空格分隔. &goto settingTables)

echo [info] Your setting arguments:
echo Tables or views names：%tables%

echo [info] start generate code...
call java -classpath .;acooly-module-coder-allinone.jar com.acooly.module.coder.Generate -t %tables%
pause