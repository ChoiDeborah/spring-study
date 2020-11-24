package me.deborah.springwebmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

    // 서비스를 주입받아 사용할 수 있음.
    @Autowired
    EventService eventService;

    // 특정한 요청이 들어올 때 웹브라우져에서 이벤트 목록을 보여준다.
    // 화면에 전달할 모줌(map collection 이라고 생각하면 됨)에 객체를 담아
    //@RequestMapping(value = "/events", method = RequestMethod.GET)
    @GetMapping("/events")

    public String events(Model model) {
        // 주입 받은 걸 모델에 담아줌.
        model.addAttribute("events", eventService.getEvents());
        // 문자열이 뷰를 찾는데 키워드가 됨
        return "events";
    }
}
