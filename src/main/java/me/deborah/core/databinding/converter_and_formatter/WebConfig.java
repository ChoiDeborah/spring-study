package me.deborah.core.databinding.converter_and_formatter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 스프링 웹설정에 넣어준 컨버터가 모든 컨트롤러에서 동작을 한다.
    // 컨트롤러에 요청한 1이 컨버터에서 이벤트로 변환이 되어서 컨트롤러에서 이벤트 타입으로 받을 수 있음.
    // 근데 인테저 타입은 스프링이 알아서 변환해줌...
    // 그래서 모든걸 컨버팅 할 필요는 없다.

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new EventConverter.StringToEventConverter());
    }
}
