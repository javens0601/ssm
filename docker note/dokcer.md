## docker 学习笔记

#### 一、安装docker

```shell
sudo yum update
sudo yum install -y  yum-utils  device-mapper-persistent-data lvm2
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
sudo yum install -y  docker-ce
docker -v

```

#### 二、设置ustc的镜像



```shell
vi /etc/docker/daemon/json
{
"register-mirrors":["https://docker.mirrors.ustc.edu.cn"]
}
```

