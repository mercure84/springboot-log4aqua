package com.centropyge92.log4aqua.controller;


import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.service.AppUserService;
import com.centropyge92.log4aqua.service.AquariumService;
import com.centropyge92.log4aqua.service.WaterTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WaterTestController {


    @Autowired
    WaterTestService waterTestService;

    @Autowired
    AquariumService aquariumService;

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getWaterTests/{id}")
    public ResponseEntity<List<WaterTest>> getAllTestsByUser(@PathVariable int id) {
        System.out.println("FETCHING WATER TESTS");

        Optional<Aquarium> currentAquarium = aquariumService.getAquarium(id);
        if (currentAquarium.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<WaterTest> tests = waterTestService.getWaterTestsByAquariumIdOrderByDateDesc(currentAquarium.get().getId());
        return ResponseEntity.ok(tests);
    }


    @PostMapping("/addWaterTest/{id}")
    public void addWaterTest(@PathVariable int id, @RequestBody WaterTest waterTest, @AuthenticationPrincipal User user) {
        System.out.println("ADDING WATER TEST");
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Aquarium> allAquariumsByUserId = aquariumService.getAllAquariumsByUserId(appUser.getId()).orElseThrow(() -> new RuntimeException("Aquarium not found"));
        Aquarium currentAquarium = aquariumService.getAquarium(id).orElseThrow(() -> new RuntimeException("Aquarium not found"));
        if (allAquariumsByUserId.stream().noneMatch(aquarium -> aquarium.getId() == id)) {
            throw new RuntimeException("Aquarium not found for this user");
        } else {
            waterTest.setAquarium(currentAquarium);
            System.out.println("REGISTERING a NEW TEST ==> "+ waterTest);
            waterTestService.addWaterTest(waterTest);
        }
    }

    @DeleteMapping("/deleteWaterTest/{id}")
    public void deleteTest(@PathVariable int id, @AuthenticationPrincipal User user) {
        System.out.println("DELETING WATER TEST");
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        // find the test by the iD
        WaterTest testTodelete = (WaterTest) waterTestService.getWaterTest(id).orElseThrow(() -> new RuntimeException("Test not found"));
        List<Aquarium> allAquariumsByUserId = aquariumService.getAllAquariumsByUserId(appUser.getId()).orElseThrow(() -> new RuntimeException("Aquarium not found"));

        if (allAquariumsByUserId.stream().noneMatch(aquarium -> aquarium.getId() == testTodelete.getAquarium().getId())) {
            throw new RuntimeException("Aquarium not found for this user");
        } else {
            System.out.println("Delete TEst ==> "+ testTodelete);
            waterTestService.deleteWaterTest(testTodelete);
        }
    }
}
