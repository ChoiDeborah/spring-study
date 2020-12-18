package me.deborah.core.resource_and_validation.validattion_abstraction;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "notempty", "Empty title is now allowed.");
        // notempty.title 이렇게 적지 않는 이유?
        // 에러코드가 생성될 때 에러코드 외에 3가지를 추가해줌.
        // 반드시 errors uitil에 다가만 넣어야하는 것은 아니다.
        // 이처럼 벨리데이션 유틸없이도 검증하는 코드를 작성할 수 있음.
        Event event = (Event)target;
        if(event.getTitle() == null) {
            //errors.reject();
        }
    }
}
