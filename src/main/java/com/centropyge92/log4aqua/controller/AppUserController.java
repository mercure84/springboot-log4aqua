package com.centropyge92.log4aqua.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {


    @GetMapping("/helloworld")
    public String helloWorld() {
        System.out.println("Nous sommes iciiii");
        return "Hello World";
    }

    @GetMapping("/protected")
    public String protectedEndpoint(@AuthenticationPrincipal User user) {
        return "Hello, " + user.getUsername() + "!";
    }


}
