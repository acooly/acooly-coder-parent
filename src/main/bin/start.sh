#!/bin/bash
echo acooly coder 自动代码生成工具

if [ ! -f 'application.properties' ]; then
	echo "配置文件application.properties不存在，请先根据文档说明，在根目录建立配置文件，并配置数据库等基础配置"
	exit
fi

echo ==============================
read -p "请输入需要生成的表名(多个表使用空格分隔):" -t 30 tables
if [ "$tables" == "" ]; then
	java -Djava.ext.dirs=lib:../ com.acooly.module.coder.Generator
else
	java -Djava.ext.dirs=lib:../ com.acooly.module.coder.Generator -t $tables
fi
