package me.deborah.springintro.controller;

import me.deborah.springintro.domain.Member;
import me.deborah.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링 컨테이너에서 빈을 생성해서 관리
@Controller
public class MemberController {

    private MemberService memberService;

    // 필드 주입 (중간에 런타임에 바꿀수가 없음...)
    //@Autowired private MemberService memberService;

    // 세터 주입 setter injection : 퍼블릭으로 노출되어있어 의도치 않게 바뀌는 상황이 발생할수 있음.
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 생성자 주입 (권장)
    @Autowired  // memberService를 스프링이 연결시켜
    // MemberService 순수 자바 클래스...
    // 마찬가지로 빈으로 등록해줘야함
    // Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
