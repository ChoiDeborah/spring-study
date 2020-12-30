package me.deborah.demowebmvc;

import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
