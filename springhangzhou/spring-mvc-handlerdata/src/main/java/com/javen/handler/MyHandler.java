package com.javen.handler;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class MyHandler implements Converter<String, Date> {
    public Date convert(String s) {
        return null;
    }
}
