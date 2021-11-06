package com.example.hotelmanagementsystem.controller;
import com.example.hotelmanagementsystem.config.JwtTokenProvider;
import com.example.hotelmanagementsystem.model.AuthenticationRequest;
import com.example.hotelmanagementsystem.repository.UserRepository;
import com.example.hotelmanagementsystem.security.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.hotelmanagementsystem.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Fahim created at 11/15/2020
*/
@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")//
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));

            Optional<User> user = userRepository.findByUsername(username);

            List<String> authorities = user.get().getRole().getAuthorities().stream()
                    .map(a -> a.getAuthority())
                    .map(String::new).collect(Collectors.toList());

            String token = jwtTokenProvider.createToken(username, authorities);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


}
