package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.entity.ServiceName;
import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.model.ServiceNameDto;
import com.example.hotelmanagementsystem.service.ServiceNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceNameController {

    @Autowired
    ServiceNameService serviceNameService;

    @PostMapping("/addServiceName")
    public ResponseEntity addServiceName(@RequestBody ServiceNameDto serviceNameDto) throws Exception {
        serviceNameDto = serviceNameService.addServiceName(serviceNameDto);
        return ResponseEntity.ok(serviceNameDto);
    }

    @RequestMapping(value = "/updateServiceName/{id}", method = RequestMethod.PUT)
    public boolean updateServiceName(@RequestBody ServiceNameDto serviceNameDto,
                              @PathVariable long id) throws Exception {
        return serviceNameService.updateServiceName(serviceNameDto, id);
    }

    @RequestMapping(value = "/deleteServiceName/{id}", method = RequestMethod.DELETE)
    public boolean deleteServiceName(@RequestBody ServiceNameDto serviceNameDto,
                              @PathVariable long id) throws Exception {
        return serviceNameService.deleteServiceName(serviceNameDto,id);
    }

    @RequestMapping(value = "/findAllServiceName", method = RequestMethod.GET)
    public List<ServiceName> findAllServiceName() throws Exception {

        return serviceNameService.findAllServiceName();
    }

}
