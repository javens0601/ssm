package com.javen.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.javen.entity.User;
import com.javen.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

//@Slf4j
public class SpringTest {
    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("springContext.xml");
    }

    @Test
    public void test01() {

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        Long count = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        System.out.println("sql count is : " + count);

        User user = jdbcTemplate.queryForObject("select * from user where id=1", new BeanPropertyRowMapper<>(User.class));
        System.out.println("user is : " + user);

        /*DruidDataSource druidDataSource = context.getBean(DruidDataSource.class);
        System.out.println(druidDataSource);*/
        System.out.println("====================================================");
        IUserService userService = context.getBean(IUserService.class);
        System.out.println(userService.getUserInfo(new User()));
    }
}
