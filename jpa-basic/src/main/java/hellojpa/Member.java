package hellojpa;

import javax.persistence.*;

@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    @Id
    @GeneratedValue(strategy = /*GenerationType.SEQUENCE*/ GenerationType.TABLE,
            generator = "MEMBER_SEQ_GENERATOR"/*"member_seq_generator"*/)

    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
