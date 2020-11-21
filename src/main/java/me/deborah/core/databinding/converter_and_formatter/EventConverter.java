package me.deborah.core.databinding.converter_and_formatter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// Generic type으로 2개를 받는다 Source와 Target
// 프로퍼티 에디와 같은 역할을 함
// 상태정보(Stateless == thread safe)가 없기 때문에 빈으로 등록해도 됨.
// ConverterRegistry에 등록해서 사용
// Spring Boot 없이 Spring MVC를 쓴다면...
// WebMvcConfigurer를 통해서 등록 가능

public class EventConverter {
    @Component
    public static class StringToEventConverter implements Converter<String, Event> {
        @Override
        public Event convert(String source) {
            return new Event(Integer.parseInt(source));
        }
    }

    @Component
    public static class EventToStringConverter implements Converter<Event, String> {
        @Override
        public String convert(Event source) {
            return source.getId().toString();
        }
    }
}
