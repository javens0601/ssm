[TOC]

## ansibleѧϰ�ʼ�

### ansible ����

Ansible��һ�ֿ�Դ������ã����ù����Ӧ�ó����𹤾ߣ��ɽ������ṹ��Ϊ�������á��������������Unixϵͳ�����У����ҿ���������Unixϵͳ��Microsoft Windows���������Լ�������������������ϵͳ���á�

**�ص�:**

- �����������谲װAgent
- ����ɵ���ִ�У��ɱ�д�ɾ籾��֧�������жϡ�ѭ��
- u����SSH��Kerberos��LDAP��Э�鹤��
- ����python����������ά��

### ansible �ܹ�ԭ��

![arch](arch.png)



### ansible ��װ

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
>
> - Դ�밲װ
>
> - pip��װ

### ansible �����ļ�

> /etc/ansible/ansible.cfg      #�����ļ�
>
> - host_key_checking	ssh���
> - log_path		��־��¼
>
> /etc/ansible/hosts               #Ĭ�������嵥�������޸ģ���ִ��playbook��ʱ�����ͨ��  -i  ����ָ�������嵥
>
> - ����������
>
>   [webserver]
>
>   192.168.1.100
>
>   192.168.1.101
>
> - �������
>
>   [webserver]
>
>   192.168.1.100      ansible_port=80    ansible_user=javen
>
>   192.168.1.101      ansible_port=81
>
> - ֧��ģʽƥ��
>
>   ��ʹ�� �ű���ʱ��:  ansible __web*__ -m ping

### ansible ����

#### ansible ����

- ansible                     # ִ��ansible��������,ansible�����ַ�ʽΪ��ʱ����(Ansible Ad-Hoc)��

- ansible-doc             # �鿴ansible ģ��˵��

- ansible-galaxy        # ����https://galaxy.ansible.com/ ������Ӧ��roles

  ```shell
  #�г������Ѿ���װ�� galaxy
  ansible-galaxy list
  #��װgalaxy
  ansible-galaxy install andrewrothstein.etcd
  ansible-galaxy install geerlingguy.jenkins
  #ɾ��galaxy
  ansible-galaxy remove andrewrothstein.etcd
  
  ```

- ansible-pull           #����������Զ��

- ansible-playbook  #

- ansible-vault          #������ܽ���yml�ļ�

- ansibl-console        #ִ�н���ʽ����Ľ���

#### ansible ����ģ��

* ping ģ��

  - `ansible servername -m ping -u username -k`

* command ģ��

  * `ansible 192.168.* -m command -a 'ls /'`
  * `ansible  192.168.* -a 'ls /'`     Ĭ��Ϊcommand ģ�飬���Ժ��ԡ�

* shell ģ��

  * `ansible 192.168.* -m shell -a 'ls /'`

  > �кܶ�����£�command ģ�鲻֧���ˣ�����Ҫʹ��shellģ�飬������������
  >
  > <u>___���������ǽ����������������ԭ�������___</u>
  >
  > ```shell
  > ansible 192.168* -a 'echo 123 | grep 3'
  > �����
  > 192.168.xxx.xxx | CHANGED | rc=0 >>
  > 123 | grep 3
  > ```
  >
  > <u>___������ʹ��shellģ���Ч��___</u>
  >
  > ```shell
  > ansible 192.168* -m  shell -a 'echo 123 | grep 3'
  > �����
  > 192.168.xxx.xxx | CHANGED | rc=0 >>
  > 123
  > ```

* script ģ��

  - `ansible  192.168.* -m script -a 'host.sh args'`

* copy ģ��

  * �����ػ����ļ��������ܿػ�

    `ansible ubuntu -m copy -a 'src=/etc/ansible/shell dest=~/ansible/tmp/'`

* fetch ģ��

  * ���ܿػ����ļ����������ػ�

    `ansible ubuntu -m fetch -a 'src=~/.ansible/tmp/test/hello.txt dest=~/.ansible/'`

* file ģ��

* setup ģ��

* templates ģ��

#### ansible����ִ�й���

``` sequence
Title : Ansible����ִ�й���

ansible -> ansible : �����Լ��������ļ�\n��/etc/ansible/ansible.cfg��
#Note right of ansible :/etc/ansible/ansible.cfg
ansible -> ansible : �����Լ���ģ���ļ�\n(�� ping�� command)
ansible -> ansible : ������ʱpy�ļ�\n(~/.ansible/tmp/)
ansible -> remote server : ��py������Զ���ܿػ�(~/.ansible/tmp)
#Note right of remote server : ansible֧�������Ĳ���\n�����remote server�����Ƕ�̨����
Note over remote server : ansible֧�������Ĳ���\n�����remote server�����Ƕ�̨����
remote server -> remote server : ���ļ�����ִ��Ȩ��,��ִ�� (+x)
remote server --> ansible :����ִ�н��
remote server -> remote server : ɾ����ʱ�ļ�


```

#### ansible Զ�̵���

> - ansibleĬ����ִ����modules�����Զ�������Զ�������ϵ���ʱ�ļ���
>   ʹ�� ___`ANSIBLE_KEEP_REMOTE_FILES=1`___ �������� �����Ա���ansible��Զ��������ִ���ļ����Ӷ���Զ�������ϵ���ģ�顣
>
> - ʹ�� -v��-vv��-vvv ���Բ鿴ansibleִ�е���ϸ��־

### ansible playbook

> ##### playbook ����Ԫ��
> - hosts ִ�е�Զ�������б�
> - tasks ����
> - varniables ���ñ��������Զ��������playbook�е���
> - templates ģ�壬���滻ģ���ļ��еı�����ʵ��һЩ���߼����ļ�
> - handlers �� notify���ʹ�ã����ض���������������������ִ�У�����ִ��
> - tags ��ǩ��ָ��ĳ������ִ�У�����ѡ��playbook�еĲ��ִ��룬ansible�����ݵ��ԣ���˻��Զ�����û�б仯�Ĳ��֣�������ˣ���Щ����Ϊ�˲�����ȷʵû�з����仯��ʱ��ܳ�����ʱ�����ȷ��û�б仯���Ϳ���ͨ��tags������Щ����Ƭ��
>   `ansible-playbook -t tagsname useradd.yml`

### templates ģ��

> jinja2����

### role ����

> ����role��ͨ����[ansible-galaxy](https://galaxy.ansible.com/home)����վ�������������Լ���Ҫ��role��
>
> ```shell
> geerlingguy.jenkins   	#ÿһ���ļ��о���һ��role
> ������ defaults			#�趨Ĭ�ϱ���ʱʹ�ô�Ŀ¼�е�main.yml
> ��   ������ main.yml
> ������ handlers			#������һ��main.yml�������ļ�ͨ��include����
> ��   ������ main.yml
> ������ LICENSE
> ������ meta			#������һ��main.yml�������ļ�ͨ��include���������嵱ǰ��ɫ�������趨��������ϵ
> ��   ������ main.yml
> ������ molecule
> ��   ������ default
> ��       ������ converge.yml
> ��       ������ java-11.yml
> ��       ������ java-8.yml
> ��       ������ molecule.yml
> ��       ������ playbook-http-port.yml
> ��       ������ playbook-jenkins-version.yml
> ��       ������ playbook-plugins-with-home.yml
> ��       ������ playbook-prefix.yml
> ��       ������ requirements.yml
> ������ README.md
> ������ tasks			#������һ��main.yml�������ļ�ͨ��include����,����task�Ļ���Ԫ��
> ��   ������ main.yml
> ��   ������ plugins.yml
> ��   ������ settings.yml
> ��   ������ setup-Debian.yml
> ��   ������ setup-RedHat.yml
> ������ templates		#���ģ���ļ�
> ��   ������ basic-security.groovy.j2
> ��   ������ proxy.xml
> ������ tests
> ��   ������ test-plugins.yml
> ������ vars			#������һ��main.yml�������ļ�ͨ��include����,���������Ŀ¼
>  ������ Debian.yml
>  ������ RedHat.yml
> 
> ```
>

### ����ϵͳ����

#### seLinux ����

> ##### seLinux��
>
> ```text
>  SELinux(Security-Enhanced Linux) ���������Ұ�ȫ��NSA����ǿ�Ʒ��ʿ��Ƶ�ʵ�֣��� Linux��ʷ����ܳ����°�ȫ��ϵͳ��NSA����Linux�����İ����¿�����һ�ַ��ʿ�����ϵ�������ַ��ʿ�����ϵ�������£�����ֻ�ܷ�����Щ����������������Ҫ�ļ���SELinux Ĭ�ϰ�װ�� Fedora �� Red Hat Enterprise Linux �ϣ�Ҳ������Ϊ�������а������װ�װ�İ��õ���
> ```
>
> ---
>
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

#### ��������������

> ssh-keygen
>
> ssh-copy-id 10.1.xx.xxx     

### others

- jenkins������������

  ```shell
  $ wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
  $ vi /etc/yum.repos.d/jenkins.repo
  
  [jenkins]
  name=Jenkins-stable
  baseurl=https://mirrors.huaweicloud.com/jenkins/redhat-stable
  gpgcheck=1
  ```

- ��װredis
  - ansible-galaxy install davidwittman.redis
  - ��дplaybook
  - ִ��playbook ��װredis

