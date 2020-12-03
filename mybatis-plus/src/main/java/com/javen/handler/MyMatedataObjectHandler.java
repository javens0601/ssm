package com.javen.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis plus 的字段自动填充功能，实现这个接口就可以了
 * MetaObjectHandler
 */
@Slf4j
@Component
public class MyMatedataObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert start ...");
        this.setFieldValByName("cTime", new Date(), metaObject);
        this.setFieldValByName("uTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update start ...");
        this.setFieldValByName("uTime", new Date(), metaObject);
    }
}
