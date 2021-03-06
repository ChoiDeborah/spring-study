package me.deborah.corebasic.member;

import me.deborah.corebasic.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// 구현체가 하나일 때 Impl 붙이는 관례 같은 게 있음.


// 컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한 다. @Component 를 붙여주자.
// > 참고: @Configuration 이 컴포넌트 스캔의 대상이 된 이유도 @Configuration 소스코드를 열어보면 @Component 애노테이션이 붙어있기 때문이다.
// 이제 각 클래스가 컴포넌트 스캔의 대상이 되도록 @Component 애노테이션을 붙여주자.

@Component
public class MemberServiceImpl implements MemberService {

    // 의존 관계가 인터페이스 뿐만 아니라, 구현까지 모두 의존하는 문제점이 있음.
    // MemberRepository는 인터페이스이지만, 실제 할당하는 부분이 구현체로 할당함.
    // 결국 추상화, 구체화 모두 의존함. DIP 위반.

    // 해결방안
    // 이 문제를 해결하려면 누군가가 클라이언트인 MemberServiceImpl 에 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해주어야 한다.

    // 설계 변경으로 MemberServiceImpl 은 MemoryMemberRepository 를 의존하지 않는다! 단지 MemberRepository 인터페이스만 의존한다.
    // MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    // MemberServiceImpl 의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정 된다.
    // MemberServiceImpl 은 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.

    private final MemberRepository memberRepository;

    @Autowired  // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
