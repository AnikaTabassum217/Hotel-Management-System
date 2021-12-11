package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.model.ServiceNameDto;
import com.example.hotelmanagementsystem.model.ServiceReservationDto;
import com.example.hotelmanagementsystem.service.ServiceReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ServiceReservationController {

    @Autowired
    ServiceReservationService serviceReservationService;

    @PostMapping("/addServiceReservation")
    public ResponseEntity addServiceReservation(@RequestBody ServiceReservationDto serviceReservationDto) throws Exception {
        serviceReservationDto = serviceReservationService.addServiceReservation(serviceReservationDto);
        return ResponseEntity.ok(serviceReservationDto);
    }

    @RequestMapping(value = "/updateServiceReservation/{id}", method = RequestMethod.PUT)
    public boolean updateServiceReservation(@RequestBody ServiceReservationDto serviceReservationDto,
                                     @PathVariable int id) throws Exception {
        return serviceReservationService.updateServiceReservation(serviceReservationDto, id);
    }
    @RequestMapping(value = "/deleteServiceReservation/{id}", method = RequestMethod.DELETE)
    public boolean deleteServiceReservation(@RequestBody ServiceReservationDto serviceReservationDto,
                                     @PathVariable long id) throws Exception {
        return serviceReservationService.deleteServiceReservation(serviceReservationDto, id);
    }

}
