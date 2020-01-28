package com.ponodan.loader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext ctx = new
                ClassPathXmlApplicationContext( "spring-context.xml" );

        ResourceLoader resourceLoader = ctx.getBean(ResourceLoader.class);

        System.out.println(resourceLoader.getOpenapiSpec());
    }
}
