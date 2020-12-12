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
@RequiredArgsConstructor    // final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다!!
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

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
