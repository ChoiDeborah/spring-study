package me.deborah.springintro.service;

import me.deborah.springintro.domain.Member;
import me.deborah.springintro.repository.MemberRepository;
import me.deborah.springintro.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */

    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        //Optional<Member> result = memberRepository.findByName(member.getName());

        // Optinal로 감싸서 주기 땜에 이런식으로 처리가능
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
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
        return memberRepository.findALl();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
