package me.deborah.usingjpa1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    // order table에 있는 member필드에 의해서 매핑된거다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
