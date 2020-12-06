package me.deborah.springintro.service;

import me.deborah.springintro.domain.Member;
import me.deborah.springintro.repository.MemberRepository;
import me.deborah.springintro.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 컨테이너와 테스트를 함꼐 실행한다.
@SpringBootTest
// 디비는 기본적으로 트렌젝션 개념이 있음 커밋 안하면 반영안됨.
// 테스트를 실행할때 트렌젝션, 쿼리 실행 반영하고 끝나면 디비 롤백해줌(다음 테스트에 영향을 주지 않음)
@Transactional
class MemberServiceIntegrationTest {

    // 테스트는 제일 끝단에 하는거라 필드기반으로 많이들 쓴다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memoryMemberRepository;


    // 테스트 코드는 한글로 바꿔도 됨...!
    // 빌드 시 실제코드에 포함되지 않음.
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
       Member findMember = memberService.findOne(saveId).get();
       assertThat(member.getName()).isEqualTo(findMember.getName());
    }


    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring2");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //then
    }
}