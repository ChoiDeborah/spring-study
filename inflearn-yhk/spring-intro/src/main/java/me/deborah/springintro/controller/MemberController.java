package me.deborah.springintro.controller;

import me.deborah.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링 컨테이너에서 빈을 생성해서 관리
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired  // memberService를 스프링이 연결시켜
                // MemberService 순수 자바 클래스...
                // 마찬가지로 빈으로 등록해줘야함
                // Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
