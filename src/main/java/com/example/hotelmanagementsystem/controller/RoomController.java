package com.example.hotelmanagementsystem.controller;


import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping("/addroom")
    public ResponseEntity addRoom(@RequestBody RoomDto roomDto) throws Exception {
        roomDto = roomService.addRoom(roomDto);
        return ResponseEntity.ok(roomDto);
    }

    @RequestMapping(value = "/updateroom/{roomNumber}", method = RequestMethod.PUT)
    public boolean updateRoom(@RequestBody RoomDto roomDto,
                              @PathVariable int roomNumber) throws Exception {
        return roomService.updateRoom(roomDto, roomNumber);
    }

    @RequestMapping(value = "/deleteroom/{roomNumber}", method = RequestMethod.DELETE)
    public boolean deleteRoom(@RequestBody RoomDto roomDto,
                              @PathVariable int roomNumber) throws Exception {
        return roomService.deleteRoom(roomDto, roomNumber);
    }

    @RequestMapping(value = "/findallroom", method = RequestMethod.GET)
    public List<Room> findRoom() throws Exception {

        return roomService.findRoom();
    }

    @RequestMapping(value = "/findavailableroom", method = RequestMethod.GET)
    public List<Room> findAvailableRoom() throws Exception {

        return roomService.findAvailableRoom();
    }


}
