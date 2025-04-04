package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.repository.AquariumRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class AquariumService {

    @Autowired
    AquariumRepository aquariumRepository;

    public void deleteAquarium(int id) {
        aquariumRepository.deleteById(id);
    }


    public Optional<Aquarium> getAquarium(int id) {
        return aquariumRepository.findById(id);
    }

    public Optional<List<Aquarium>> getAllAquariums() {
        return Optional.of(aquariumRepository.findAll());
    }

    public Optional<List<Aquarium>> getAllAquariumsByUserId(int userId) {
        return Optional.of(aquariumRepository.getAllByAppUserId(userId));
    }

    public void saveAquarium(Aquarium aquarium) {
        aquariumRepository.save(aquarium);
    }

    public Optional<List<Aquarium>> getAllAquariumsWithoutUser() {
        return Optional.of(aquariumRepository.getAllByAppUserIdIsNull());
    }

}
