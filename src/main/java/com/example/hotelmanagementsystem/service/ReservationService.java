package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.Reservation;
import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.entity.User;
import com.example.hotelmanagementsystem.model.ReservationDto;
import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.repository.ReservationRepository;
import com.example.hotelmanagementsystem.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UserRepository userRepository;

    public ReservationDto addReservation(ReservationDto reservationDto) throws Exception {

        String errorMessage = validateReservation(reservationDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }

        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setArrivalDate(reservationDto.getArrivalDate());
        reservation.setDepartureDate(reservationDto.getDepartureDate());
        reservation.setRoomNumber(reservationDto.getRoomNumber());
        reservation.setUserId(reservationDto.getUserId());
        reservation.setStatus(reservationDto.getStatus());

        reservation = reservationRepository.save(reservation);
        reservationDto.setId(reservation.getId());
        return reservationDto;

    }

    private String validateReservation(ReservationDto reservationDto) {
        String errorMessage = "";

        if (reservationDto.getArrivalDate() == null) {
            errorMessage = errorMessage + "Arrival Date can not be null.";
        }
        if (reservationDto.getDepartureDate() == null) {
            errorMessage = errorMessage + "Departure Date can not be null.";
        }
        if (reservationDto.getStatus() == null) {

            errorMessage = errorMessage + "Status can not be null.";
        }
        if (reservationDto.getUserId() <= 0) {

            errorMessage = errorMessage + "user id can not be 0.";
        }
        if (reservationDto.getRoomNumber() <= 0) {

            errorMessage = errorMessage + "Room Number can not be 0.";
        }
        Optional<Reservation> reservationOptional = reservationRepository.findByRoomNumber(reservationDto.getRoomNumber());

        if (reservationOptional.isPresent()) {
            errorMessage = errorMessage + " Room already there for " + reservationDto.getRoomNumber();
        }

        if (reservationDto.getUserId() > 0) {
            Optional<User> userOptional = userRepository.findById(reservationDto.getUserId());
            if (!userOptional.isPresent()) {
                errorMessage = errorMessage + "User not valid";
            }
        }

//
        return errorMessage;

    }

    public boolean updateReservation(ReservationDto reservationDto, int roomNumber) throws Exception {

        String errorMessage = UpdateValidateReservation(reservationDto);

        if (errorMessage.length() > 0) {
            System.out.println("invalid request");
            throw new Exception(errorMessage);
        }
        Optional<Reservation> reservationToUpdateOptional = reservationRepository.findByRoomNumber(roomNumber);
        if (reservationToUpdateOptional.isPresent()) {

            Reservation reservationToUpdate = reservationToUpdateOptional.get();

            reservationToUpdate.setDepartureDate(reservationDto.getDepartureDate());
            reservationToUpdate.setArrivalDate(reservationDto.getArrivalDate());
            reservationToUpdate.setStatus(reservationDto.getStatus());


            return reservationRepository.save(reservationToUpdate) != null;
        } else {
            return false;
        }
    }


    private String UpdateValidateReservation(ReservationDto reservationDto) {

        String errorMessage = "";

        if (StringUtils.isEmpty(reservationDto.getArrivalDate())) {
            errorMessage = errorMessage + "Arrival Date can not be null.";
        }
        if (reservationDto.getDepartureDate() == null) {
            errorMessage = errorMessage + "Departure Date can not be null.";
        }
        if (reservationDto.getStatus() == null) {

            errorMessage = errorMessage + "Status can not be null.";
        }


        return errorMessage;
    }


    public boolean deleteReservation(ReservationDto reservationDto, int roomNumber) throws Exception {

        Optional<Reservation> reservationToDeleteOptional = reservationRepository.findByRoomNumber(roomNumber);
        if (reservationToDeleteOptional.isPresent()) {

            Reservation reservationToDelete = reservationToDeleteOptional.get();
            reservationRepository.delete(reservationToDelete);
            return true;
        } else {
            return false;
        }
    }

    public List<Reservation> findReservation() {

        return reservationRepository.findAll();
    }

}
