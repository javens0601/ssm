## ansible学习笔记



#### ansible 介绍

#### ansible 特点

#### ansible 架构原理

#### ansible 安装
- yum 安装
	- yum install -y ansible

#### ansible 基本命令
ansible 10.1.20.114 -m ping -u username -k

#### ansible 配置文件
/etc/ansible/ansible.cfg
host_key_checking	ssh检查
log_path		日志记录

#### 其他设置

- 查看SELinux状态：

1、/usr/sbin/sestatus -v      ##如果SELinux status参数为enabled即为开启状态

SELinux status:                 enabled

2、getenforce                 ##也可以用这个命令检查

关闭SELinux：

1、临时关闭（不用重启机器）：

setenforce 0                  ##设置SELinux 成为permissive模式

                              ##setenforce 1 设置SELinux 成为enforcing模式

2、修改配置文件需要重启机器：

修改/etc/selinux/config 文件

将SELINUX=enforcing改为SELINUX=disabled

重启机器即可


- 建立互信

ssh-keygen

ssh-copy-id 10.1.20.114