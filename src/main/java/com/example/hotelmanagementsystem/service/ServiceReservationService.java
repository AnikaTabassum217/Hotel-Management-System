package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.ServiceReservation;
import com.example.hotelmanagementsystem.model.ServiceReservationDto;
import com.example.hotelmanagementsystem.repository.ServiceReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceReservationService {

    @Autowired
    ServiceReservationRepository serviceReservationRepository;

    public ServiceReservationDto addServiceReservation(ServiceReservationDto serviceReservationDto) throws Exception {

        ServiceReservation serviceReservation = new ServiceReservation();

        serviceReservation.setServiceId(serviceReservationDto.getServiceId());
        serviceReservation.setUserId(serviceReservationDto.getUserId());
        serviceReservation.setDescription(serviceReservationDto.getDescription());

        serviceReservation = serviceReservationRepository.save(serviceReservation);
        serviceReservationDto.setId(serviceReservation.getId());
        return serviceReservationDto;

    }

    public boolean updateServiceReservation(ServiceReservationDto serviceReservationDto, long id) throws Exception {
        Optional<ServiceReservation> serviceReservationToUpdateOptional = serviceReservationRepository.findById(id);
        if (serviceReservationToUpdateOptional.isPresent()) {

            ServiceReservation serviceToUpdate = serviceReservationToUpdateOptional.get();

            serviceToUpdate.setDescription(serviceReservationDto.getDescription());

            return serviceReservationRepository.save(serviceToUpdate) != null;
        } else {
            return false;
        }
    }

    public boolean deleteServiceReservation(ServiceReservationDto serviceReservationDto, long id) throws Exception {

        Optional<ServiceReservation> serviceReservationToDeleteOptional = serviceReservationRepository.findById(id);
        if (serviceReservationToDeleteOptional.isPresent()) {

            ServiceReservation servicereservationToDelete = serviceReservationToDeleteOptional.get();
            serviceReservationRepository.delete(servicereservationToDelete);
            return true;
        } else {
            return false;
        }
    }
}

