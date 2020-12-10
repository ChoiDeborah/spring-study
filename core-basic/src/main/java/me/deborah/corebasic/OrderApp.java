package me.deborah.corebasic;

import me.deborah.corebasic.member.Grade;
import me.deborah.corebasic.member.Member;
import me.deborah.corebasic.member.MemberService;
import me.deborah.corebasic.member.MemberServiceImpl;
import me.deborah.corebasic.order.Order;
import me.deborah.corebasic.order.OrderService;
import me.deborah.corebasic.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;

        Member memberA = new Member(memberId, "MemberA", Grade.VIP);
        memberService.join(memberA);

        Order order = orderService.createOrder(memberId, "item", 10000);

        System.out.println("order = " + order.toString());
        System.out.println("order.calculatePrice= " + order.calculatePrice());
    }
}