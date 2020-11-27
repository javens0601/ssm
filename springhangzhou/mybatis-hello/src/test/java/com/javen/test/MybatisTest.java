package com.javen.test;

import com.javen.mapper.UserMapper;
import com.javen.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * 1、添加pom依赖，mybatis的核心jar包和数据库版本对应的驱动jar包
 * 2、新建数据库表
 * 2、添加mybatis的全局配置文件
 * 3、修改全局配置文件中的数据库连接信息
 * 5、添加数据库表对应的pojo对象
 * 6、添加对应的POJOMapper.xml
 * 修改namespace
 * 如果是statementId，没有特殊要求
 * 如果是接口绑定的方式，必须等于接口的全限定名
 * 7、修改mybatis的全局配置文件，修改mapper
 */
@Slf4j
public class MybatisTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 基于statementId的方式
     *
     */
    @Test
    public void test01() throws IOException {
        User user = null;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            user = (User) session.selectOne("com.javen.pojo.UserMapper.selectUser", 1);
        }

        System.out.println(user);
    }

    //基于接口绑定的方式
    @Test
    public void test02() throws IOException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user1 = mapper.selectUser(1);
            log.debug(user1.toString());
            log.info(user1.toString());
            log.error(user1.toString());
            System.out.println(user1);
        }

    }

    //基于注解的方式
}
