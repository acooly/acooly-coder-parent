@echo off
title Package Project
echo [INFO] Package module without test

cd %~dp0
rem if not exist pom.xml in current directory , then 'cd' parent directory
if not exist pom.xml cd..
set module_home=%cd%
echo [INFO] The module location: %module_home%
echo.

call mvn clean package -Pdeploy -Dmaven.test.skip=true

echo.
echo [INFO] Package finished
pause