package me.deborah.corebasic.singleton;

import me.deborah.corebasic.AppConfig;
import me.deborah.corebasic.member.MemberRepository;
import me.deborah.corebasic.member.MemberServiceImpl;
import me.deborah.corebasic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService->memberRepository1 = " + memberRepository1);
        System.out.println("orderService->memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        // output : bean = class me.deborah.corebasic.AppConfig$$EnhancerBySpringCGLIB$$72bc5981
        System.out.println("bean = " + bean.getClass());
    }

    // 순수한 클래스라면 다음과 같이 출력되어야 한다. class me.deborah.corebasic.AppConfig
    // 그런데 예상과는 다르게 클래스 명에 xxxCGLIB가 붙으면서 상당히 복잡해진 것을 볼 수 있다.
    // 이것은 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
    // AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다!

    // @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
    // 스프링 빈이 없으면 생성 해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
    // 덕분에 싱글톤이 보장되는 것이다.
    // > 참고 AppConfig@CGLIB는 AppConfig의 자식 타입이므로, AppConfig 타입으로 조회 할 수 있다.

    // @Configuration 을 적용하지 않고, @Bean 만 적용하면 어떻게 될까?
    // @Configuration 을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장하지만, 만약 @Bean만 적용하면 어떻게 될까?

    // @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
    // memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.
    // 크게 고민할 것이 없다. 스프링 설정 정보는 항상 @Configuration을 사용하자
}
