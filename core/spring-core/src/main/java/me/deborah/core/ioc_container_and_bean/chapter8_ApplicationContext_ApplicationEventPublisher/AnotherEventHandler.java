package me.deborah.core.ioc_container_and_bean.chapter8_ApplicationContext_ApplicationEventPublisher;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class AnotherEventHandler {
    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE+2)
    public void handle(MyEvent myEvent) {
        // 순차적으로 실행 됨.
        // 뭐가 먼저 실행될 지는 모르지만 A핸들러 -> B핸들러
        System.out.println(Thread.currentThread().toString());
        System.out.println("Anohter" + myEvent.getData());
    }

}
