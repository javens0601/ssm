package com.javen.springinitializr;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@PropertySource("classpath:app2.properties")
public class SpringInitializrApplication {

    public static void main(String[] args) throws Exception{
        //SpringApplication.run(SpringInitializrApplication.class, args);
        //自定义springApplication
        SpringApplication application = new SpringApplication(SpringInitializrApplication.class);
        application.setBannerMode(Banner.Mode.LOG);

        //读取外部配置文件
        Properties properties = new Properties();
        InputStream inputStream = SpringInitializrApplication.class.getClassLoader().getResourceAsStream("app.properties");
        properties.load(inputStream);
        application.setDefaultProperties(properties);

        application.run(args);

    }

}
