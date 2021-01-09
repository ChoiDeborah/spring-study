
## JPA 내부구조

#### 영속성 관리

##### JPA에서 가장 중요한 2가지  
- 객체와 관계형 데이터 베이스 매핑하기 (Object RelationalMapping)
- 영속성 컨텍스트

##### 엔티티 매니저 팩토리와 엔티티 매니저
웹 애플리케이션 개발 시 EntityManagerFactory를 통해서 고객의 요청이 올 때마다 EntityManager를
생성한다.  
EntityManager는 내부적으로 데이터 베이스 커넥션을 이용해 데이터 베이스를 사용하게 된다.

##### 영속성 컨텍스트
- JPA를 이해하는데 가장 중요한 용어
- "엔티티를 영구 저장하는 환경" 이라는 뜻
- EntityManager.persist(entity);
    - 엔티티를 영속성 컨텍스트라는 곳에 저장하는 것 (영속성 컨텍스트를 통해서 엔티티 라는 것을 영속한다)

##### 엔티티 매니저? 영속성 컨텍스트?
- 영속성 컨텍스트는 논리적인 개념
- 눈에 보이지 않는다.
- 엔티티 매니저를 통해서 영속성 컨텍스트에 접근

##### J2SE 환경
엔티티 매니저와 영속성 컨텍스트가 1:1

##### J2EE, 스프링 프레임워크 같은 컨테이너 환경
엔티티 매니저와 영속성 컨텍스트가 N:1

---

#### 엔티티의 생명 주기
- **비영속 (new/transient)**  
    영속성 컨텍스트와 전혀 관계가 없는 **새로운** 상태  
    
- **영속(managed)**  
    영속성 컨텍스에 **관리**되는 상태  
    
- **준영속(detached)**  
    영속컨텍스트에 저장되었다가 **분리**된 상태  
    
- **삭제(removed)**  
    **삭제**된 상태
    
---

#### 영속성 컨텍스트의 이점
- 1차 캐시
- 동일성(identity) 보장
- 트랜잭션을 지원하는 쓰기 지연(transactional write-behide)
- 변경 감지(Dirty Checking)
- 지연 로딩(Lazy Loading)


##### 엔티티 조회, 1차 캐시
```java
Member member = new Member();
member.setID("member1");
member.setUserName("회원1);

// 엔티티를 영속
em.persis(member);
```

##### 1차 캐시에서 조회
```java
Member member = new Member();
member.setID("member1");
member.setUserName("회원1);

// 1차 캐시에 저장됨
em.persis(member);

// 1차 캐시에서 조회 (DB에서 찾는 것이 아님)
Member findMember = em.find(Member.class, "member1");
```

##### 데이터베이스에서 조회
```java
// 1.find("member2") 1차 캐시에 없음
// 2. DB 조회
// 3. 1차 캐시에 저장
// 4. 반환

Member findMember2 = em.find(Member.class, "member2");
```

##### 영속 엔티티의 동일성 보장

```java
Member a = em.find(Member.class, "member1");
Member b = em.find(Member.class, "member1");

System.out.println(a == b); // 동일 성 비교 true
```

1차 캐시로 반복 가능한 읽기(Repeatable Read) 등급의 트랜잭션 격리 수준을   
데이터 베이스가 아닌 애플리케이션 차원에서 제공

##### 엔티티 등록 트랜적션을 지원하는 쓰기 지연

```java
EntityManager em = emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
// 엔티티 매니저는 데이터 변경 시 트랜잭션을 시작해야 한다.
transaction.begin(); // [트랜잭션] 시작

em.persist(memberA);
em.persist(memberB);
// 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.

// 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
transaction.commit(); // [트랜잭션] 커밋
```

##### 엔티티 수정 변경 감지

```java
EntityManager em = emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
transaction.begin(); // [트랜잭션] 시작

// 영속 엔티티 조회
Member memberA = em.find(Member.class, "memberA");

// 영속 엔티티 데이터 수정
memberA.setUserName("hi");
memberA.setAge(10);

// em.update(member) 이런 코드가 있어야하지 않을까? 필요없음

transcation.commit(); // [트랜잭션] 커밋
```

##### 변경 감지
JPA는 데이터 베이스 트렌잭션 커밋하는 시점에 무슨일이 벌어지냐?  
커밋 시 flush 호출되고, 엔티티와 스냅샷을 비교한다.(내가 값을 읽어온 최초시점을 스냅샷으로 떠둠)  
변경된 것이 있다면 update 쿼리를 쓰기전에 SQL쓰기지연 저장소에 만들어두고 업데이트 쿼리를 데이터 베이스에 반영하고 커밋하게 된다.

##### 엔티티 삭제
```java
// 삭제 대상 엔티티 조회
Member memberA = em.find(Member.class, "memberA");
em.remove(memberA); // 엔티티 삭제
```