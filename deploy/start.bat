@echo off
echo ============ Acooly Coder �Զ��������ɹ��� ==============
cd %~dp0
echo ���ò�����application.properties�����ļ�����Ϊ׼
:settingTables
set /p tables=Tables or views names(split:','):
if "%tables%" == "" (echo [INFO] �������������ͼ�������ʹ�ÿո�ָ�. &goto settingTables)

echo [info] Your setting arguments:
echo Tables or views names��%tables%

echo [info] start generate code...
call java -classpath .;acooly-module-coder-allinone.jar com.acooly.module.coder.Generate -t %tables%
pause