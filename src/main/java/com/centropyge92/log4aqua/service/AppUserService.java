package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.model.AppSettings;
import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser saveOrUpdateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object is null");
        }
        String email = user.getUsername();
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) {
            appUser = new AppUser();
            appUser.setEmail(email);
            appUser.setProvider("firebase");
            appUser.setAppSettings(new AppSettings());
            return appUserRepository.save(appUser);
        }
        return appUser;
    }


    public void updateAppUserSettings(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public Optional<AppUser> getAppUser(String email) {
        return Optional.ofNullable(appUserRepository.findByEmail(email));
    }

    public Optional<List<AppUser>> getAllAppUsers() {
        return Optional.of(appUserRepository.findAll());
    }

    public void deleteAppUser(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

}
