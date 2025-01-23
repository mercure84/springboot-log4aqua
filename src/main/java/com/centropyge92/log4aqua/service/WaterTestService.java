package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.centropyge92.log4aqua.repository.WaterTestRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class WaterTestService {

    @Autowired
    WaterTestRepository waterTestRepository;

    public List<WaterTest> getWaterTestsByAquariumId(int id) {
        return waterTestRepository.getWaterTestsByAquariumId(id);

    }

    public void addWaterTest(WaterTest waterTest) {
        waterTestRepository.save(waterTest);
    }


}
