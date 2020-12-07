package me.deborah.springintro.service;

import me.deborah.springintro.domain.Member;
import me.deborah.springintro.repository.MemberRepository;
import me.deborah.springintro.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스 계층에 데이터를 저장하거나 변경할 때 트렌젝셔널이 필요함.
// jpa는 조인 들어올 떄 모든 데이터 변경이 트렌젝션 안에서 실행되야함.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */

    // jpa는 조인 들어올 떄 모든 데이터 변경이 트렌젝션 안에서 실행되야함
    //@Transactional 여기만 걸어줘도 됨
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        //Optional<Member> result = memberRepository.findByName(member.getName());
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
