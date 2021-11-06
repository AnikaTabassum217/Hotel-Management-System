package com.example.hotelmanagementsystem.repository;


import com.example.hotelmanagementsystem.entity.Room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(int roomNumber);
    List<Room> findAllByAvailable(String available);

}
