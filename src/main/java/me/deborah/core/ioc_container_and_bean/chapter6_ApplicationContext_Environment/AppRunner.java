package me.deborah.core.ioc_container_and_bean.chapter6_ApplicationContext_Environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext ctx;

    @Value("${app.name}")
    String appName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Environment environment = ctx.getEnvironment();
        System.out.println(Arrays.toString(environment.getActiveProfiles()));
        // 디폴트 프로파일은 어떤 프로파일이느냐에 상관없이 적용되는 프로파일
        System.out.println(Arrays.toString(environment.getDefaultProfiles()));

        // 둘중 우선순위가 뭐가 더 높냐?
        // PeroperySource? or JVM Option?
        //environment.getProperty("app.name");
        System.out.println(environment.getProperty("app.about"));
        System.out.println(appName);
    }
}
