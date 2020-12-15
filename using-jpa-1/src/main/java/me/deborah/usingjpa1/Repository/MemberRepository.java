package me.deborah.usingjpa1.Repository;

import me.deborah.usingjpa1.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    // 엔티티 메니저( EntityManager ) 주입  //@PersistenceUnit : 엔티티 메니터 팩토리( EntityManagerFactory ) 주입
    @PersistenceContext
    private EntityManager em;

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
