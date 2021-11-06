package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.entity.User;
import com.example.hotelmanagementsystem.model.UserDto;
import com.example.hotelmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto){
        userDto = userService.registerUser(userDto);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal UserDetails userDetails,
                                     @RequestBody UserDto userDto) throws Exception {
        userDto = userService.updateUser(userDetails, userDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@AuthenticationPrincipal UserDetails userDetails,
                                     @RequestBody UserDto userDto) throws Exception {
        boolean result = userService.deleteUser(userDetails, userDto);
        return ResponseEntity.ok(result);
    }
}
