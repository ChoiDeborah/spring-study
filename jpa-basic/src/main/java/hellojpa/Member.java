package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Member {

    @Id
    private Long id;

    // 런타임 JPA의 실행매커니즘 자체에 영향을 주는게 아니라 alter table 스크립트가 생성되거나 실행되는 것에 차이가 있을
    @Column(unique = true, length = 10)
    private String name;

    private int age;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
