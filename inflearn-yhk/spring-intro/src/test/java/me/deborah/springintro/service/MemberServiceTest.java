package me.deborah.springintro.service;

import me.deborah.springintro.domain.Member;
import me.deborah.springintro.repository.MemberRepository;
import me.deborah.springintro.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    // 각 테스트 전에 실행
    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        // 외부에서 넣어주도록
        // DI -> Defendency Injection
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        // 테스트 돌때마다 DB값 날리도록
        memoryMemberRepository.clearStore();
    }

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
        member2.setName("spring");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1111");
//        }
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}