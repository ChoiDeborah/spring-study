package me.deborah.springintro.repository;

import me.deborah.springintro.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // jpa 는 EntityManager로 동작 함.
    // spring boot 가 DB랑 연결해서 EntityManager 만들어 줌
    // 만들어진 걸 injection 받으면 됨.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // jpa가 인서트 쿼리만들어서 db에 집어넣고 아이디 까지 셋해줌.
        return member;      // 리턴값이 없어서 그냥 스펙 맞춰준 것임.
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // pk는 이렇게 조회가능.
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        // PK기반이 아닌 얘들은 JPQL라는 객체지향 언어를 사용해야 함.
        // Member 를 대상으로 쿼리를 보냄.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }
}
