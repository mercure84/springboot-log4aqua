package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @GetMapping("/getAppUser")
    public AppUser getAppUser(@AuthenticationPrincipal User user) {
        return appUserService.saveOrUpdateUser(user);
    }
}