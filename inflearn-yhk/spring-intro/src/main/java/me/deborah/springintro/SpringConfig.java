package me.deborah.springintro;

import me.deborah.springintro.repository.MemberRepository;
import me.deborah.springintro.repository.MemoryMemberRepository;
import me.deborah.springintro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직업 빈등록
// 정형화 되지 않거나 상황에 따라 구현 클래스를 변ㄱ셩해야하면 설정을 통해 스프링 빈으로 등록한다.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
