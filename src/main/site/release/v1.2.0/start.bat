@echo off
echo ============ Acooly Coder �Զ��������ɹ��� ==============
cd %~dp0

if exist "application.properties" (
	echo ���ò�����application.properties�����ļ�����Ϊ׼ 
) else (
	echo ��ǰĿ¼û���ҵ�application.properties���������ø��ļ�
	goto theend
)

:settingTables
set /p tables=Tables or views names(split:�ո�):
if "%tables%" == "" (echo [INFO] �������������ͼ�������ʹ�ÿո�ָ�. &goto settingTables)

echo [info] Your setting arguments:
echo Tables or views names��%tables%

echo [info] start generate code...
call java -classpath .;acooly-coder-allinone.jar com.acooly.module.coder.Generator -t %tables%
pause

:theend
 