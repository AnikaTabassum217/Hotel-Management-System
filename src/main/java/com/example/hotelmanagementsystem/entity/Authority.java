package com.example.hotelmanagementsystem.entity;
import javax.persistence.*;

/*
Fahim created at 11/16/2020
*/
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


    private String authority;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Authority( String authority, Role role) {
        this.authority = authority;
        this.role = role;
    }

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
