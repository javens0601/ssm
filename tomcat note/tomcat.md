## Tomcat 学习笔记

```tex
tomcat是一个http服务器 + servlet容器，servlet没有main方法，不能独立运行，必须将它放在servlet容器中，由容器实例化俩调用它。
```

![image-20210115133350551](.\image-20210115133350551.png)

![](.\c612cea018a85c0d9e64b65e33b6286.png)

### tomcat 源码

- 关闭tomcat 
  - 连接server的端口，8085，输入SHUTDOWN 就会关闭了，在配置文件server.xml里面有配置tomcat的端口