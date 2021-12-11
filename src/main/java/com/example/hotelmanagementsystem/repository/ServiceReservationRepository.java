package com.example.hotelmanagementsystem.repository;

import com.example.hotelmanagementsystem.entity.ServiceReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceReservationRepository extends JpaRepository<ServiceReservation, Long> {



}