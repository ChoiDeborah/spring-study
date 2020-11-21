package me.deborah.core.aspect_oriendted_programming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// 클라이언트 코드
@Component
public class AppRunner implements ApplicationRunner {
    // 인터페이스 타입이 있는 경우에는 인터페이스로 주입받는 것을 권장
    @Autowired
    EventService eventService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventService.createEvent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }
}
