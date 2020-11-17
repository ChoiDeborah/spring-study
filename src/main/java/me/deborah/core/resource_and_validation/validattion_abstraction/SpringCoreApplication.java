package me.deborah.core.resource_and_validation.validattion_abstraction;

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
