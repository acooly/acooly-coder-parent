#!/bin/bash
echo acooly coder cli
echo application.properties配置参数:
cat application.properties 
echo ==============================
read -p "请输入需要生成的表名(多个表使用空格分隔):" -t 30 tables
if [ "$tables" == "" ]; then
	java -classpath .:acooly-module-coder-1.1.0.jar -Djava.ext.dirs=libs com.acooly.module.coder.Generate
else
	java -classpath .:acooly-module-coder-1.1.0.jar -Djava.ext.dirs=libs com.acooly.module.coder.Generate -t $tables
fi