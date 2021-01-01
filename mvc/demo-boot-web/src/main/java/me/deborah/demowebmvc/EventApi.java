package me.deborah.demowebmvc;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping
    public Event createEvent(@RequestBody @Valid Event event /*HttpEntity<Event> request*/, BindingResult bindingResult) {
        // save event
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                // 원하는 처리 해야함. 에러 발생 안함
                System.out.println("error = " + error);
            });

        }
        //return request.getBody();
        //return request.getHeaders().getContentType();
        return event;
    }
}