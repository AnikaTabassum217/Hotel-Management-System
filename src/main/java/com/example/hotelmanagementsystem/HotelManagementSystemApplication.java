package com.example.hotelmanagementsystem;

import com.example.hotelmanagementsystem.security.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class HotelManagementSystemApplication {




    public static void main(String[] args) {

        SpringApplication.run(HotelManagementSystemApplication.class, args);
    }

}
