#!/bin/bash

cd FAT/adapter/src/SSP-AdapterImpl-CZBank/src/main/java/com/sisun/yqtrequest/adapter/client/impl/czwb/

NAME_STRFIX="ClientImpl.java"

STR="CB200005"


for i in `echo $STR|sed 's#,#\n#g'`
do
	#生成Client
	#echo " create $i$NAME_STRFIX interface start ...";
	#touch $i$NAME_STRFIX;
	#echo "package com.sisun.adapter.client.czwb;" >  $i$NAME_STRFIX
	#echo "import com.alibaba.fastjson.JSONObject;" >>  $i$NAME_STRFIX
	#echo "public interface $i"Client" {" >> $i$NAME_STRFIX
	#echo " JSONObject runService(JSONObject jsonObject);"  >> $i$NAME_STRFIX
	#echo "}" >> $i$NAME_STRFIX
	
	#生成ClientImpl
	echo " create $i$NAME_STRFIX interface start ...";
	touch $i$NAME_STRFIX;
	echo "package com.sisun.yqtrequest.adapter.client.impl.czwb;" >  $i$NAME_STRFIX
	echo >>  $i$NAME_STRFIX
	echo "import com.alibaba.fastjson.JSONObject;" >>  $i$NAME_STRFIX
	echo "import com.sisun.adapter.client.czwb.$i"Client";" >>  $i$NAME_STRFIX
	echo "import com.sisun.yqtrequest.adapter.client.impl.yqtRequest.DefaultBaseClientImpl;" >>  $i$NAME_STRFIX
	echo "import org.springframework.stereotype.Component;" >>  $i$NAME_STRFIX
	echo  >>  $i$NAME_STRFIX
	echo "@Component" >>  $i$NAME_STRFIX
	echo "public class $i"ClientImpl" extends DefaultBaseClientImpl implements $i"Client" {" >>  $i$NAME_STRFIX
	echo "    @Override" >>  $i$NAME_STRFIX
	echo "    public JSONObject runService(JSONObject params) {" >>  $i$NAME_STRFIX
	echo "        JSONObject retJson = super.runService(params);" >>  $i$NAME_STRFIX
	echo "        return retJson;" >>  $i$NAME_STRFIX
	echo "    }" >>  $i$NAME_STRFIX
	echo "}" >>  $i$NAME_STRFIX
	
	#生成xml
	#echo " create $i$NAME_STRFIX interface start ...";
	#touch $i$NAME_STRFIX;
	#echo '<?xml version="1.0" encoding="UTF-8"?>' >  $i$NAME_STRFIX
	#echo "<service id=\"$i\">" >>  $i$NAME_STRFIX
	#echo '	<config>' >>  $i$NAME_STRFIX
	#echo '		<item name="path" value="http://192.129.136.100:8100/czcb_zxbank_gateway/"></item>' >>  $i$NAME_STRFIX
	#echo "		<item name=\"serviceId\" value=\"$i\"></item>" >>  $i$NAME_STRFIX
	#echo '		<item name="protocol" value="http"></item>' >>  $i$NAME_STRFIX
	#echo '	</config>' >>  $i$NAME_STRFIX
	#echo '	<request>' >>  $i$NAME_STRFIX
	#	REQUEST=`eval echo '$'"$i"request`
	#	for j in `echo $REQUEST|sed 's#,#\n#g'`
	#	do
	#		echo "		<item name=\"$j\" mappedName=\"$j\" type=\"string\" required=\"Y\"></item>" >>  $i$NAME_STRFIX
	#	done;
	#echo '	</request>' >>  $i$NAME_STRFIX
	#echo '	<response>' >>  $i$NAME_STRFIX
	#	RESPONE=`eval echo '$'"$i"response`
	#for k in `echo $RESPONE|sed 's#,#\n#g'`
	#	do
	#		echo "		<item name=\"$k\" mappedName=\"$k\" type=\"string\" required=\"N\"></item>" >>  $i$NAME_STRFIX
	#	done;
	#echo '	</response>' >>  $i$NAME_STRFIX
	#echo '</service>' >>  $i$NAME_STRFIX
done
