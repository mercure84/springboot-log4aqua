package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.service.AppUserService;
import com.centropyge92.log4aqua.service.AquariumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AquariumController {

    @Autowired
    AquariumService aquariumService;

    @Autowired
    AppUserService appUserService;


    @PostMapping("/createNewAquarium")
    public Aquarium createNewAquarium(@RequestBody Aquarium aquarium, Authentication authentication) throws Exception {
        if (aquarium == null) {
            throw new Exception("Aquarium cannot be null");
        }
        User principal = (User) authentication.getPrincipal();
        AppUser appUser = appUserService.getAppUser(principal.getUsername()).orElseThrow(() -> new Exception("User not found"));
        aquarium.setAppUser(appUser);
        aquariumService.saveAquarium(aquarium);
        return aquarium;
        // create new aquarium
    }


}
