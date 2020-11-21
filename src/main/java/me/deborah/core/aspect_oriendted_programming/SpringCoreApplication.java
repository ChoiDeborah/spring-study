package me.deborah.core.aspect_oriendted_programming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// Ioc Chapter 6 Environment Property
//@PropertySource("classpath:/app.properties")
public class SpringCoreApplication {
    public static void main(String[] args) {
        // 스프링 어플리케이션은 웹어플리케이션을 기본으로 띄워주는데
        // 웹 어플리케이션을 안띄우고 그냥 자바 메인 메서드 실행하듯이 서버모드가 아닌 상태로 띄우는 방법
        // 끄지 않아도 자동으로 꺼짐 웹서버가 기동되지 않기 때문에 살짝 더 빠르기도 하고
        SpringApplication app = new SpringApplication(SpringCoreApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

        //SpringApplication.run(SpringCoreApplication.class, args);

    }

}
