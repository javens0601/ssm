## mysql 学习笔记

[TOC]



### mysql安装

> ```shell
> #1、创建mysql用户
> groupadd mysql
> useradd -r -g mysql mysql
> #2、解压mysql的tar包，并移动到 /usr/local/mysql 目录下，
> mv mysql-5.7.32-linux-glibc2.12-x86_64/ mysql
> #3、创建mysql的data目录
> mkdir /usr/local/mysql/data
> #4、修改目录权限以及所属用户、用户组
> chown -R mysql:mysql /usr/local/mysql
> chmod -R 755 /usr/local/mysql
> #5、编译安装并初始化mysql，并记住临时密码
> cd /usr/local/mysql/bin
> ./mysqld --initialize --user=mysql --datadir=/usr/local/mysql/data --basedir=/usr/local/mysql
> 	#如果出现下面的报错
> 	./mysqld: error while loading shared libraries: libaio.so.1: cannot open shared object file: No such file or directory
> 	yum install  libaio-devel.x86_64
> 
> #6、添加mysql配置文件   my.cnf
> [mysqld]
> datadir=/usr/local/mysql/data
> port=3306
> sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
> symbolic-links=0
> max_connections=600
> innodb_file_per_table=1
> lower_case_table_names=1
> character_set_server=utf8
> #7、测试启动mysql
> /usr/local/mysql/support-files/mysql.server start
> #8、添加软连接，并重启
> ln -s /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
> ln -s /usr/local/mysql/bin/mysql /usr/bin/mysql
> service mysql restart
> #9、登陆mysql
> mysql -uroot -p
> #10、修改临时密码
> set password for root@localhost = password('yourpass');
> #11、开放远程连接
> use mysql;
> update user set user.Host='%' where user.User='root';
> flush privileges;
> #12、设置开机自动启动
> 1、将服务文件拷贝到init.d下，并重命名为mysql
> [root@localhost /]# cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysqld
> 2、赋予可执行权限
> [root@localhost /]# chmod +x /etc/init.d/mysqld
> 3、添加服务
> [root@localhost /]# chkconfig --add mysqld
> 4、显示服务列表
> [root@localhost /]# chkconfig --list
> 
> ```



### 基础架构

![image-20210122171739965](mysql-struct.png)

### 日志系统

binlog

redo log

undo log

### 事务隔离

ACID：

- Atomicity    原子性
- Consistency    一致性
- Isolation    隔离性
- Durability    持久性

数据库上多个事务同事操作的时候，就可能会出现 ***脏读、不可重复读、幻读*** 的问题。

隔离级别：

- 读未提交：
- 读提交：
- 可重复读：
- 串行化：

### 索引

索引 B+树

最左前缀

覆盖索引

索引下推

顺序扫描索引

- 索引的选择
  - change buffer
  - 什么条件下使用change buffer？
    - 唯一索引的更新就不能使用change buffer，只有普通索引可以使用，唯一索引都已经读到内存里面了，没必要再去使用change buffer，直接操作内存更快。
    - change buffer用的是buffer pool里面的内存，不能无限增大
  - change buffer 的使用场景
    - 使用change buffer加速更新，因为merge的时候是真正的进行数据更新的时刻，而change buffer的主要目的是将记录的变化动作缓存下来，所以在一个数据页做merge之前，change buffer记录的变更越多，收益就越大。因此在***写多读少***的场景下，页面写完的数据马上被访问到的概率比较小，此时change buffer的使用效果最好，这种业务模型常见的就是账单类，日志类的系统。
    - 如果一个业务的更新模式是写入之后马上会做查询，那么即使满足条件，将更新记录在changebuffer，但是之后由于马上要访问这个页面，会立即触发merge过程。这样随机访问IO的次数不会减少，反而增加了change buffer 的维护代价，所以对于这种业务模式来说，change buffer反而起到了副作用。
  - redo log主要节省的是随机写磁盘IO消耗（转成顺序写）
  - change buffer主要节省的是随机读磁盘的IO消耗

### 锁

- 全局锁
  - 对整个数据库实例加锁
  - 应用场景：全库逻辑备份
  - mysql提供一个加全局锁的方法，``` flush table with read lock``` 当需要真个库只读的时候，可以使用这个锁，之后其他线程的以下语句会被阻塞：数据更新语句（增删改）、数据定义语句（建表、修改表结构）和更新类事务的提交语句。
  - 全库只读readonly=true在slave上，如果用户有超级权限的话，readonly是失效的。

---

- 表锁
  - mysql有两种表级别的锁，一种是表锁，一种是元数据锁（meta data lock）MDL
  - *表锁*的语法是lock tables ... read/write   ;  unlock tables
  - *MDL*不需要显式使用，在访问一个表的时候会自动加上，MDL的作用是，保证读写的正确性
  - *MDL*防止DDL和DML并发的冲突

---

- 行锁
  - MyISAM引擎不支持行锁，并发控制只能使用表锁
  - 在InnoDB事务中，行锁是在需要的时候才加上的，但不是不需要了就立即释放，而是等到事务结束才释放，这个就是***两阶段协议锁***。
  - 如果你的事务中需要锁多个行，要把最可能造成锁冲突，最可能影响并发度的锁尽量往后放。

* 死锁和死锁检测，当死锁出现后，有两种策略：
  * 一种是直接进入等待，直到超时   ```innodb_lock_wait_timeout```，默认是50s
  * 推荐：另一种是发起死锁检测，发现死锁后，主动回滚死锁链条中的某一个事务，让其他事务得以继续执行，将参数```innodb_deadlock_detect```设置为on，表示开启这个逻辑。
* 怎么解决热点行更新导致的性能问题？
  * 如果能确保这个业务一定不会出现死锁，可以临时把死锁检测关掉
  * 控制并发度，比如同一行同时最多只能10个线程在更新，那么死锁检测的成本就很低。
  * 可以考虑将一行改成逻辑上的多行来减少锁冲突，比如影院账户，可以考虑放在多条记录上，比如10个记录，影院账户的总额就等于这个10个记录的值得和，这样每次冲突的概率就变成原来的1/10，可以减少锁等待个数，也就减少了死锁检测的cpu消耗。

---

- 间隙锁 GAP锁

---

### 事务隔离

在mysql里面有两个视图的概念：

- 一个是view，他是一个用查询语句定义的虚拟表，在调用的时候执行查询语句并生成结果。创建视图的语法是 create view，而他的查询方法与表一样。
- 另一个是innodb在实现MVCC时用到的一致性读视图，即consistent read view，用于支持RC（Read Commit，读提交）和RR(Repeatable Read, 可重复读)隔离级别的实现。它没有物理结构，作用是***在事务执行期间用来定义，“我能看见什么数据”***
- ***“快照”在MVCC里是怎么工作的？***
  - 在可重复读隔离级别下，事务在启动的时候就拍了快照，是基于整个库的。innodb里面每个事务有一个唯一的事务ID，叫做transaction id，在事务开始的时候向innodb的事务系统申请的，是按顺序严格递增的。数据表中的每一个记录，可能有多个版本，每个版本有自己的row trx_id
  - 在实现上，Innodb为每个事务构造了一个数组，用来保存这个事务启动瞬间，当前正在活跃的所有事务ID，活跃指的是 启动了但还没提交。数组里面事务ID的最小值记为低水位，当前系统里面已经创建过的事务ID的最大值加1记为高水位。***这个视图数组和高水位，就组成了当前事务的一致性视图（read view）***，而数据版本的可见性规则就是基于数据的 row trx_id和这个一致性视图的对比结果得到的。
  - Innodb利用了所有数据都有多个版本的这个特性实现了秒级创建快照的能力
  - 更新数据都是先读后写，而这个读，只能读当前的值，称为当前读
- 事务的可重复读的能力是怎么实现的？
  - 可重复读的核心就是一致性读，而事务更新数据的时候，就只能用当前读。如果当前的记录的行锁被其他事务占用的话，就需要进入锁等待。
  - 而读提交和可重复读的逻辑类似，区别在于：
    - 在可重复读隔离级别下，只需要在事务开始的时候创建一致性视图，之后事务里的其他查询都公用这一个视图；
    - 在读提交隔离级别下， 每一个语句执行前都会重新算出一个新的视图。

### mysql 备份表

- create table tablename as select * from table1;

- 备份db1里的t7表

  [root@stu1 mysql]#  mysqldump -u root -p123 db1 t7 > t7.sql

  恢复db1里的t7表
  [root@stu1 mysql]#  mysql -u root -p123 db1 < t7.sql

--查看索引基数
show index from j_test1;

--收集统计信息   --重新统计索引信息
ANALYZE table j_test1;



### 怎么给字符串字段加索引

- 前缀索引，节省空间，又不用额外增加太多的查询成本。
- 前缀索引对覆盖索引的影响：
  - 使用前缀索引就用不上覆盖索引对查询性能的优化了，，这也是在选择是否使用前缀索引是考虑的一个因素。

- 如果前缀的区分度不够的时候
  - 使用倒序存储
  - 使用hash字段

### 数据库表空间回收

一个Innodb表包含两部分，

- 表结构定义和数据；在mysql8.0以前，表结构是在 .frm后缀的文件里面，而8.0版本，则允许把表结构放在系统数据表中，因为表结构定义占用的空间很小。

- 参数 innodb_file_per_table，表数据既可以存在共享表空间里面，也可以是单独的文件，这个行为是由参数 innodb_file_per_table控制的。

  - OFF表示 表的数据放在系统共享表空间里面，也就是和数据字典放在一起
  - ON表示   每个Innodb表数据存储在一个以.ibd为后缀的文件中，

- 数据删除流程

  - delete只是把记录的位置或者数据页标记为了可以复用，但磁盘文件的大小是不会改变的。也就是说 通过delete命令是不能回收表空间的。
  - 不止是删除数据会造成空洞， 插入数据也会；如果数据是随机插入的就会造成数据页分裂。
  - 也就是说，经过大量增删改耳朵表，是可能存在大量空洞的，所以，如果能把这些空洞都去掉，就可以达到收缩表空间的目的。***重建表就可以达到这样的目的*** 
  - 重建表： 使用 alter table A engine=Innodb命令来重建表。
  - Online DDL 允许重建表的时候对源表进行操作。通过增加了一个row log日志文件来实现。
  - inplace DDL 在innodb内部完成的，使用临时文件

- count 原理

  - 在不同的mysql引擎中，count(*)有不同的实现方式。
  - myisam引擎把表的总行数存在了磁盘上，因此执行count(*)的时候直接返回这个数，效率很高。
  - innodb执行count的时候需要把数据一行一行地从引擎里面读出来，然后累积计数。

  - 小结：
    - MyISAM表虽然count很快，但是不支持事务
    - show table status命令虽然返回很快，但是不准确
    - innodb表直接count(*)会遍历全表，虽然结果准确，但是有性能问题。
  - 