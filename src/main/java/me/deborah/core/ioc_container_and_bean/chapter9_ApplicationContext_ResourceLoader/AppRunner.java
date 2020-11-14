package me.deborah.core.ioc_container_and_bean.chapter9_ApplicationContext_ResourceLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 문자열이 복잡함..! 담시간에...Resource 추상화 시간에...
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        System.out.println(resource.exists());
        // 리소스 디렉토리에 있는 것들이 빌드시에 target 디렉토리 밑으로 들어가면서 classes path에 들어간다.
        System.out.println(resource.getDescription());
        // readString은 java 11 이후에 만들어진 Method임
        System.out.println(Files.readString(Path.of(resource.getURI())));

    }
}
