package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name", updatable = false)
    private String username;    // 객체는 username

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient  // 메모리에서만 쓰고싶다 디비에서는 안쓰겠다
    private int temp;

    public Member() {
    }
}
