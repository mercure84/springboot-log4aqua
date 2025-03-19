package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getAppUser")
    public AppUser getAppUser(@AuthenticationPrincipal User user) {
        return appUserService.saveOrUpdateUser(user);
    }

    @DeleteMapping("/deleteAppUser")
    public ResponseEntity deleteAquarium(@AuthenticationPrincipal User user) {
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        try {
            appUserService.deleteAppUser(appUser);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}