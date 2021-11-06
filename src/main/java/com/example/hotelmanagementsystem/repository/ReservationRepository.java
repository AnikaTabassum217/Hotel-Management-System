package com.example.hotelmanagementsystem.repository;

import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.entity.Room;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends  JpaRepository<Reservation, Long> {

    Optional<Reservation> findByRoomNumber(int roomNumber);
    Optional<Reservation> findByUserId (int userId);
}
