package me.deborah.corebasic.order;

import me.deborah.corebasic.discount.DiscountPolicy;
import me.deborah.corebasic.discount.FixDiscountPolicy;
import me.deborah.corebasic.discount.RateDiscountPolicy;
import me.deborah.corebasic.member.Member;
import me.deborah.corebasic.member.MemberRepository;
import me.deborah.corebasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 우리는 역할과 구현을 충실하게 분리했다. OK
    // 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다. OK
    // OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다
    // 그렇게 보이지만 사실은 아니다.
    // DIP: 주문서비스 클라이언트( OrderServiceImpl )는 DiscountPolicy 인터페이스에 의존하면서 DIP를 지킨 것 같은데?
    // 클래스 의존관계를 분석해 보자. 추상(인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있 다.
    // 추상(인터페이스) 의존: DiscountPolicy
    // 구체(구현) 클래스: FixDiscountPolicy , RateDiscountPolicy OCP: 변경하지 않고 확장할 수 있다고 했는데!
    // 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP를 위반한다.

    // 어떻게 해결?
    // 실행 시 Nullpoint Exception 발생
    // 해결 방안
    // 누군가 클라이언트인 OrderServiceImpl에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해 주어야한다.
    private DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
