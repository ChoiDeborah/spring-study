package me.deborah.springintro;

import me.deborah.springintro.repository.JdbcMemberRepository;
import me.deborah.springintro.repository.MemberRepository;
import me.deborah.springintro.repository.MemoryMemberRepository;
import me.deborah.springintro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 자바 코드로 직업 빈등록
// 정형화 되지 않거나 상황에 따라 구현 클래스를 변ㄱ셩해야하면 설정을 통해 스프링 빈으로 등록한다.
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // 개방 폐쇄 원칙 OCP (Open-closed Principle)
        // 확장에는 열려있고, 수정, 변경에는 닫혀있다.
        // 인터페이스 확장 시 기존 코드를 전혀 손대지 않고, 설정만으로 구현클래스를 변경할 수 있다.
        return new JdbcMemberRepository(dataSource);
    }
}
