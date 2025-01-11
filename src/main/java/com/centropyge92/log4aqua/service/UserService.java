package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public void saveOrUpdateUser(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");

        AppUser user = appUserRepository.findByEmail(email);
        if (user == null) {
            user = new AppUser();
            user.setEmail(email);
            user.setUserName(name);
            user.setProvider(jwt.getIssuer().toString());
            appUserRepository.save(user);
        }
    }
}
