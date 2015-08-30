@echo off
echo ============ Acooly Coder ==============
cd %~dp0

set /p workspace  =Project root:
set /p package    =Code package:
set /p pagePath   =View relative path:
set /p ignorPrefix=Entity Ignore prefix:

:settingTables
set /p tables=Tables or views names(split:','):
if "%tables%" == "" (echo [INFO] please input table or view names. &goto settingTables)

echo [info] Your setting arguments:
echo Project root£º%workspace% 
echo Code package£º%package%  
echo View relative path£º%pagePath%
echo Table to entity ignore profix£º%ignorPrefix%
echo Tables or views names£º%tables%

echo [info] start generate code...
call java -classpath . -Djava.ext.dirs=libs com.acooly.module.coder.Generate -i %ignorPrefix% -v %pagePath% -p %package% -w %workspace% -t %tables%
pause