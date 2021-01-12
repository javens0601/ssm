## ansibleѧϰ�ʼ�

#### ansible ����

#### ansible �ص�

#### ansible �ܹ�ԭ��

#### ansible ��װ

> yum ��װ
>
> - ����epelԴ
>
> - ִ�� `yum install -y ansible`
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

#### ansible �����ļ�

> /etc/ansible/ansible.cfg
>
> - host_key_checking	ssh���
> - log_path		��־��¼
>
> /etc/ansible/hosts

#### ansible ��������

* ansible 

  * ping ģ��

    - `ansible servername -m ping -u username -k`

  * command ģ��

    * `ansible 192.168.222.131 -m command -a 'ls /'`
    * `ansible  192.168.222.131 -a 'ls /'`     Ĭ��Ϊcommand ģ�飬���Ժ��ԡ�

  * shell ģ��

    * `ansible 192.168.222.131 -m shell -a 'ls /'`

    > �кܶ�����£�command ģ�鲻֧���ˣ�����Ҫʹ��shellģ�飬������������
    >
    > <u>___���������ǽ����������������ԭ�������___</u>
    >
    > ```shell
    > ansible 192.168* -a 'echo 123 | grep 3'
    > �����
    > 192.168.222.131 | CHANGED | rc=0 >>
    > 123 | grep 3
    > ```
    >
    > <u>___������ʹ��shellģ���Ч��___</u>
    >
    > ```shell
    > ansible 192.168* -m  shell -a 'echo 123 | grep 3'
    > �����
    > 192.168.222.131 | CHANGED | rc=0 >>
    > 123
    > ```

  * script ģ��

    - `ansible  192.168.* -m script -a 'host.sh args'`

* ansible-doc

#### ansible����ִ�й���

``` sequence
Title : Ansible����ִ�й���

ansible -> ansible : �����Լ��������ļ�\n��/etc/ansible/ansible.cfg��
#Note right of ansible :/etc/ansible/ansible.cfg
ansible -> ansible : �����Լ���ģ���ļ�\n(�� ping�� command)
ansible -> ansible : ������ʱpy�ļ�\n(~/.ansible/tmp/)
ansible -> remote server : ��py������Զ���ܿػ�(~/.ansible/tmp)
remote server -> remote server : ���ļ�����ִ��Ȩ��,��ִ�� (+x)
remote server --> ansible :����ִ�н��
remote server -> remote server : ɾ����ʱ�ļ�


```



#### ansible Զ�̵���

> ansibleĬ����ִ����modules�����Զ�������Զ�������ϵ���ʱ�ļ���
>  ʹ�� ___`ANSIBLE_KEEP_REMOTE_FILES=1`___ �������� �����Ա���ansible��Զ��������ִ���ļ����Ӷ���Զ�������ϵ���ģ�顣

#### ����ϵͳ����

> ##### �鿴SELinux״̬��
>
> 1��/usr/sbin/sestatus -v      #���SELinux status����Ϊenabled��Ϊ����״̬
>
> `SELinux status:                 enabled`
>
> 2��getenforce                 #Ҳ���������������
>
> ---
>
> ##### �ر�SELinux��
>
> 1����ʱ�رգ�����������������
>
> setenforce 0                  #����SELinux ��Ϊpermissiveģʽ
>
> setenforce 1				 #setenforce 1 ����SELinux ��Ϊenforcingģʽ 
>
> 2���޸������ļ���Ҫ����������
>
> �޸�/etc/selinux/config �ļ�
>
> ��SELINUX=enforcing��ΪSELINUX=disabled
>
> ������������
>
> ---
>
> ##### ��������
>
> ssh-keygen
>
> ssh-copy-id 10.1.20.114     



