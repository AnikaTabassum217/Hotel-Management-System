package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.entity.DiningReservaton;
import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.model.DiningReservationDto;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.service.DiningReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiningReservationController {
    @Autowired
    DiningReservationService diningReservationService;

    @PostMapping("/addDiningReservation")
    public ResponseEntity addDiningReservation(@RequestBody DiningReservationDto diningReservationDto) throws Exception {
        diningReservationDto = diningReservationService.addDiningReservation(diningReservationDto);
        return ResponseEntity.ok(diningReservationDto);
    }

    @RequestMapping(value = "/updateDiningReservation/{id}", method = RequestMethod.PUT)
    public boolean updateDiningReservation(@RequestBody DiningReservationDto diningReservationDto,
                                     @PathVariable long id) throws Exception {
        return diningReservationService.updateDiningReservation(diningReservationDto, id);
    }

    @RequestMapping(value = "/deleteDiningReservation/{id}", method = RequestMethod.DELETE)
    public boolean deleteDiningReservation(@RequestBody DiningReservationDto diningReservationDto,
                                     @PathVariable long id) throws Exception {
        return diningReservationService.deleteDiningReservation(diningReservationDto, id);
    }

    @RequestMapping(value = "/findAllDiningReservation", method = RequestMethod.GET)
    public List<DiningReservaton> findReservation() throws Exception {

        return diningReservationService.findReservation();
    }


}
