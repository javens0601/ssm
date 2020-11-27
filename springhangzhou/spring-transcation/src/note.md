## Spring 声明式事务
- 事务的4大特性：ACID
    - A 原子性
    - C 一致性
    - I 隔离性
    - D 持久性
    
    
#### @Transactional
- 可以标记在类上面
- 可以标记在方法上面
- 建议@Transactional 注解卸载业务逻辑层，因为只有业务逻辑层才存在嵌套调用

#### 事务配置的属性
- isolation：设置事务的隔离级别
- propagation：事务的传播行为
- noRollbackFor:哪些异常事务可以不回滚
- noRollbackForClassName:填写的参数是全类名
- rollbackFor:哪些异常事务需要回滚
- rollbackForClassName:填写的参数是全类名
- readOnly：设置事务是否为只读事务
- timeout：事务超出指定执行时常后自动终止并回滚，单位是秒（s）

- 脏读 
    - 解决：设置事务的隔离级别为 读已提交 Isolation.READ_COMMITTED
- 不可重复读
    - 解决：设置事务的级别为 可重复读 Isolation.REPEATABLE_READ，确保transaction 1 可以多次从一个字段中读取到相同的值，禁止其他事务对这个字段进行更新（行锁）
- 幻读
    - 解决：可串行化 Isolation.SERIALIZABLE 确保transaction 1 可以多次从一个表中读取到相同的行，在事务执行期间，禁止其他事务对这个表进行增删改操作（表锁），可以避免任何并发问题，但是性能低下
    
- 不可重复读和幻读
    - 不可重复读只需要锁行，重点在于delete和update
    - 幻读需要锁表，重点在于insert
    
    
- 查询mysql的事务隔离级别
    - select @@tx_isolation;
    - 默认是可重复读：REPEATABLE-READ    
    
- oracle 的默认事务隔离级别是 读已提交    

#### 事务的传播特性

    
