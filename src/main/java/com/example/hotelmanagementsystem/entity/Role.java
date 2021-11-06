package com.example.hotelmanagementsystem.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Fahim created at 11/16/2020
*/
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


    private String roleName;

    private String description;

    @OneToMany(mappedBy="role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authority> authorities;

    public Role( String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
