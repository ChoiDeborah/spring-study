package me.deborah.core.ioc_container_and_bean.chapter8_ApplicationContext_ApplicationEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ApplicationEventPublisher publishEvent;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 이벤트를 발생시킬 수 있다.
        // 이벤트 1번 발생시키지만 핸들링 하는 리스너가 2개이므로 2군데에서 메세지가 찍힐것임.

        publishEvent.publishEvent(new MyEvent(this, 100));

        // 임의대로 순서를 정할 수 있음.
        // @Order(Ordered.HIGHEST_PRECEDENCE)

        // 비 동기적으로 실행하고 싶다면
        // @Async 당연히 순서는 보장안됨.
        // 각각의 스레드 풀에서 따로 돈다. Application에 @EnableAsync
    }
}
