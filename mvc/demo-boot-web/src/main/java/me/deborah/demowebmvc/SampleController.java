package me.deborah.demowebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

    @GetMapping("events/form")
    public String eventForm(Model model) {
        Event event = new Event();
        event.setLimit(100);
        model.addAttribute("event", new Event());
        return "events/form";
    }

    @PostMapping("/events")
    public String createEvent(@Validated @ModelAttribute Event event,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "/events/form";
        }

        // DB SAVE 로직 타야 함.
        //List<Event> eventList = new ArrayList<>();
        //eventList.add(event);
        //model.addAttribute("eventList", eventList);
        return "redirect:events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        // DB에서 읽어왔다고 가정하겠다. 밑의 코드는 예제를 위한 것
        Event event = new Event();
        event.setName("mozzi");
        event.setLimit(10);

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        model.addAttribute("eventList", eventList);

        return "events/list";
    }
}
