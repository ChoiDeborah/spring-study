package me.deborah.corebasic;

import me.deborah.corebasic.discount.DiscountPolicy;
import me.deborah.corebasic.discount.FixDiscountPolicy;
import me.deborah.corebasic.discount.RateDiscountPolicy;
import me.deborah.corebasic.member.MemberRepository;
import me.deborah.corebasic.member.MemberService;
import me.deborah.corebasic.member.MemberServiceImpl;
import me.deborah.corebasic.member.MemoryMemberRepository;
import me.deborah.corebasic.order.OrderService;
import me.deborah.corebasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성 한다.
// AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입 해준다.

// 객체의 생성과 연결은 AppConfig 가 담당한다.
// DIP 완성: MemberServiceImpl 은 MemberRepository 인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
// 관심사의 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.

@Configuration
public class AppConfig {

    // appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl 을 생성하면서 생성자로 전달한다.
    // 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.
    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
       // AppConfig 에서 할인 정책 역할을 담당하는 구현을 FixDiscountPolicy 객체로 변경했다.
       // 이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다. 클라이언 트 코드인 OrderServiceImpl 를 포함해서 사용 영역의 어떤 코드도 변경할 필요가 없다.
       // 구성 영역은 당연히 변경된다. 구성 역할을 담당하는 AppConfig를 애플리케이션이라는 공연의 기획자로 생각하자. 공연 기획자는 공연 참여자인 구현 객체들을 모두 알아야 한다.
        return new RateDiscountPolicy();
    }

    // 기존 AppConfig를 보면 중복이 있고, 역할에 따른 구현이 잘 안보인다.

    // new MemoryMemberRepository() 이 부분이 중복 제거되었다. 이제 MemoryMemberRepository 를 다 른 구현체로 변경할 때 한 부분만 변경하면 된다.
    // AppConfig 를 보면 역할과 구현 클래스가 한눈에 들어온다. 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다
}
