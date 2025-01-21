package com.centropyge92.log4aqua.controller;


import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.service.AquariumService;
import com.centropyge92.log4aqua.service.WaterTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class WaterTestController {


    @Autowired
    WaterTestService waterTestService;

    @Autowired
    AquariumService aquariumService;

    @GetMapping("/getWaterTests/{id}")
    public Optional<List<WaterTest>> getAllTestsByUser(@PathVariable int id, @AuthenticationPrincipal User user) {
        System.out.println("FETCHING WATER TESTS");
        Aquarium currentAquarium = aquariumService.getAquarium(id).orElseThrow(() -> new RuntimeException("Aquarium not found"));

        return Optional.ofNullable(waterTestService.getWaterTestsByAquariumId(currentAquarium.getId()));
    }



}
