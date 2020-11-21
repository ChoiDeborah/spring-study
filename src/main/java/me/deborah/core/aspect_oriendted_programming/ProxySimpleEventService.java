package me.deborah.core.aspect_oriendted_programming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// 인터페이스는 Subject 와 동일해야한다.
@Primary    // 같은타입이 있다면 하나를 선택하는 방
@Service
public class ProxySimpleEventService  implements EventService{
    // 이론적으로 인터페이스 타입의 빈을 주입받는걸 추천하지만
    // 프록시의 경우에는 서브젝트 빈을 주입받아 써야함. 그래서 서브젝트 빈의 이름을 명시해주는 게 좋음.
    // EventService simpleEventService; 해도 됨.
    @Autowired
    SimpeEventService simpeEventService;

    // 단점, 문제 중복코드가 생긴다. 프록시 클래스를 만드는데 드는 비용, 모든 클래스를 델리게이션 해줘야 함.
    // 런타임에 동적으로 프록시 객체를 만드는 방법이 있음

    @Override
    public void createEvent() {
        // 위임 델리게이션 함.
        long begin = System.currentTimeMillis();
        simpeEventService.createEvent();

        System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void publishEvent() {
        long begin = System.currentTimeMillis();

        simpeEventService.publishEvent();

        System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void deleteEvent() {
        simpeEventService.deleteEvent();
    }
}
