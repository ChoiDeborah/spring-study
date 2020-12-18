package me.deborah.core.resource_and_validation.validattion_abstraction;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Event {
    private Integer id;
    @NotEmpty
    private String title;

    //@Size 는 콜렉션의 사이즈를 검증하는 어노테이션
    @Min(0)
    private Integer limit;

    @Email
    private String email;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
