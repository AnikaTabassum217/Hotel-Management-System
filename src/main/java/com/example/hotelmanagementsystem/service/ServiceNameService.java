package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.ServiceName;
import com.example.hotelmanagementsystem.model.ServiceNameDto;
import com.example.hotelmanagementsystem.repository.ServiceNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceNameService {
    @Autowired
    ServiceNameRepository serviceNameRepository;

    public ServiceNameDto addServiceName(ServiceNameDto serviceNameDto) throws Exception {

        ServiceName serviceName = new ServiceName();
        serviceName.setName(serviceNameDto.getName());
        serviceName.setPrice(serviceNameDto.getPrice());

        serviceName = serviceNameRepository.save(serviceName);
        serviceNameDto.setId(serviceName.getId());
        return serviceNameDto;
    }

    public boolean updateServiceName(ServiceNameDto serviceNameDto, long id) throws Exception {

        Optional<ServiceName> serviceNameToUpdateOptional = serviceNameRepository.findById(id);
        if (serviceNameToUpdateOptional.isPresent()) {


            ServiceName serviceNameToUpdate = serviceNameToUpdateOptional.get();

            serviceNameToUpdate.setName(serviceNameDto.getName());
            serviceNameToUpdate.setPrice(serviceNameDto.getPrice());

            return serviceNameRepository.save(serviceNameToUpdate) != null;
        }
        else {
            return false;
        }

    }

    public boolean deleteServiceName(ServiceNameDto serviceNameDto, long id) throws Exception {

        Optional<ServiceName> serviceNameToDeleteOptional = serviceNameRepository.findById(id);
        if (serviceNameToDeleteOptional.isPresent()) {

            ServiceName serviceNameToDelete = serviceNameToDeleteOptional.get();
            serviceNameRepository.delete(serviceNameToDelete);
            return true;
        } else {
            return false;
        }
    }

    public List<ServiceName> findAllServiceName() {

        return  serviceNameRepository.findAll();
    }
}
