package com.example.hotelmanagementsystem.repository;

import com.example.hotelmanagementsystem.entity.FoodOrder;
import com.example.hotelmanagementsystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {

    Optional<FoodOrder> findByUserId (int userId);

}
