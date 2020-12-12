package me.deborah.corebasic.autowired;

import me.deborah.corebasic.AutoAppConfig;
import me.deborah.corebasic.discount.DiscountPolicy;
import me.deborah.corebasic.member.Grade;
import me.deborah.corebasic.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    // - 조회한 빈이 모두 필요할 때, List, Map
    // 의도적으로 정말 해당 타입의 스프링 빈이 다 필요한 경우도 있다.
    // 예를 들어서 할인 서비스를 제공하는데, 클라이언트가 할인의 종류(rate, fix)를 선택할 수 있다고 가정해보자.
    // 스프링을 사용하면 소위 말하는 전략 패턴을 매우 간단하게 구현할 수 있다.
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }

    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            // 주입 분석
            //Map<String, DiscountPolicy> : map의 키에 스프링 빈의 이름을 넣어주고,
            // 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
            //List<DiscountPolicy> : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
            // 만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다.

            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        // - 비즈니스 로직 중에서 다형성을 적극 활용할 때
        // 의존관계 자동 주입 - 조회한 빈이 모두 필요할 때, List, Map을 다시 보자.
        // DiscountService 가 의존관계 자동 주입으로 Map<String, DiscountPolicy> 에 주입을 받는 상황을 생각해보자. 여기에 어떤 빈들이 주입될 지, 각 빈들의 이름은 무엇일지 코드만 보고 한번에 쉽게 파악할 수 있을까? 내가 개발했으니 크게 관계가 없지만, 만약 이 코드를 다른 개발자가 개발해서 나에게 준 것이라면 어떨까?
        // 자동 등록을 사용하고 있기 때문에 파악하려면 여러 코드를 찾아봐야 한다.
        // 이런 경우 수동 빈으로 등록하거나 또는 자동으로하면 특정 패키지에 같이 묶어두는게 좋다! 핵심은 딱 보고 이해가 되어야 한다!
        // 이 부분을 별도의 설정 정보로 만들고 수동으로 등록하면 다음과 같다.

        // @Configuration
        //  public class DiscountPolicyConfig {
        //      @Bean
        //      public DiscountPolicy rateDiscountPolicy() {
        //          return new RateDiscountPolicy();
        //      }
        //      @Bean
        //      public DiscountPolicy fixDiscountPolicy() {
        //          return new FixDiscountPolicy();
        //      }
        //}
        // 이 설정 정보만 봐도 한눈에 빈의 이름은 물론이고, 어떤 빈들이 주입될지 파악할 수 있다.
        // 그래도 빈 자동 등 록을 사용하고 싶으면 파악하기 좋게 DiscountPolicy 의 구현 빈들만 따로 모아서 특정 패키지 모아두자.

        // 참고로 스프링과 스프링 부트가 자동으로 등록하는 수 많은 빈들은 예외다.
        // 이런 부분들은 스프링 자체를 잘 이해하고 스프링의 의도대로 잘 사용하는게 중요하다.
        // 스프링 부트의 경우 DataSource 같은 데이터베이스 연결에 사용하는 기술 지원 로직까지 내부에서 자동으로 등록하는데,
        // 이런 부분은 메뉴얼을 잘 참고해서 스 프링 부트가 의도한 대로 편리하게 사용하면 된다.
        // 반면에 스프링 부트가 아니라 내가 직접 기술 지원 객체를 스프링 빈으로 등록한다면 수동으로 등록해서 명확하게 들어내는 것이 좋다.

        // 정리
        // 편리한 자동 기능을 기본으로 사용하자
        // 직접 등록하는 기술 지원 객체는 수동 등록
        // 다형성을 적극 활용하는 비즈니스 로직은 수동 등록을 고민해보자

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);

            // 로직 분석
            // DiscountService는 Map으로 모든 DiscountPolicy 를 주입받는다.
            // 이때 fixDiscountPolicy , rateDiscountPolicy 가 주입된다.
            // discount () 메서드는 discountCode로 "fixDiscountPolicy"가 넘어오면
            // map에서 fixDiscountPolicy 스프링 빈을 찾아서 실행한다.
            // 물론 “rateDiscountPolicy”가 넘어오면 rateDiscountPolicy 스프링 빈을 찾아서 실행한다
        }
    }
}
