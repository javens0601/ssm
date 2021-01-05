#!/bin/bash
#date:2021/1/4
#author:Javen
#description:通过指定NEXUS的Group下载地址下载指定Group上面的全部jar

#浏览器上看到的html下载页面的地址
NEXUS_GROUP_BROWSE=http://nexus:8081/service/rest/repository/browse/your-group/
#工程里面配置的私服下载地址，这个主要是为了计算出字符串长度，后面创建目录的时候剪切URL前缀用
NEXUS_GROUP_DOWNLOAD=http://nexus:8081/repository/your-group/

#这里加1是为了在后面创建目录的时候，剪切掉目录前面的“/”，不然mkdir的时候会认为是根目录，没有权限创建文件夹
LENGTH=`expr ${#NEXUS_GROUP_DOWNLOAD} + 1`

#echo $LENGTH;
#exit;

#下载jar
function downloadJar() {
	#输入jar的url
	#curl -sO $1
	#如果需要密码的话 curl -u username:passwd -sO $1
	printLog "下载jar 方法: "$1
	mkdirJarDir $1
	curl -sO $1
	cd - >&1 >/dev/null
}

#判断是否最后一级目录,如果包含jar文件就认为是最后一级目录
function isLastDir(){
	printLog "判断是否最后一级目录 $1"
	local boolLastDir=`echo $1 | egrep 'jar$|jar.sha1|pom|pom.sha1'`
	if [ -z $boolLastDir ]; then
		printLog "不是最后一级目录";
		#sleep 2
		return 10;
	else
		printLog "是最后一级目录";
		#sleep 2
		return 0;
	fi
}

#日志打印
function printLog() {
	echo $1 | tee -a javen.log
}


#在执行下载方法的时候为jar包创建文件存储路径
function mkdirJarDir() {
	printLog "为jar包创建合适的存储路径 $1"
	jarDir=`dirname $1|cut -b$LENGTH-200`;
	printLog $jarDir
	mkdir -p $jarDir;
	cd $jarDir
}

#递归进行下载
function downList() {
	for var in `curl -s $1 |grep '<a href'|grep -v  'Parent'| sed 's#<a \([^>]*\)>#--SYN--\1--FIN--#g; s/<//g; s/>//g' | sed 's/--SYN--/</g; s/--FIN--[^<]*</></g; s/[^<]*</</; s/--FIN--.*/>/;' | sed "s#<[^>]*href=\([^a-zA-Z>]*[^ >]*\)[^>]*># @\1@#g; s/<[^>]*>//g; s/'//g; s/@/ /g;s/\"/ /g;"`
	do
	#getSubDir $i
	#echo $1;echo $var
		isLastDir $1$var
		retvar=$?
		if [ $retvar -gt 0 ];then
			printLog "继续下一级目录";
			#sleep 1;
			downList "$1$var"
			printLog "==================下一个根目录开始查找======================";echo
		else
			downloadJar $var
			#sleep 1
		fi
	done
}

rm -rf javen.log 2>&1 >/dev/null
downList $NEXUS_GROUP_BROWSE



