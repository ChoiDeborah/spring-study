package me.deborah.corebasic.member;

// 구현체가 하나일 때 Impl 붙이는 관례 같은 게 있음.
public class MemberServiceImpl implements MemberService {

    // 의존 관계가 인터페이스 뿐만 아니라, 구현까지 모두 의존하는 문제점이 있음.
    // MemberRepository는 인터페이스이지만, 실제 할당하는 부분이 구현체로 할당함.
    // 결국 추상화, 구체화 모두 의존함. DIP 위반.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
