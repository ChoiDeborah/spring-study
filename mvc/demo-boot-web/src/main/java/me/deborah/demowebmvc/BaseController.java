package me.deborah.demowebmvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {

    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException exception, Model model) {
        model.addAttribute("message", "runtime error");
        return "error";
    }

    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");    // 아이디 값을 바인딩 하지 않음.
        webDataBinder.addValidators(new EventValidator());

    }

    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", List.of("study", "semina", "hobby", "social"));
    }

//    @ModelAttribute("categories")
//    public List<String> categories(Model model) {
//        return List.of("study", "semina", "hobby", "social");
//    }

}
