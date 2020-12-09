package me.deborah.corebasic;

import me.deborah.corebasic.member.Grade;
import me.deborah.corebasic.member.Member;
import me.deborah.corebasic.member.MemberService;
import me.deborah.corebasic.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member :" + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
