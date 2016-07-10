#!/bin/bash
echo acooly-coder CLI工具

if [ ! -f 'application.properties' ]; then
	echo "配置文件application.properties不存在，请先根据文档说明，在根目录建立配置文件，并配置数据库等基础配置"
	exit
fi
java -Djava.ext.dirs=lib:../ com.acooly.module.coder.Generator $*
