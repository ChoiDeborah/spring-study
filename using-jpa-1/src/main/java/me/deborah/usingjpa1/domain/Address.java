package me.deborah.usingjpa1.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter // 엔티티에는 가급적 Setter를 사용하지 말자
        // Setter가 모두 열려있다. 변경 포인트가 너무 많아서, 유지보수가 어렵다. 나중에 리펙토링으로 Setter 제거
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
