package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public RoomDto addRoom(RoomDto roomDto) throws Exception {

        String errorMessage = validateRoom(roomDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }
        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setCapacity(roomDto.getCapacity());
        room.setFloor(roomDto.getFloor());
        room.setAvailable(roomDto.getAvailable());
        room.setPrice(roomDto.getPrice());
        room.setType(roomDto.getType());

        room = roomRepository.save(room);
        roomDto.setId(room.getId());
        return roomDto;
    }

    private String validateRoom(RoomDto roomDto) {

        String errorMessage = "";

       if(roomDto.getAvailable()== null){
           errorMessage = errorMessage + "Available Can not be null";
       }

       if(roomDto.getType()== null){
           errorMessage = errorMessage + "Type Can not be null";
       }
       if(roomDto.getRoomNumber()<= 0){

           errorMessage = errorMessage + "Room Number Can not be 0";
       }
       if(roomDto.getCapacity()<= 0){
           errorMessage = errorMessage + "Capacity Can not be 0";
       }

       if(roomDto.getPrice()<=0){
           errorMessage = errorMessage + "Price Can not be 0";
       }

       if(roomDto.getFloor()<=0){

           errorMessage = errorMessage + "Floor Can not be 0";
       }

       Optional<Room> roomOptional = roomRepository.findByRoomNumber(roomDto.getRoomNumber());

       if(roomOptional.isPresent()) {
           errorMessage = errorMessage + " Room already there for " + roomDto.getRoomNumber();
       }
        return errorMessage;
    }

    public boolean updateRoom(RoomDto roomDto, int roomNumber) throws Exception {

        String errorMessage = UpdateValidateRoom(roomDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Optional<Room> roomToUpdateOptional = roomRepository.findByRoomNumber(roomNumber);
        if (roomToUpdateOptional.isPresent()) {

            Room roomToUpdate = roomToUpdateOptional.get();

            roomToUpdate.setPrice(roomDto.getPrice());
            roomToUpdate.setCapacity(roomDto.getCapacity());
            roomToUpdate.setAvailable(roomDto.getAvailable());
            return roomRepository.save(roomToUpdate) != null;
        } else {
            return false;
        }
    }

    private String UpdateValidateRoom(RoomDto roomDto) {

        String errorMessage = "";

        if(roomDto.getCapacity()<= 0){
            errorMessage = errorMessage + "Capacity Can not be 0";
        }

        if(roomDto.getPrice()<=0){
            errorMessage = errorMessage + "Price Can not be 0";
        }




        return errorMessage;
    }

    public boolean deleteRoom(RoomDto roomDto, int roomNumber) throws Exception {

        Optional<Room> roomToDeleteOptional = roomRepository.findByRoomNumber(roomNumber);
        if (roomToDeleteOptional.isPresent()) {

            Room roomToDelete = roomToDeleteOptional.get();
            roomRepository.delete(roomToDelete);
            return true;
        } else {
            return false;
        }
    }


    public List<Room> findRoom() {

        return  roomRepository.findAll();
    }

    public List<Room> findAvailableRoom() {

        return  roomRepository.findAllByAvailable("true");
    }
}

