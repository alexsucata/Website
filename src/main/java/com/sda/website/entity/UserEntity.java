package com.sda.website.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users") //tabela trebuie sa se numeasca exact "users"
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username; //camp obligatoriu cu exact acest nume!!!
    private String password; //camp obligatoriu cu exact acest nume!!!
    private Boolean enabled; //camp obligatoriu cu exact acest nume!!!

    @OneToMany(mappedBy = "user")
    private List<AuthorityEntity> authorities;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }
}
