package com.example.hotelmanagementsystem.repository;

import com.example.hotelmanagementsystem.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByAvailable(String available);
}
