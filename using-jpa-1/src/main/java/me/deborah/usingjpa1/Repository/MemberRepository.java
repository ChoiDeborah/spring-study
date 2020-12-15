package me.deborah.usingjpa1.Repository;

import lombok.RequiredArgsConstructor;
import me.deborah.usingjpa1.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    // 엔티티 메니저( EntityManager ) 주입  //@PersistenceUnit : 엔티티 메니터 팩토리( EntityManagerFactory ) 주입
    //@PersistenceContext   스프링 부트가 @Autowired로 인젝션 되도록 지원해줌.
    private final EntityManager em;

    //public MemberRepository(EntityManager em) {
    //    this.em = em;
    //}

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // type, pk
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // Entity 대상
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
