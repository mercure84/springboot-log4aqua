package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.centropyge92.log4aqua.repository.WaterTestRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class WaterTestService {

    @Autowired
    WaterTestRepository waterTestRepository;

    public List<WaterTest> getWaterTestsByAquariumIdOrderByDateDesc(int id) {
        return waterTestRepository.getWaterTestsByAquariumIdOrderByDateDesc(id);

    }

    public void addWaterTest(WaterTest waterTest) {
        waterTestRepository.save(waterTest);
    }


    public Optional<Object> getWaterTest(int id) {
        return Optional.of(waterTestRepository.getWaterTestById(id));
    }

    public void deleteWaterTest(WaterTest waterTest) {
        waterTestRepository.delete(waterTest);
    }
}
