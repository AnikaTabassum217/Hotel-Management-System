package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.entity.Dining;
import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.model.DiningDto;
import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.service.DiningService;
import com.example.hotelmanagementsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class DiningController {

    @Autowired
    DiningService diningService;

    @PostMapping("/addDining")
    public ResponseEntity addDining(@RequestBody DiningDto diningDto) throws Exception {
        diningDto = diningService.addDining(diningDto);
        return ResponseEntity.ok(diningDto);
    }

    @RequestMapping(value = "/updateDining/{id}", method = RequestMethod.PUT)
    public boolean updateDining(@RequestBody DiningDto diningDto,
                              @PathVariable long id) throws Exception {
        return diningService.updateDining(diningDto, id);
    }

    @RequestMapping(value = "/findAllDining", method = RequestMethod.GET)
    public List<Dining> findDining() throws Exception {

        return diningService.findDining();
    }

    @RequestMapping(value = "/findAvailableDining", method = RequestMethod.GET)
    public List<Dining> findAvailableDining() throws Exception {

        return diningService.findAvailableDining();
    }

    @RequestMapping(value = "/deleteDining/{id}", method = RequestMethod.DELETE)
    public boolean deleteDining(
                              @PathVariable long id) throws Exception {
        return diningService.deleteDining(id);
    }

}
