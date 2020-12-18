package me.deborah.core.ioc_container_and_bean.chapter7_ApplicationContext_MessageSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AppRunner implements ApplicationRunner {
    // ApplicationContext에 가보면 MessageSource 라는 인터페이스를 직접 구현하고 있다.
    // 따라서 ApplicationContext를 주입 받을 수 있으면 사실상 MessageSource를 주입 받을 수 있다.

    @Autowired
    //ApplicationContext applicationContext;
    MessageSource messageSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 메세지 소스가 사용하는 인퍼테이스를 사용할 수 있다.
        // resources/messages.properties 및 message_ko_KR.properties 파일 참조.

        // 원래 직접 빈으로 등록해야하는데 이미 빈으로 등록되어있음
        // 스프링 부트를 사용하면 메세지를 읽어들이는 ResourceBundleMessageSource
        // 이 빈이 Messages라는 리소스 번들을 읽게 됨.
        System.out.println(messageSource.getClass());
        System.out.println(messageSource.getMessage("greeting", new String[]{"deobrah"},  Locale.KOREA));
        System.out.println(messageSource.getMessage("greeting", new String[]{"deobrah"},  Locale.getDefault()));
    }

    // 리로딩 기능이 있는 메세지 소스 사용하기.
//    @Bean
//    public MessageSource messageSource() {
//        var messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:/messages");
//        messageSource.setDefaultEncoding("UTF-8")
//        messageSource.setCacheSeconds(3);
//        return messageSource;
//    }
}
