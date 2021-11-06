package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.DiningReservaton;
import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.model.DiningReservationDto;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.repository.DiningReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningReservationService {
    @Autowired
    DiningReservationRepository diningReservationRepository;


    public DiningReservationDto addDiningReservation(DiningReservationDto diningReservationDto) throws Exception {

        DiningReservaton diningReservaton = new DiningReservaton();

        diningReservaton.setUserId(diningReservationDto.getUserId());
        diningReservaton.setMobileNumber(diningReservationDto.getMobileNumber());
        diningReservaton.setUserName(diningReservationDto.getUserName());
        diningReservaton.setPayment(diningReservationDto.getPayment());
        diningReservaton.setStartTime(diningReservationDto.getStartTime());
        diningReservaton.setEndTime(diningReservationDto.getEndTime());

        diningReservaton = diningReservationRepository.save(diningReservaton);
        diningReservationDto.setId(diningReservaton.getId());
        return diningReservationDto;


    }

    public boolean updateDiningReservation(DiningReservationDto diningReservationDto, long id) throws Exception {


        Optional<DiningReservaton> diningReservationToUpdateOptional = diningReservationRepository.findById(id);
        if (diningReservationToUpdateOptional.isPresent()) {

            DiningReservaton diningReservationToUpdate= diningReservationToUpdateOptional.get();

            diningReservationToUpdate.setMobileNumber(diningReservationDto.getMobileNumber());
            diningReservationToUpdate.setPayment(diningReservationDto.getPayment());
            diningReservationToUpdate.setStartTime(diningReservationDto.getStartTime());
            diningReservationToUpdate.setEndTime(diningReservationDto.getEndTime());

            return diningReservationRepository.save(diningReservationToUpdate) != null;
        } else {
            return false;
        }
    }

    public boolean deleteDiningReservation(DiningReservationDto diningReservationDto) throws Exception {

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
