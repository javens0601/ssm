

## minio存储在实践中的探索

[TOC]

### 部署所需要的步骤

```shell
./mc alias set source http://10.1.3.232:9000 UqdHCnYlQOj1234ru7ZI3sip iumAB4n1234uu2hVNxidahDvHVaMcG \
&& ./mc alias set dest http://10.1.25.30:18444 UqdHCnYlQOj1234ru7ZI3sip iumAB4n1234uu2hVNxidahDvHVaMcG  \
&& ./mc version enable source/bbizmateba \
&& ./mc version enable source/bbizmate-previewca \
&& ./mc version enable dest/bbizmateba \
&& ./mc version enable dest/bbizmate-previewca \
&& ./mc admin policy add source repladmin-policy policy-source.json \
&& ./mc admin policy set source/ repladmin-policy user=bizmate \
&& ./mc admin policy add dest replpolicy policy-dest.json \
&& ./mc admin policy set dest/ replpolicy user=bizmate

./mc admin bucket remote add source/bbizmateba http://UqdHCnYlQOj1234ru7ZI3sip:iumAB4n1234uu2hVNxidahDvHVaMcG@10.1.25.30:18444/bbizmateba --service replication --bandwidth 1G
#下面这一句是一个模板命令，上一步生成的arn会替换到xxx...中
./mc replicate add source/bbizmateba --priority 1 --remote-bucket bbizmateba --arn xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx --replicate delete-marker,delete
./mc replicate add source/bbizmateba --priority 1 --remote-bucket bbizmateba --arn arn:minio:replication::35005ad5-fee2-411c-a637-8dbc987c6346:bbizmateba --replicate delete-marker,delete


./mc admin bucket remote add source/bbizmate-previewca http://UqdHCnYlQOj1234ru7ZI3sip:iumAB4n1234uu2hVNxidahDvHVaMcG@10.1.25.30:18444/bbizmate-previewca --service replication --bandwidth 1G
#下面这一句是一个模板命令，上一步生成的arn会替换到xxx...中
./mc replicate add source/bbizmate-previewca --priority 1 --remote-bucket bbizmate-previewca --arn xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx --replicate delete-marker,delete
./mc replicate add source/bbizmate-previewca --priority 1 --remote-bucket bbizmate-previewca --arn arn:minio:replication::31709bae-94ff-4b69-ba93-1abceb90f38d:bbizmate-previewca --replicate delete-marker,delete

```

### 在minio双向同步过程中，部署结构的演化：

<img src="./resources/minio1.png" style="zoom:200%;" />

<img src="./resources/minio2.png" style="zoom:150%;" />

![](./resources/minio3.png)