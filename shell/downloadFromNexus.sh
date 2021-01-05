#!/bin/bash
#date:2021/1/4
#author:Javen
#description:ͨ��ָ��NEXUS��Group���ص�ַ����ָ��Group�����ȫ��jar

#������Ͽ�����html����ҳ��ĵ�ַ
NEXUS_GROUP_BROWSE=http://nexus:8081/service/rest/repository/browse/your-group/
#�����������õ�˽�����ص�ַ�������Ҫ��Ϊ�˼�����ַ������ȣ����洴��Ŀ¼��ʱ�����URLǰ׺��
NEXUS_GROUP_DOWNLOAD=http://nexus:8081/repository/your-group/

#�����1��Ϊ���ں��洴��Ŀ¼��ʱ�򣬼��е�Ŀ¼ǰ��ġ�/������Ȼmkdir��ʱ�����Ϊ�Ǹ�Ŀ¼��û��Ȩ�޴����ļ���
LENGTH=`expr ${#NEXUS_GROUP_DOWNLOAD} + 1`

#echo $LENGTH;
#exit;

#����jar
function downloadJar() {
	#����jar��url
	#curl -sO $1
	#�����Ҫ����Ļ� curl -u username:passwd -sO $1
	printLog "����jar ����: "$1
	mkdirJarDir $1
	curl -sO $1
	cd - >&1 >/dev/null
}

#�ж��Ƿ����һ��Ŀ¼,�������jar�ļ�����Ϊ�����һ��Ŀ¼
function isLastDir(){
	printLog "�ж��Ƿ����һ��Ŀ¼ $1"
	local boolLastDir=`echo $1 | egrep 'jar$|jar.sha1|pom|pom.sha1'`
	if [ -z $boolLastDir ]; then
		printLog "�������һ��Ŀ¼";
		#sleep 2
		return 10;
	else
		printLog "�����һ��Ŀ¼";
		#sleep 2
		return 0;
	fi
}

#��־��ӡ
function printLog() {
	echo $1 | tee -a javen.log
}


#��ִ�����ط�����ʱ��Ϊjar�������ļ��洢·��
function mkdirJarDir() {
	printLog "Ϊjar���������ʵĴ洢·�� $1"
	jarDir=`dirname $1|cut -b$LENGTH-200`;
	printLog $jarDir
	mkdir -p $jarDir;
	cd $jarDir
}

#�ݹ��������
function downList() {
	for var in `curl -s $1 |grep '<a href'|grep -v  'Parent'| sed 's#<a \([^>]*\)>#--SYN--\1--FIN--#g; s/<//g; s/>//g' | sed 's/--SYN--/</g; s/--FIN--[^<]*</></g; s/[^<]*</</; s/--FIN--.*/>/;' | sed "s#<[^>]*href=\([^a-zA-Z>]*[^ >]*\)[^>]*># @\1@#g; s/<[^>]*>//g; s/'//g; s/@/ /g;s/\"/ /g;"`
	do
	#getSubDir $i
	#echo $1;echo $var
		isLastDir $1$var
		retvar=$?
		if [ $retvar -gt 0 ];then
			printLog "������һ��Ŀ¼";
			#sleep 1;
			downList "$1$var"
			printLog "==================��һ����Ŀ¼��ʼ����======================";echo
		else
			downloadJar $var
			#sleep 1
		fi
	done
}

rm -rf javen.log 2>&1 >/dev/null
downList $NEXUS_GROUP_BROWSE



