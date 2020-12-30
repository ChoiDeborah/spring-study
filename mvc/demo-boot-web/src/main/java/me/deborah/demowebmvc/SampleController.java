package me.deborah.demowebmvc;

import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {

    // @RequestParam
    // ● 요청 매개변수에 들어있는 단순 타입 데이터를 메소드 아규먼트로 받아올 수 있다.
    // ● 값이 반드시 있어야 한다
    //   ○ required=false 또는 Optional을 사용해서 부가적인 값으로 설정할 수도 있다.
    // ● String 이 아닌 값들은 타입 컨버전을 지원한다.
    // ● Map<String, String> 또는 MultiValueMap<String, String>에 사용해서 모든 요청 매개변수를 받아 올 수도 있다.
    // ● 이 애노테이션은 생략 할 수 잇다.
    // 요청 매개변수란?
    // ● 쿼리 매개변수
    // ● 폼 데이터
    @PostMapping("/events")
    @ResponseBody
    // required = true 기본 값
    public Event getEvent(@RequestParam String name /*@RequestParam(required = false, defaultValue = "mozzi"*/,
                          @RequestParam Integer limit) {
        Event event = new Event();
        event.setName(name);
        event.setLimit(limit);
        return event;
    }
}
