package me.deborah.core.ioc_container_and_bean.chapter8_ApplicationContext_ApplicationEventPublisher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

// 4.2 이전에는 Implements ApplicationListener<MyEvent> 로 구현 했어야 함.
//@Component
//public class MyEventHandler implements ApplicationListener<MyEvent> {
//
//    @Override
//    public void onApplicationEvent(MyEvent myEvent) {
//        System.out.println("Event 발생 ! 데이터는 : " + myEvent.getData());
//    }
//}

// 4.2 이후 핸들러 또한 특정한 클래스를 구현하지 않아도 됨.
// 대신 Handler는 Bean으로 등록이 되어야함. 대신 Event는 Bean으로 등록 안해도 됨.
@Component
public class MyEventHandler {
    @EventListener

    @Order(Ordered.HIGHEST_PRECEDENCE)
    public void handle(MyEvent myEvent) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("Event 발생 ! 데이터는 : " + myEvent.getData());
    }

    // 스프링이 기본으로 제공해주는 ApplicationContext관련 이벤트가 있음.
    // 그 이벤트 처리하는 핸들러들

    @EventListener
    @Async
    public void handle(ContextRefreshedEvent event) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextRefreshedEvent");
        // 이 이벤트들은 ApplicationContext에 관한거라 아래와 같이 ApplicationContext를 얻어와 사용할 수도 있음.
        ApplicationContext applicationContext = event.getApplicationContext();
    }

    // ContextRefreshedEvent : ApplicationContext를 초기화 했거나 리프래시 했을 때 발생
    // ContextStartedEvent : ApplicationContext를 start()하여 라이프사이클 빈들이 시작신호를 받은 시점에 발생
    // ContextStoppedEvent : ApplicationContext stop()하여 라이프 사이클 빈들이 정지신호를 받은 시점에 발생
    // ContextClosedEvent : ApplicationContext를 close()하여 싱글톤 빈 소멸되는 시점에 발생
    // RequestHandledEvent : HTTP요청을 처리 했을 때 발생
}



