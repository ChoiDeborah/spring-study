package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            // 생성
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("mozzii");
//            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, 1L);
            //System.out.println("findMember Name = " + findMember.getName());

            // 수정
            findMember.setName("zzimo");

            // 삭제
            //em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.commit();
        } finally {
            em.close();
        }

        emf.close();
    }
}
