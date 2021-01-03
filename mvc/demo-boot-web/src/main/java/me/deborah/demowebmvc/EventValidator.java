package me.deborah.demowebmvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Event.class.isAssignableFrom(aClass);    // 어떤 도메인 클레스에 대한 validation을 지원하는 거냐
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        if (event.getName().equalsIgnoreCase("aaa")) {
            errors.rejectValue("name", "wrongValue", "The value is not allowed");
        }
    }
}
