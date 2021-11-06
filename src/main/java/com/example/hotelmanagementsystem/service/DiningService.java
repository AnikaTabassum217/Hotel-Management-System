package com.example.hotelmanagementsystem.service;

import com.example.hotelmanagementsystem.entity.Dining;
import com.example.hotelmanagementsystem.entity.Room;
import com.example.hotelmanagementsystem.model.DiningDto;
import com.example.hotelmanagementsystem.model.RoomDto;
import com.example.hotelmanagementsystem.repository.DiningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningService {
    @Autowired
    DiningRepository diningRepository;

    public DiningDto addDining(DiningDto diningDto) throws Exception {

        Dining dining = new Dining();
        dining.setSize(diningDto.getSize());
        dining.setAvailable(diningDto.getAvailable());
        dining.setStatus(diningDto.getStatus());

        dining = diningRepository.save(dining);
        diningDto.setId(dining.getId());
        return diningDto;

    }

    public boolean updateDining(DiningDto diningDto, long id) throws Exception {

        Optional<Dining> diningToUpdateOptional = diningRepository.findById(id);
        if (diningToUpdateOptional.isPresent()) {

            Dining diningToUpdate = diningToUpdateOptional.get();

            diningToUpdate.setStatus(diningDto.getStatus());
            diningToUpdate.setSize(diningDto.getSize());
            diningToUpdate.setAvailable(diningDto.getAvailable());

            return diningRepository.save(diningToUpdate) != null;
        } else {
            return false;
        }
    }

    public List<Dining> findDining() {

        return diningRepository.findAll();
    }

    public List<Dining> findAvailableDining() {

        return diningRepository.findAllByAvailable("true");
    }

    public boolean deleteDining(long id) throws Exception {

        Optional<Dining> diningToDeleteOptional = diningRepository.findById(id);

        if (diningToDeleteOptional.isPresent()) {

            Dining diningToDelete = diningToDeleteOptional.get();
            diningRepository.delete(diningToDelete);
            return true;
        } else {
            return false;
        }
    }


}





