package com.centropyge92.log4aqua.repository;

import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterTestRepository extends JpaRepository<WaterTest, Integer> {

    public List<WaterTest> getWaterTestsByAquariumId(int aquariumId);

}
