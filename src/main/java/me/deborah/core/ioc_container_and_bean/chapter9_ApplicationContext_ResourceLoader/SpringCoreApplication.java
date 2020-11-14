package me.deborah.core.ioc_container_and_bean.chapter9_ApplicationContext_ResourceLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// Ioc Chapter 6 Environment Property
//@PropertySource("classpath:/app.properties")
public class SpringCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreApplication.class, args);
    }

}
