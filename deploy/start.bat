@echo off
echo ============ Acooly Coder ==============
cd %~dp0
echo CLIģʽ���е����ò�����application.properties�����ļ�����Ϊ׼
:settingTables
set /p tables=Tables or views names(split:','):
if "%tables%" == "" (echo [INFO] please input table or view names. &goto settingTables)

echo [info] Your setting arguments:
echo Tables or views names��%tables%

echo [info] start generate code...
call java -classpath .;acooly-module-coder-1.1.0.jar -Djava.ext.dirs=libs com.acooly.module.coder.Generate -t %tables%
pause