package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 어플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야한다)
        EntityManager em = emf.createEntityManager();
        // JPA의 모든 데이터 변경은 트랜잭션안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // JPQL
            // JAP를 사용하면 엔티티 객체를 중심으로 개발
            // 문제는 검색 쿼리
            // 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색
            // 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
            // 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요

            // JAP는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
            // SQL과 문법 유사, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원

            // 차이?
            // JPQL은 엔티티 객체를 대상으로 쿼리
            // SQL은 데이터베이스 테이블을 대상으로 쿼리

            // 장점?
            // 테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
            // SQL을 추상화 해서 특정 데이터베이스 SQL에 의존 X

//            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

//            // 영속 객체를 저장한 상태 영속(엔티티 매니저 안의 영속성 컨텍스트를 통해서 맴버가 관리됨)
//            System.out.println("===== BEFORE =====");
//            em.persist(member); // 1차 캐시에 저장
//            System.out.println("===== AFTER =====");
//
//            Member findMember = em.find(Member.class, 101L);    // 1차 캐시에 있는것을 반환
//            System.out.println("findMember  = " + findMember);
//
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

//            Member findMember1 = em.find(Member.class, 101L);   // 첫번 째 조회시 쿼리로 디비에서 조회 후 영속성 컨텍스트에 올린다(1차 캐시)
//            Member findMember2 = em.find(Member.class, 101L);   // 두번째 부터는 1차 캐시에서 가져온다.
//
//            System.out.println("result = " + (findMember1 == findMember2));

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(150L, "B");
//
//            em.persist(member1);
//            em.persist(member2);

            Member member = em.find(Member.class, 101L);
            member.setName("ZZZZ");
            //  em.persist(member); 필요 없

            System.out.println("========================");

            tx.commit();
        } catch (Exception e) {
            tx.commit();
        } finally {
            em.close();
        }

        emf.close();
    }
}
