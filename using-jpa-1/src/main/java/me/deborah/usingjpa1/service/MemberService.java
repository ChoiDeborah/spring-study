package me.deborah.usingjpa1.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.deborah.usingjpa1.Repository.MemberRepository;
import me.deborah.usingjpa1.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}


    /**
     * 회원 가입
     */
    @Transactional  // 쓰기 readOnly default false임
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복회원 검
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
