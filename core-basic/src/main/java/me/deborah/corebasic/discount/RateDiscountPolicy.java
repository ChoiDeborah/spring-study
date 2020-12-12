package me.deborah.corebasic.discount;

import me.deborah.corebasic.annotation.MainDiscountPolicy;
import me.deborah.corebasic.member.Grade;
import me.deborah.corebasic.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// 아래의 문제는 컴파일 타임에 문자열을 알수 없기 때문에 오타 나거나 했을 때 인지하기가 어렵다.
//@Qualifier("mainDiscountPolicy")    // 빈 등록시 @Qualifier를 붙여 준다.
@MainDiscountPolicy
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }

    }
}
