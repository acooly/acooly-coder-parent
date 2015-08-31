@echo off
cd %~dp0
rem start javaw -classpath .;acooly-module-coder-1.1.0.jar -Djava.ext.dirs=libs com.acooly.module.coder.ui.Generator
java -classpath .;acooly-module-coder-1.1.0.jar -Djava.ext.dirs=libs com.acooly.module.coder.ui.Generator
