package me.deborah.demowebmvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    @PostMapping
    //@ResponseBody   // 리턴하는 값을 응답 본문에 넣어 줌 RestController 으로 인해 기입할 필요 없
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event /*HttpEntity<Event> request*/, BindingResult bindingResult) {
        // save event
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        // 상태 코드 바꿀 수도 있음
        //return new ResponseEntity<Event>(event, HttpStatus.CREATED);
        return ResponseEntity.ok(event);
    }
}