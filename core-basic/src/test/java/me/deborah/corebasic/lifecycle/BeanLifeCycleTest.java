package me.deborah.corebasic.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //스프링 컨테이너를 종료, ConfigurableApplicationContext 필요
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }

    // 실행해보면 다음과 같은 이상한 결과가 나온다.

    // 생성자 호출, url = null
    // connect: null
    // call: null message = 초기화 연결 메시지

    // 생성자 부분을 보면 url 정보 없이 connect가 호출되는 것을 확인할 수 있다.
    // 너무 당연한 이야기이지만 객체를 생성하는 단계에는 url이 없고, 객체를 생성한 다음에 외부에서 수정자 주 입을 통해서
    // setUrl() 이 호출되어야 url이 존재하게 된다.

    // 스프링 빈은 간단하게 다음과 같은 라이프사이클을 가진다.
    // 객체 생성 의존관계 주입
    // 스프링 빈은 객체를 생성하고, 의존관계 주입이 다 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
    // 따라서 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.
    //
    // 그런데 개발자가 의존관계 주입이 모두 완료된 시점을 어떻게 알 수 있을까?
    // 스프링은 의존관계 주입이 완료되면 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능을 제공한다.
    // 또한 스프링은 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다. 따라서 안전하게 종 료 작업을 진행할 수 있다.

    // 스프링 빈의 이벤트 라이프사이클
    // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸 전 콜백 -> 스프링 종료

    // 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출 (생성자 주입의 경우 빈이 생성되는 시점임)
    // 소멸전 콜백: 빈이 소멸되기 직전에 호출

    // 스프링은 다양한 방식으로 생명주기 콜백을 지원한다.

    // > 참고: 객체의 생성과 초기화를 분리하자.
    // > 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면에 초기화는
    // 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는등 무거운 동작을 수행한다.
    // > 따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화 하는 부분
    // 을 명확하게 나누는 것이 유지보수 관점에서 좋다. 물론 초기화 작업이 내부 값들만 약간 변경하는 정도로 단 순한 경우에는 생성자에서 한번에 다 처리하는게 더 나을 수 있다.
    // > 참고: 싱글톤 빈들은 스프링 컨테이너가 종료될 때 싱글톤 빈들도 함께 종료되기 때문에 스프링 컨테이너가 종료되기 직전에 소멸전 콜백이 일어난다. 뒤에서 설명하겠지만 싱글톤 처럼 컨테이너의 시작과 종료까지 생존하는 빈도 있지만, 생명주기가 짧은 빈들도 있는데 이 빈들은 컨테이너와 무관하게 해당 빈이 종료되기 직전에 소멸전 콜백이 일어난다. 자세한 내용은 스코프에서 알아보겠다.

    // 스프링은 크게 3가지 방법으로 빈 생명주기 콜백을 지원한다.
    // 인터페이스(InitializingBean, DisposableBean)
    // 설정 정보에 초기화 메서드, 종료 메서드 지정
    // @PostConstruct, @PreDestory 애노테이션 지원

    // @PostConstruct , @PreDestroy 이 두 애노테이션을 사용하면 가장 편리하게 초기화와 종료를 실행할 수 있다.
    // - @PostConstruct, @PreDestory 애노테이션 특징
    // 최신 스프링에서 가장 권장하는 방법이다.
    // 애노테이션 하나만 붙이면 되므로 매우 편리하다.
    // 패키지를 잘 보면 javax.annotation.PostConstruct 이다. 스프링에 종속적인 기술이 아니라 JSR-250 라는 자바 표준이다.
    // 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.
    // 컴포넌트 스캔과 잘 어울린다.
    // 유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것이다. 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 기능을 사용하자.

    // 정리
    // @PostConstruct, @PreDestory 애노테이션을 사용하자
    // 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean 의 initMethod , destroyMethod 를 사용하자
}
