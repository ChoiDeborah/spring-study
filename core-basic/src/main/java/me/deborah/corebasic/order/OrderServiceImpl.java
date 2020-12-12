package me.deborah.corebasic.order;

import lombok.RequiredArgsConstructor;
import me.deborah.corebasic.AppConfig;
import me.deborah.corebasic.discount.DiscountPolicy;
import me.deborah.corebasic.discount.FixDiscountPolicy;
import me.deborah.corebasic.discount.RateDiscountPolicy;
import me.deborah.corebasic.member.Member;
import me.deborah.corebasic.member.MemberRepository;
import me.deborah.corebasic.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 조회 빈이 2개 이상 - 문제 @Autowired 는 타입(Type)으로 조회한다.입으로 조회하기 때문에, 마치 다음 코드와 유사하게 동작한다. (실제로는 더 많은 기능을 제공한다.) ac.getBean(DiscountPolicy.class)
    // 스프링 빈 조회해서 학습했듯이 타입으로 조회하면 선택된 빈이 2개 이상일 때 문제가 발생한다.
    // DiscountPolicy 의 하위 타입인 FixDiscountPolicy , RateDiscountPolicy 둘다 스프링 빈으로 선언 해보자.
    // 그리고 이렇게 의존관계 자동 주입을 실행하면 NoUniqueBeanDefinitionException 오류가 발생한다.
    // 오류메시지가 친절하게도 하나의 빈을 기대했는데 fixDiscountPolicy , rateDiscountPolicy 2개가 발견되었다고 알려준다.
    // 이때 하위 타입으로 지정할 수 도 있지만, 하위 타입으로 지정하는 것은 DIP를 위배하고 유연성이 떨어진다.
    // 그리고 이름만 다르고, 완전히 똑같은 타입의 스프링 빈이 2개 있을 때 해결이 안된다.
    // 스프링 빈을 수동 등록해서 문제를 해결해도 되지만, 의존 관계 자동 주입에서 해결하는 여러 방법이 있다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
