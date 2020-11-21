package me.deborah.core.databinding.converter_and_formatter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 스프링 웹설정에 넣어준 컨버터가 모든 컨트롤러에서 동작을 한다.
    // 컨트롤러에 요청한 1이 컨버터에서 이벤트로 변환이 되어서 컨트롤러에서 이벤트 타입으로 받을 수 있음.
    // 근데 인테저 타입은 스프링이 알아서 변환해줌...
    // 그래서 모든걸 컨버팅 정의 할 필요는 없다.

    //    @Override
    //    public void addFormatters(FormatterRegistry registry) {
    //        registry.addConverter(new EventConverter.StringToEventConverter());
    //    }

    // 포매터나 컨버터가 빈으로 등록되어있다면, 스프링 부트가 자동으로 ConversionService에 등록해준다.
    // 스프링 부트 사용 시 포메터와 컨버터가 자동으로 등록된다.
    // 포매터와 컨버터를 사용하기 위해서 Spring MVC설정을 새로 만들 필요없음.

    // 만약 Formatter를 빈으로 설정하지 않았다면 Converter와 같은 방식으로 여기서 등록해주면됨
    //    @Override
    //    public void addFormatters(FormatterRegistry registry) {
    //        registry.addFormatter(new EventFormatter());
    //    }

    // 타입을 변환하는 작업은 데이터 바인더 대신 컨버터와 포메터를 활용할 수 있게 해주는
    // ConversionService
    // WebMvcConfigurer 인터페이스를 통해 등록하는 애들은 컨버전 서비스에 등록이 되는 것이고
    // 컨버전 서비스를 통해 실제 변환하는 작업이 되는 것임.
    // - 스프링 MVC, 스프링 xml 빈(value) 설정, SpEl에서 사용
    // - DefaultFormattingConversionService 가 아래 두가지 인터페이스를 모두 구현
    //// - FormatterRegistry
    //// - ConversionService
    //// - 여러 기본 컨버터와 포매터를 등록해줌.
}
