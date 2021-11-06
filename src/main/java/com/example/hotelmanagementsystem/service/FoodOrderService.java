package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.Food;
import com.example.hotelmanagementsystem.entity.FoodOrder;
import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.entity.User;
import com.example.hotelmanagementsystem.model.FoodOrderDto;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.repository.FoodOrderRepository;
import com.example.hotelmanagementsystem.repository.FoodRepository;
import com.example.hotelmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService {
    @Autowired
    FoodOrderRepository foodOrderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;

    public FoodOrderDto addFoodOrder(FoodOrderDto foodOrderDto) throws Exception {

        String errorMessage = validateFoodOrder(foodOrderDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        FoodOrder foodOrder = new FoodOrder();

        foodOrder.setFoodId(foodOrderDto.getFoodId());
        foodOrder.setUserId(foodOrderDto.getUserId());
        foodOrder.setMobileNumber(foodOrderDto.getMobileNumber());
        foodOrder.setUserName(foodOrderDto.getUserName());
        foodOrder.setOrderType(foodOrderDto.getOrderType());
        foodOrder.setQuantity(foodOrderDto.getQuantity());


        foodOrder = foodOrderRepository.save(foodOrder);
        foodOrderDto.setId(foodOrder.getId());
        return foodOrderDto;
    }

    private String validateFoodOrder(FoodOrderDto foodOrderDto) {

        String errorMessage = "";

        if (foodOrderDto.getFoodId() == 0) {
            errorMessage = errorMessage + "Food Id can not be 0.";
        }

        if (foodOrderDto.getQuantity() <= 0) {
            errorMessage = errorMessage + "Quantity can not be 0.";
        }

        if (foodOrderDto.getUserId() > 0) {
            Optional<User> userOptional = userRepository.findById(foodOrderDto.getUserId());
            if(!userOptional.isPresent()) {
                errorMessage = errorMessage + "Invalid user id";
            }
        } else {
            if (foodOrderDto.getUserName() == null) {
                errorMessage = errorMessage + "User Name can not be null";
            }
            if (foodOrderDto.getMobileNumber() == null) {

                errorMessage = errorMessage + "Mobile Number can not be null";
            }
        }
        if(foodOrderDto.getFoodId()>0){

            Optional<Food> foodOptional = foodRepository.findById(foodOrderDto.getFoodId());

            if(!foodOptional.isPresent()) {
                errorMessage = errorMessage + "Invalid Food id";
            }
        }

        if (foodOrderDto.getOrderType() == null) {

            errorMessage = errorMessage + "Order Type can not be null";
        }

        return errorMessage;

    }


    public boolean updateFoodOrder(FoodOrderDto foodOrderDto, long id) throws Exception {

        String errorMessage = UpdateValidateFoodOrder(foodOrderDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Optional<FoodOrder> foodOrderToUpdateOptional = foodOrderRepository.findById(id);
        if (foodOrderToUpdateOptional.isPresent()) {

            FoodOrder foodOrderToUpdate = foodOrderToUpdateOptional.get();
            foodOrderToUpdate.setFoodId(foodOrderDto.getFoodId());
            foodOrderToUpdate.setOrderType(foodOrderDto.getOrderType());
            foodOrderToUpdate.setQuantity(foodOrderDto.getQuantity());


            return foodOrderRepository.save(foodOrderToUpdate) != null;
        } else {
            return false;
        }
    }

    private String UpdateValidateFoodOrder(FoodOrderDto foodOrderDto) {

        String errorMessage = "";

        if (foodOrderDto.getFoodId() == 0) {
            errorMessage = errorMessage + "Food Id can not be 0.";
        }

        if (foodOrderDto.getQuantity() <= 0) {
            errorMessage = errorMessage + "Quantity can not be 0.";
        }


        if (foodOrderDto.getOrderType() == null) {

            errorMessage = errorMessage + "Order Type can not be null";
        }

        return errorMessage;
    }

    public List<FoodOrder> findFoodOrder() {
        return foodOrderRepository.findAll();
    }

    public boolean deleteFoodOrder(FoodOrderDto foodOrderDto, long id) throws Exception {

        Optional<FoodOrder> foodOrderToDeleteOptional = foodOrderRepository.findById(id);
        if (foodOrderToDeleteOptional.isPresent()) {

            FoodOrder foodOrderToDelete = foodOrderToDeleteOptional.get();
            foodOrderRepository.delete(foodOrderToDelete);
            return true;
        } else {
            return false;
        }
    }
}

