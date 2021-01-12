package com.javen.security.springmvc.init;

import com.javen.security.springmvc.config.WebSecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurtiyApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    protected SpringSecurtiyApplicationInitializer() {
        //super(WebSecurityConfig.class);
    }
}
