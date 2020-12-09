package me.deborah.corebasic.discount;

import me.deborah.corebasic.member.Member;

public interface DiscountPolicy {

    /*
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
