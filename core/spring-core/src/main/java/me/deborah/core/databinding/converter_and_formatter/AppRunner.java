package me.deborah.core.databinding.converter_and_formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ConversionService conversionService;

    // 웹어플리 케이션의 경우에 DefaultFormattingConversionService가 아닌
    // WebConversionService를 빈으로 등록 준다.
    // WebConversionService는 SpringBoot가 제공해 주는 클래스이다.
    // WebConversionService는 DefaultFormattingConversionService 를 상속해서 만들었다.
    // 그래서 조금 더 많은 기능을 가지고 있다.

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 등록되어 있는 모든 컨버터 확인하는 방법
        System.out.println(conversionService);

        // ConversionService가 실제 자기가 가지고 있는 컨버터와 포메터를 사용해서 실제로 컨버팅 작업을 할 수 있는 클래스이다.
        // conversionService.canConvert() // 컨버팅 가능한지 확인
        // 거의 직접 이렇게 하는 일은 거의 없음.
        System.out.println(conversionService.getClass().toString());
    }

    //
}
