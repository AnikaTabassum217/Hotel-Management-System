package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping("/addreservation")
    public ResponseEntity addReservation(@RequestBody ReservationDto reservationDto) throws Exception {
        reservationDto = reservationService.addReservation(reservationDto);
        return ResponseEntity.ok(reservationDto);
    }

    @RequestMapping(value = "/updatereservation/{roomNumber}", method = RequestMethod.PUT)
    public boolean updateReservation(@RequestBody ReservationDto reservationDto,
                              @PathVariable int roomNumber) throws Exception {
        return reservationService.updateReservation(reservationDto, roomNumber);
    }

    @RequestMapping(value = "/deletereservation/{roomNumber}", method = RequestMethod.DELETE)
    public boolean deleteReservation(@RequestBody ReservationDto reservationDto,
                              @PathVariable int roomNumber) throws Exception {
        return reservationService.deleteReservation(reservationDto, roomNumber);
    }
    @RequestMapping(value = "/findallreservation", method = RequestMethod.GET)
    public List<Reservation> findReservation() throws Exception {

        return reservationService.findReservation();
    }
}
