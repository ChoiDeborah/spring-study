package me.deborah.core.resource_and_validation.validattion_abstraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {
    // 스프링 부트를 쓴다면 스프링이 제공해주는 LocalValidatorFactoryBean을 자동으로 등록해준다.
    // 스프링이 Validator를 자동으로 주입함.
    @Autowired
    Validator validator;

// 아래와 같이 직접 구현하지 않아도 됨.
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Event event = new Event();
//        EventValidator eventValidator = new EventValidator();
//        // 어떤 객체를 검사할것인지 타겟, 어떤 이름,
//        Errors errors = new BeanPropertyBindingResult(event, "event");
//        // 이벤트 객체를 검사할 것이고 에러스에 검증에러를 담아 준다.
//        // 잘 안씀.
//        eventValidator.validate(event, errors);
//        // errors에 에러가 있는지 확인
//        System.out.println(errors.hasErrors());
//        errors.getAllErrors().forEach(e->{
//            System.out.println("====== error code ======");
//            Arrays.stream(e.getCodes()).forEach(System.out::println);
//            System.out.println(e.getDefaultMessage());
//        });
//   }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(validator.getClass());
        Event event = new Event();
        event.setLimit(-1);
        event.setEmail("aaa2");

        Errors errors = new BeanPropertyBindingResult(event, "event");
        // 이벤트 객체를 검사할 것이고 에러스에 검증에러를 담아 준다.
        // 잘 안씀.
        validator.validate(event, errors);
        // errors에 에러가 있는지 확인
        System.out.println(errors.hasErrors());
        errors.getAllErrors().forEach(e->{
            System.out.println("====== error code ======");
            Arrays.stream(e.getCodes()).forEach(System.out::println);
            System.out.println(e.getDefaultMessage());
        });

    }
}
