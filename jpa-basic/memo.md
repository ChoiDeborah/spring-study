
## JPA 내부구조

### 영속성 관리

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
--- 

##### 플러시
영속성 컨텍스트의 변경 내용을 데이터베이스에 반영

##### 플러시 발생
- 변경 감지 (Dirty Checking)
- 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
- 쓰기 지연 SQL 저장소의 쿼리를 데이터 베이스에 전송(등록, 수정, 삭제 쿼리)

##### 영속성 컨텍스트를 플러시하는 방법
- em.flush() - 직접 호출
- 트랜잭션 커밋 - 플리시 자동 호출
- JPQL 쿼리 실행 - 플러시 자동 호출

##### JPQL 쿼리 실행 시 플러시가 자동으로 호출되는 이유
```java
em.persist(memberA);
em.persist(memberB);
em.persist(memberC);

// 중간에 JPQL 실행
query = em.createQuery("select m from Member m", Member.class);
List<Member> members = query.getResultList();
```

##### 플러시 모드 옵션
```java
em.setFlushMode(FlushModeType.COMMIT)
```
- FlushModeType.AUTO  
    커밋이나 쿼리를 실행할 때 플리시(기본값)
- FlushModeType.COMMIT  
    커밋할 때만 플러시  
    
##### 플러시는!
- 영속성 컨텍스트를 비우지 않음
- 영속성 컨텍스트의 변경 내용을 데이터 베이스에 동기화
- 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화하면 됨    

---
##### 준영속 상태
- 영속(1차 캐시에 올려진 상태) -> 준영속  
- 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
- 영속성 컨텍스트가 제공하는 기능을 사용 못함

##### 준영속 상태로 만드는 방법
- em.detach(entity)  
    특정 엔티티에만 준영속 상태로 전환
- em.clear()  
    영속성 컨텍스트를 완전히 초기화
- em.close()  
    영속성 컨텍스트를 종료
    
---
### 엔티티 매핑

#### 엔티티 매핑 소개
- 객체와 태이블 매핑 : @Entity, @Table
- 필드와 컬럼 매핑: @Column
- 기본 키 매핑 : @Id
- 연관관계 매핑 : @ManyToOne, @JoinColumn

#### 객체와 테이블 매핑

##### @Entity
- @Entity가 붙은 클래스는 JPA가 관리, 엔티티라 한다.
- JPA를 사용해서 테이블과 매핑할 클래스는 **@Entity** 필수
- **주의**
    - **기본 생성자 필수**(파라미터가 없는 public 또는 protected 생성자)
    - final 클래스, enum, interface, inner 클래스 사용 X
    - 저장 필드에 final 사용 X

##### @Entity 속성 정리
- 속성 : name
    - JPA에서 사용할 엔티티 이름을 지정한다
    - 기본값 : 클래스 이름을 그대로 사용(예 : Member)
    - 같은 클래스 이름이 없으면 가급적 기본값을 사용한다
    
---
#### 데이터베이스 스키마 자동 생성
- DDL을 애플리케이션 실행 시점에 자동 생성
- 테이블 중심 -> 객체중심
- 데이터베이스 방언을 활용해서 데이터베이스에 맞는 적절한 DDL 생성
- 이렇게 **생성된 DDL은 개발 장비에서만 사용**
- 생성된 DDL은 운영서버에서는 사용하지 않거나, 적절히 다듬은 후 사용

#### 데이터베이스 스키마 자동 생성 - 속성
```xml
 <property name="hibernate.hbm2ddl.auto" value="create"/>
```

|옵션 | 설명 |
|:--------------|:-------------------------------------:|
|create         |   기본 테이블 삭제 후 다시 생성(DROP + CREATE)|  
|create-drop    |       create와 같으나 종료 시점에 테이블 DROP|
|update         |         변경분만 반영(운영 DB에는 사용하면 안됨)|
|validate       |         엔티티와 테이블이 정상 매핑되었는지만 확인|
|none           |                              사용하지 않음|

#### 데이터베이스 스키마 자동 생성 - 실습
- 스키마 자동 생성하기 설정
- 스키마 자동 생성하기 실행, 옵션별 확인
- 데이터베이스 방언 별로 달라지는 것 확인(varchar)
```xml
<property name="hibernate.dialect" value="org.hibernate.dialect.Oracle12cDialect"/>
```

#### 데이터베이스 스키마 자동 생성 - 주의
- `운영 장비에는 절대 create, create-drop, update 사용하면 안된다`
- 개발 초기 단계에는 create 또는 update
- 테스트 서버는 update 또는 validate
- 스테이징과 운영 서버는 validate 또는 none 

#### DDL 생성 기능
- 제약조건 추가 : 회원 이름은 **필수**, 10자 초과 X
    - **@Column(nullable = false, length =10)**
- 유니크 제약조건 추가
    - @Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames={"NAME","AGE"})})
- DDL 생성 기능은 DDL을 자동 생성할 때만 사용되고  JPA의 실행 로직에는 영향을 주지 않는다.