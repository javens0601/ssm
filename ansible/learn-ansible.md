## ansible学习笔记

#### ansible 介绍

#### ansible 特点

#### ansible 架构原理

#### ansible 安装

> yum 安装
>
> - 配置epel源
>
> - 执行 `yum install -y ansible`
>
>   ```shell
>   cd /etc/yum.repos.d/
>   for i in $(ls ./);do echo $i; mv $i $i.bak.2021.01.11 ;done
>   wget -O /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-7.repo
>   wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
>   yum clean all
>   yum makecache
>   yum -y install ansible
>   ansible --version
>   ```

#### ansible 配置文件

> /etc/ansible/ansible.cfg
>
> - host_key_checking	ssh检查
> - log_path		日志记录
>
> /etc/ansible/hosts

#### ansible 基本命令

* ansible 

  * ping 模块

    - `ansible servername -m ping -u username -k`

  * command 模块

    * `ansible 192.168.222.131 -m command -a 'ls /'`
    * `ansible  192.168.222.131 -a 'ls /'`     默认为command 模块，可以忽略。

  * shell 模块

    * `ansible 192.168.222.131 -m shell -a 'ls /'`

    > 有很多情况下，command 模块不支持了，就需要使用shell模块，如下面的情况：
    >
    > <u>___下面的输出是将单引号里面的内容原样输出了___</u>
    >
    > ```shell
    > ansible 192.168* -a 'echo 123 | grep 3'
    > 输出：
    > 192.168.222.131 | CHANGED | rc=0 >>
    > 123 | grep 3
    > ```
    >
    > <u>___下面是使用shell模块的效果___</u>
    >
    > ```shell
    > ansible 192.168* -m  shell -a 'echo 123 | grep 3'
    > 输出：
    > 192.168.222.131 | CHANGED | rc=0 >>
    > 123
    > ```

  * script 模块

    - `ansible  192.168.* -m script -a 'host.sh args'`

* ansible-doc

#### ansible命令执行过程

``` sequence
Title : Ansible命令执行过程

ansible -> ansible : 加载自己的配置文件\n（/etc/ansible/ansible.cfg）
#Note right of ansible :/etc/ansible/ansible.cfg
ansible -> ansible : 加载自己的模块文件\n(如 ping、 command)
ansible -> ansible : 生成临时py文件\n(~/.ansible/tmp/)
ansible -> remote server : 将py传输至远程受控机(~/.ansible/tmp)
remote server -> remote server : 给文件增加执行权限,并执行 (+x)
remote server --> ansible :返回执行结果
remote server -> remote server : 删除临时文件


```



#### ansible 远程调试

> ansible默认在执行完modules，会自动清理在远程主机上的临时文件。
>  使用 ___`ANSIBLE_KEEP_REMOTE_FILES=1`___ 环境变量 ，可以保留ansible在远程主机的执行文件，从而在远程主机上调试模块。

#### 其他系统设置

> ##### 查看SELinux状态：
>
> 1、/usr/sbin/sestatus -v      #如果SELinux status参数为enabled即为开启状态
>
> `SELinux status:                 enabled`
>
> 2、getenforce                 #也可以用这个命令检查
>
> ---
>
> ##### 关闭SELinux：
>
> 1、临时关闭（不用重启机器）：
>
> setenforce 0                  #设置SELinux 成为permissive模式
>
> setenforce 1				 #setenforce 1 设置SELinux 成为enforcing模式 
>
> 2、修改配置文件需要重启机器：
>
> 修改/etc/selinux/config 文件
>
> 将SELINUX=enforcing改为SELINUX=disabled
>
> 重启机器即可
>
> ---
>
> ##### 建立互信
>
> ssh-keygen
>
> ssh-copy-id 10.1.20.114     



