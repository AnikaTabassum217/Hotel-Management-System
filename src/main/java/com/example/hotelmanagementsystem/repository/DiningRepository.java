package com.example.hotelmanagementsystem.repository;

import com.example.hotelmanagementsystem.entity.Dining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiningRepository extends JpaRepository<Dining, Long> {



    List<Dining> findAllByAvailable(String available);
}
