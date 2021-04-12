package com.sda.website.entity;


import javax.persistence.*;

@Entity
@Table(name = "authorities") //exact acest nume
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorityId;
    private String username; //camp obligatoriu exact cu acest nume
    private String authority; //camp obligatoriu exact cu acest nume

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
