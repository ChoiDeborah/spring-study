package me.deborah.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SampleController {
//    @GetMapping("/events/{id}")
//    // 리턴한 값을 응답 본문에 쓰게 되고 그떄 HTTPMessageConverter를 이용해 이벤트라는 객체를 js on으로 변환해 응답으로 내보내줌
//    @ResponseBody
//    public Event getEvent(@PathVariable Integer id) {
//        Event event = new Event();
//        event.setId(id);
//        return event;
//    }

    @GetMapping("/events/{id}")
    // 리턴한 값을 응답 본문에 쓰게 되고 그떄 HTTPMessageConverter를 이용해 이벤트라는 객체를 js on으로 변환해 응답으로 내보내줌
    @ResponseBody
    public Event getEvent(@PathVariable Integer id, @MatrixVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name );
        return event;
    }
}
