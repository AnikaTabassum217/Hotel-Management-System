package com.example.hotelmanagementsystem.controller;

import com.example.hotelmanagementsystem.entity.Food;
import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.model.FoodDto;
import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping("/addfood")
    public ResponseEntity addFoom(@RequestBody FoodDto foodDto) throws Exception {
        foodDto = foodService.addFood(foodDto);
        return ResponseEntity.ok(foodDto);
    }

    @RequestMapping(value = "/updateFood/{id}", method = RequestMethod.PUT)
    public boolean updateFood(@RequestBody FoodDto foodDto,
                              @PathVariable long id) throws Exception {
        return foodService.updateFood(foodDto, id);
    }

    @RequestMapping(value = "/findAllFood", method = RequestMethod.GET)
    public List<Food> findFood() throws Exception {

        return foodService.findFood();
    }

    @RequestMapping(value = "/findAvailableFood", method = RequestMethod.GET)
    public List<Food> findAvailableFood() throws Exception {

        return foodService.findAvailableFood();
    }

    @RequestMapping(value = "/deleteFood/{id}", method = RequestMethod.DELETE)
    public boolean deleteFood(@RequestBody FoodDto foodDto,
                              @PathVariable long id) throws Exception {
        return foodService.deleteFood(foodDto, id);
    }
}
