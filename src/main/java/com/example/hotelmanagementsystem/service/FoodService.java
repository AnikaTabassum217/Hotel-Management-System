package com.example.hotelmanagementsystem.service;


import com.example.hotelmanagementsystem.entity.Food;
import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.model.FoodDto;

import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;

    public FoodDto addFood(FoodDto foodDto) throws Exception {

        String errorMessage = validateFood(foodDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Food food = new Food();

        food.setFoodName(foodDto.getFoodName());
        food.setQuantity(foodDto.getQuantity());
        food.setPrice(foodDto.getPrice());
        food.setAvailable(foodDto.getAvailable());

        food = foodRepository.save(food);
        foodDto.setId(food.getId());
        return foodDto;

    }
    private String validateFood(FoodDto foodDto) {

        String errorMessage = "";

        if (foodDto.getFoodName() == null) {
            errorMessage = errorMessage + "Food Name Can not be null";
        }

        if (foodDto.getPrice() <= 0) {
            errorMessage = errorMessage + "Price Can not be 0";
        }

        if (foodDto.getQuantity() < 0) {

            errorMessage = errorMessage + "Quantityan not be less than 0";
        }

        if (foodDto.getAvailable() == null) {
            errorMessage = errorMessage + "Available Can not be null";
        }

        return errorMessage;
    }

    public boolean updateFood(FoodDto foodDto, long id) throws Exception {

        String errorMessage = validateFood(foodDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Optional<Food> foodToUpdateOptional = foodRepository.findById(id);
        if (foodToUpdateOptional.isPresent()) {

            Food foodToUpdate = foodToUpdateOptional.get();
            foodToUpdate.setQuantity(foodDto.getQuantity());
            foodToUpdate.setPrice(foodDto.getPrice());
            foodToUpdate.setAvailable(foodDto.getAvailable());

            return foodRepository.save(foodToUpdate) != null;
        } else {
            return false;
        }
    }





    public List<Food> findFood() {

        return  foodRepository.findAll();
    }


    public List<Food> findAvailableFood() {
        return  foodRepository.findAllByAvailable("true");
    }

    public boolean deleteFood(FoodDto foodDto, long id) throws Exception {

        Optional<Food> foodToDeleteOptional = foodRepository.findById(id);
        if (foodToDeleteOptional.isPresent()) {

            Food foodToDelete = foodToDeleteOptional.get();
            foodRepository.delete(foodToDelete);
            return true;
        } else {
            return false;
        }
    }
}
