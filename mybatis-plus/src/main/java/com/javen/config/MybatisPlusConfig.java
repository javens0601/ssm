package com.javen.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 */
//代表这是一个配置类
@Configuration
//自动管理事务
@EnableTransactionManagement
//扫描mapper文件
@MapperScan(value = "com.javen.mapper")
public class MybatisPlusConfig {

    /**
     * 乐观锁的插件bean
     * @return
     */
    @Bean
    public MybatisPlusInterceptor optimisticLockerInnerInterceptor() {
        //return new OptimisticLockerInnerInterceptor();
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        return paginationInnerInterceptor;
    }
}
