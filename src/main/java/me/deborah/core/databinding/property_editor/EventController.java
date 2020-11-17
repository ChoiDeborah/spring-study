package me.deborah.core.databinding.property_editor;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    // 이벤트 클래스 타입을 처리할 프로퍼 에디터를 등록할수 있음
    // 웹 데이터 바인더라는 곳에

    // 컨트롤러가 어떤 요청을 처리하기전에
    // 컨트롤러에서 정의한 데이터 바인더에 들어있는 프로퍼티 에디터를 사용하게된다.
    // 따라서 이 프로퍼티 에디터를 사용했기 때문에 문자로 들어온 것이 이벤트 객체로 변환됨.
    // 프로퍼티 에디터는... 여러 단점들이 있음

    // 스프링 3부터는 다른 데이터 바인딩과 관련된 인터페이스와 기능들이 추가
   @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Event.class, new EventEditor());
    }
    @GetMapping("event/{event}")
    public String getEvent(@PathVariable Event event) {
        System.out.println(event);
        return event.getId().toString();
    }
}
