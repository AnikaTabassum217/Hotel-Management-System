package com.example.hotelmanagementsystem.controller;


import com.example.hotelmanagementsystem.entity.FoodOrder;
import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.model.FoodOrderDto;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodOrderController {
    @Autowired
    FoodOrderService foodOrderService;

    @PostMapping("/addFoodOrder")
    public ResponseEntity addFoodOrder(@RequestBody FoodOrderDto foodOrderDto) throws Exception {
        foodOrderDto = foodOrderService.addFoodOrder(foodOrderDto);
        return ResponseEntity.ok(foodOrderDto);
    }

    @RequestMapping(value = "/updateFoodOrder/{id}", method = RequestMethod.PUT)
    public boolean updateFoodOrder(@RequestBody FoodOrderDto foodOrderDto,
                                     @PathVariable long id) throws Exception {
        return foodOrderService.updateFoodOrder(foodOrderDto, id);
    }

    @RequestMapping(value = "/findAllFoodOrder", method = RequestMethod.GET)
    public List<FoodOrder> findFoodOrder() throws Exception {

        return foodOrderService.findFoodOrder();
    }

    @RequestMapping(value = "/deleteFoodOrder/{id}", method = RequestMethod.DELETE)
    public boolean deleteFoodOrder(@RequestBody FoodOrderDto foodOrderDto,
                                     @PathVariable long id) throws Exception {
        return foodOrderService.deleteFoodOrder(foodOrderDto, id);
    }

}
