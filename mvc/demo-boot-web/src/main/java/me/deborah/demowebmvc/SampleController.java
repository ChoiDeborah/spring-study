package me.deborah.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class SampleController {

    @GetMapping("events/form/name")
    public String eventssFormName(Model model, HttpSession httpSession) {
        model.addAttribute("event", new Event());
        return "/events/form-name";
    }

    @PostMapping("/events/form/name")
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/events/form-name";
        }
        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventssFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "/events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        /*Model model*/
                                        RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/events/form-limit";
        }
        sessionStatus.setComplete();
        // 리다이렉트 시 Model에 들어있는 primitive Type 데이터는 URI 쿼리 매개변수에 추가됨
        // ex) redirect:events/list?name=mozzi
        // 스프링 부트에서는 기본적으로 비활성화 되어있다.
        //model.addAttribute("name", event.getName());
        //model.addAttribute("limit", event.getLimit());

        // redirect attribute에 명시한 것들만 쿼리 파라미터로 보고자 하는 경우는 아래와 같이 쓴다.
        attributes.addAttribute("name", event.getName());
        attributes.addAttribute("limit", event.getLimit());
        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(/*@RequestParam String name,
                            @RequestParam Integer limit,*/
                            // 주의점 @SessionAttributes("event") 이름과 같은거 쓰면 안됨.
                            // 이름이 같으면 세션에 있는거랑 같은 값 참조하려 함
                            // 이름 다르게 해줘야 리퀘스트 파라미터에 복합객체로 바인딩 해서 쓰게 해줌.
                            @ModelAttribute("newEevnt") Event event,
                            Model model,
                            @SessionAttribute LocalDateTime visitTime /*HttpSession httpSession*/) {
        System.out.println("visitTime = " + visitTime);

        //LocalTime visitTime = (LocalDataTime) httpSession.getAttribute("visitTime")  // Obejct로 반환 됨
        //System.out.println(visitTime);

//        Event event = new Event();
//        event.setName(name);
//        event.setLimit(limit);

        Event spring = new Event();
        spring.setName("spring");
        spring.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(spring);
        eventList.add(event);

        model.addAttribute("eventList", eventList);

        return "events/list";
    }
}
