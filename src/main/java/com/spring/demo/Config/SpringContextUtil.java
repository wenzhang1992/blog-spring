package com.spring.demo.Config;

import org.apache.catalina.core.ApplicationContext;

public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context){
        applicationContext = context;
    }
}
