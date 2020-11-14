package me.deborah.core.ioc_container_and_bean.chapter6_ApplicationContext_Environment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// 클래스에 정의
@Configuration
@Profile("test")

//@Component @Profile("test")
public class TestConfiguration {

    // 이 빈 설정 파일은 테스트 테스트 프로파일일 때만 사용이되는 빈 설정파일이 된다.
    // 테스트라는 프로파일로 이 어플리케이션을 실행하기 전 까지는 이 빈 설정 파일이 적용되지 않음.
    // 그럼 우리가 가지고 있는 어플리 케이션도 당연히 북 리파지토리를 주입 받을 수 없다.
    // 따라서 에러가 발생함.

    // 프로파일 지정 방법?
    // Edit Configuration -> Active profile-> test 입력
    // 또는 Edit Configuration->VM Option에 -Dspring.profiles.active="test"

    // 프로파일 표현식
    // !(not)
    // & and
    // | or
    // ex @Profile("!prod")

    @Bean
    public BookRepository bookRepository() {
        return new TestBookRepository();
    }
}
