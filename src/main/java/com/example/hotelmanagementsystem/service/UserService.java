package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.Role;
import com.example.hotelmanagementsystem.entity.User;
import com.example.hotelmanagementsystem.model.UserDto;
import com.example.hotelmanagementsystem.repository.RoleRepository;
import com.example.hotelmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public UserDto registerUser(UserDto userDto) {
        Role role = roleRepository.findRoleByRoleName("USER");

        User register = new User();// register is a Entity object
        register.setRole(role);
        register.setUsername(userDto.getUsername());
        register.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        register.setAddress(userDto.getAddress());
        register.setEmail(userDto.getEmail());
        register.setFirstName(userDto.getFirstName());
        register.setLastName(userDto.getLastName());
        register.setNid(userDto.getNid());
        register = userRepository.save(register);
        userDto.setId(register.getId());
        return userDto;
    }


    public UserDto updateUser(UserDetails userDetails, UserDto userDto) throws Exception {
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());

        if(userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
            user.setAddress(userDto.getAddress());
            user.setEmail(userDto.getEmail());
            user = userRepository.save(user);
            userDto.setId(user.getId());
        } else {
            throw new Exception("User not found");
        }

        return userDto;
    }

    public boolean deleteUser(UserDetails userDetails, UserDto userDto) throws Exception {
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());

        if(userOptional.isPresent()) {
            User user = userOptional.get();//
            userRepository.delete(user);

        } else {
            throw new Exception("User not found");
        }

        return true;
    }


}
