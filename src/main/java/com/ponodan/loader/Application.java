package com.ponodan.loader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext( "spring-context.xml" );
        ResourceReader resourceReader = context.getBean(ResourceReader.class);
        for (int i = 0; i < args.length; i++) {
            Resource resource = resourceReader.getResourceByPath(args[i]);
            String isExists = resource.exists() ? "exists" : "absent";
            System.out.println(resource.toString() + " " + isExists);
        }
    }
}

