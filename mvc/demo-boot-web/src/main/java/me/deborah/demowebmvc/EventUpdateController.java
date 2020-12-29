package me.deborah.demowebmvc;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class EventUpdateController {

    // 3
    @PostMapping("/events")
    @ResponseBody
    public String createEvent() {
        return "event";
    }

    // 5
    @PutMapping("/events")
    @ResponseBody
    public String updateEvent() {
        return "event";
    }
}
