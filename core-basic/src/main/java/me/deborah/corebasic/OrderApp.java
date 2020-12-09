package me.deborah.corebasic;

import me.deborah.corebasic.member.Grade;
import me.deborah.corebasic.member.Member;
import me.deborah.corebasic.member.MemberService;
import me.deborah.corebasic.member.MemberServiceImpl;
import me.deborah.corebasic.order.Order;
import me.deborah.corebasic.order.OrderService;
import me.deborah.corebasic.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;

        Member memberA = new Member(memberId, "MemberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "item", 10000);

        System.out.println("order = " + order.toString());
        System.out.println("order.calculatePrice= " + order.calculatePrice());
    }
}
