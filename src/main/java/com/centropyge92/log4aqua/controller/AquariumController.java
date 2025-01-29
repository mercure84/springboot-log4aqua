package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.service.AppUserService;
import com.centropyge92.log4aqua.service.AquariumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AquariumController {

    @Autowired
    AquariumService aquariumService;

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getAquariums")
    public Optional<List<Aquarium>> getAquariumsByAppUser(@AuthenticationPrincipal User user) {
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return aquariumService.getAllAquariumsByUserId(appUser.getId());
    }

    @DeleteMapping("/deleteAquarium/{id}")
    public void deleteAquarium(@PathVariable int id, @AuthenticationPrincipal User user) {
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        List<Aquarium> allAquariumsByUserId = aquariumService.getAllAquariumsByUserId(appUser.getId()).orElseThrow(() -> new RuntimeException("Aquarium not found"));
        //check if aquarium belows to the user
        if (allAquariumsByUserId.stream().noneMatch(aquarium -> aquarium.getId() == id)) {
            throw new RuntimeException("Aquarium not found for this user");
        } else {
            aquariumService.deleteAquarium(id);
        }


    }

    @PostMapping("/createOrUpdateAquarium")
    public Aquarium createOrUpdate(@RequestBody Aquarium aquarium,  @AuthenticationPrincipal User user) throws Exception {
        if (aquarium == null) {
            throw new Exception("Aquarium cannot be null");
        }
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new Exception("User not found"));
        aquarium.setAppUser(appUser);
        aquariumService.saveAquarium(aquarium);
        return aquarium;
    }
}
