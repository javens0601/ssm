## ansibleѧϰ�ʼ�



#### ansible ����

#### ansible �ص�

#### ansible �ܹ�ԭ��

#### ansible ��װ
- yum ��װ
	- yum install -y ansible

#### ansible ��������
ansible 10.1.20.114 -m ping -u username -k

#### ansible �����ļ�
/etc/ansible/ansible.cfg
host_key_checking	ssh���
log_path		��־��¼

#### ��������

- �鿴SELinux״̬��

1��/usr/sbin/sestatus -v      ##���SELinux status����Ϊenabled��Ϊ����״̬

SELinux status:                 enabled

2��getenforce                 ##Ҳ���������������

�ر�SELinux��

1����ʱ�رգ�����������������

setenforce 0                  ##����SELinux ��Ϊpermissiveģʽ

                              ##setenforce 1 ����SELinux ��Ϊenforcingģʽ

2���޸������ļ���Ҫ����������

�޸�/etc/selinux/config �ļ�

��SELINUX=enforcing��ΪSELINUX=disabled

������������


- ��������

ssh-keygen

ssh-copy-id 10.1.20.114