package me.deborah.corebasic;

import me.deborah.corebasic.discount.FixDiscountPolicy;
import me.deborah.corebasic.member.MemberService;
import me.deborah.corebasic.member.MemberServiceImpl;
import me.deborah.corebasic.member.MemoryMemberRepository;
import me.deborah.corebasic.order.OrderService;
import me.deborah.corebasic.order.OrderServiceImpl;

// AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성 한다.
// AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입 해준다.

// 객체의 생성과 연결은 AppConfig 가 담당한다.
// DIP 완성: MemberServiceImpl 은 MemberRepository 인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
// 관심사의 분리: 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
public class AppConfig {

    // appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl 을 생성하면서 생성자로 전달한다.
    // 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
