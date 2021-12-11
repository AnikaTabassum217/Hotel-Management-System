package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.DiningReservaton;
import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.entity.User;
import com.example.hotelmanagementsystem.model.DiningReservationDto;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.repository.DiningReservationRepository;
import com.example.hotelmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningReservationService {
    @Autowired
    DiningReservationRepository diningReservationRepository;
    @Autowired
    UserRepository userRepository;


    public DiningReservationDto addDiningReservation(DiningReservationDto diningReservationDto) throws Exception {

        String errorMessage = validateDiningReservation(diningReservationDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        DiningReservaton diningReservaton = new DiningReservaton();

        diningReservaton.setUserId(diningReservationDto.getUserId());
        diningReservaton.setMobileNumber(diningReservationDto.getMobileNumber());
        diningReservaton.setUserName(diningReservationDto.getUserName());
        diningReservaton.setPayment(diningReservationDto.getPayment());
        diningReservaton.setStartTime(diningReservationDto.getStartTime());
        diningReservaton.setEndTime(diningReservationDto.getEndTime());
        diningReservaton.setDiningId(diningReservationDto.getDiningId());

        diningReservaton = diningReservationRepository.save(diningReservaton);
        diningReservationDto.setId(diningReservaton.getId());
        return diningReservationDto;


    }

    private String validateDiningReservation(DiningReservationDto diningReservationDto) {
        String errorMessage = "";

        if (diningReservationDto.getStartTime() == null) {
            errorMessage = errorMessage + "Start Time can not be null.";
        }

        if (diningReservationDto.getEndTime() == null) {
            errorMessage = errorMessage + "End Time can not be null.";
        }
        if (diningReservationDto.getPayment() <= 0) {
            errorMessage = errorMessage + "Payment can not be 0.";
        }
        if (diningReservationDto.getUserId() > 0) {
            Optional<User> userOptional = userRepository.findById(diningReservationDto.getUserId());
            if (!userOptional.isPresent()) {
                errorMessage = errorMessage + "Invalid user id";
            }

        } else {
            if (diningReservationDto.getMobileNumber() == null) {
                errorMessage = errorMessage + "Mobile Number can not be null.";
            }
            if (diningReservationDto.getUserName() == null) {
                errorMessage = errorMessage + "User Name can not be null.";
            }
        }

        return errorMessage;
    }


    public boolean updateDiningReservation(DiningReservationDto diningReservationDto, long id) throws Exception {


        String errorMessage = validateDiningReservation(diningReservationDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Optional<DiningReservaton> diningReservationToUpdateOptional = diningReservationRepository.findById(id);
        if (diningReservationToUpdateOptional.isPresent()) {

            DiningReservaton diningReservationToUpdate = diningReservationToUpdateOptional.get();

            diningReservationToUpdate.setMobileNumber(diningReservationDto.getMobileNumber());
            diningReservationToUpdate.setPayment(diningReservationDto.getPayment());
            diningReservationToUpdate.setStartTime(diningReservationDto.getStartTime());
            diningReservationToUpdate.setEndTime(diningReservationDto.getEndTime());

            return diningReservationRepository.save(diningReservationToUpdate) != null;
        } else {
            return false;
        }
    }

    public boolean deleteDiningReservation(DiningReservationDto diningReservationDto, long id) throws Exception {

        Optional<DiningReservaton> diningReservationToUpdateOptional = diningReservationRepository.findById(id);
        if (diningReservationToUpdateOptional.isPresent()) {

            DiningReservaton diningReservationToDelete = diningReservationToUpdateOptional.get();
            diningReservationRepository.delete(diningReservationToDelete);
            return true;
        } else {
            return false;
        }
    }

    public List<DiningReservaton> findReservation() {

        return diningReservationRepository.findAll();
    }


}
