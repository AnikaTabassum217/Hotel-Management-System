package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.entity.User;
import com.example.hotelmanagementsystem.security.DataInitializer;
import com.example.hotelmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    DataInitializer dataInitializer;



    @GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails){
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("authorities", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(model);
    }

//    @GetMapping("/init")
//    public String initData(){
//        dataInitializer.run();
//        return "DONE";
//    }

    @GetMapping(path = "/getData")
    @PreAuthorize("hasAuthority('GET_DATA')")
    public ResponseEntity<String> geData(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping(path = "/setData")
    @PreAuthorize("hasAuthority('SET_DATA')")
    public ResponseEntity<String> seData(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok("Data set");
    }
}
