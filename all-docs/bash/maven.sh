#!/bin/bash
#Desc 项目打包脚本
#Auth zongf
#Date 2020-05-19

# 指定maven 配置
mvnHome="/opt/maven/apache-maven-3.6.2"
mvnConfig="/opt/maven/apache-maven-3.6.2/conf/settings-autohome.xml"

# 指定要模块儿列表
modules=(
  "parent-tools"
  "log-tools"
  "common-tools"
  "http-tools"
  "encrypt-tools"
  "spring-tools"
)

######################################################   functions  ######################################################

  # 输出日志
function printLog() {
  echo
  echo "********************************************************************************************************************"
  echo "*                                       $1                                                                          "
  echo "********************************************************************************************************************"
  echo
}

#####################################################   main  ######################################################

# 进入项目目录
cd ../..

for moduleName in "${modules[@]}"
do
  printLog $moduleName

  # 进入目录 > 执行mvn 命令 > 返回上级目录
  cd $moduleName
  $mvnHome/bin/mvn -s $mvnConfig -DskipTests $*
  cd ..
done







