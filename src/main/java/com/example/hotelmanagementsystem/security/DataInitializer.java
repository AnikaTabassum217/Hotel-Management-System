package com.example.hotelmanagementsystem.security;//package com.jonak.rnd.security;

import com.example.hotelmanagementsystem.entity.Authority;
import com.example.hotelmanagementsystem.entity.Role;
import com.example.hotelmanagementsystem.repository.AuthorityRepository;
import com.example.hotelmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.example.hotelmanagementsystem.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
Fahim created at 11/15/2020
*/
@Component
public class DataInitializer {
    //...
    @Autowired
    UserRepository users;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void run()  {

        Role role1 = new Role("ADMIN", "admin role");
        Role role2 = new Role("USER", "user role");
        Role role3 = new Role("DEV", "developer role");

//        role1.setId(1l);
//        role2.setId(1l);
//        role3.setId(1l);

        Authority a1 = new Authority("GET_DATA", role1);
        Authority a2 = new Authority("SET_DATA",role1);

        Authority u1 = new Authority("GET_DATA", role2);

        Authority d1 = new Authority("GET_DATA", role3);
        Authority d2 = new Authority("SET_DATA",role3);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(this.passwordEncoder.encode("password"));
        admin.setRole(role1);
        this.users.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(this.passwordEncoder.encode("password"));
        user.setRole(role2);
        this.users.save(user);

        User dev = new User();
        dev.setUsername("dev");
        dev.setPassword(this.passwordEncoder.encode("password"));
        dev.setRole(role3);
        this.users.save(dev);

        authorityRepository.save(a1);
        authorityRepository.save(a2);
        authorityRepository.save(u1);
        authorityRepository.save(d1);
        authorityRepository.save(d2);

        this.users.findAll().forEach(v ->  v.toString());
    }
}

