package me.deborah.springintro.domain;

import com.fasterxml.classmate.GenericType;

import javax.persistence.*;
import java.lang.reflect.GenericArrayType;

@Entity     //JPA가 관리하는 Entity
public class Member {

    // pk DB가 자동으로 id를 생해주는 것 identity 전략
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //@Column(name = "username")
    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
