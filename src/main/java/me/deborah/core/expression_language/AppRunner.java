package me.deborah.core.expression_language;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    // - @Value 에노테이션
    //@Value("#{}") : 표현식을 참고하는 방법.
    @Value("#{1 + 1}")
    int value;

    @Value("#{'hello ' + 'world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    // @Value{"${}"} : Property를 참고하는 방법
    // dir : resources/application.properties에 정의되어있는 데이터 가져오기

    @Value("${my.value}")
    int myValue;

    // 위와 같이 표현식과 프로퍼티 를 같이 사용할 수 있다.
    // 이때 주의점 표현식안에는 프로퍼티를 사용할수 있지만, 프로퍼티 안에서는 표현식을 사용할 수 없음.
    @Value("#{${my.value} eq 100}") // 표현식 안에 프로퍼티를 넣기 가능.
    boolean isMyValue100;

    // 이외 에도 더 많은데 레퍼런스 찾아보삼. Arrays, List, Maps등 데이터 스트럭쳐 오퍼레이터 매서드 호출 등 여러가지 지원함.

    // 빈 참조하는 것 지원함!
    @Value("#{sample.data}")
    int sampleData;

    // - @ConditionalOnExpression 어노테이션
    // 선택적으로 빈을 등록하거나 빈 설정 파일을 읽어들일 때 사용하는 에노테이션
    // 스프링 El 기반으로 선별적으로 등록할 수 있음.


    // 더 많은데 사용법은.... 쓸때 레퍼런스 찾아봐야할 듯

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("============");
        System.out.println(value);
        System.out.println(greeting);
        System.out.println(trueOrFalse);
        System.out.println(myValue);
        System.out.println(isMyValue100);
        System.out.println(sampleData);

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression =  parser.parseExpression("2 + 100");
        Integer value = expression.getValue(Integer.class);
        System.out.println(value);
        // 이때 Spring Expression Lauguage도 해당하는 타입으로 변환할 때 Conversion Service를 사용하는 것임.
    }
}
