package com.example.hotelmanagementsystem.repository;


import com.example.hotelmanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByRoleName(String roleName);
}
