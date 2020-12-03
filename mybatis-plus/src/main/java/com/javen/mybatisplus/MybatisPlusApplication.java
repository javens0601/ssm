package com.javen.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描mapper文件
//@MapperScan("com.javen.mapper") //将mapper扫描的配置转移到mybatis plus 配置类进行
@ComponentScan(basePackages = "com.javen")
public class MybatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusApplication.class, args);
	}

}
