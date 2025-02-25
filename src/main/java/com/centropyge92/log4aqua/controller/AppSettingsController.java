package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.AppSettings;
import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.model.PushToken;
import com.centropyge92.log4aqua.model.waterTest.TestTypeDTO;
import com.centropyge92.log4aqua.model.waterTest.WaterTestKind;
import com.centropyge92.log4aqua.service.AppSettingsService;
import com.centropyge92.log4aqua.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AppSettingsController {


    @Autowired
    AppUserService appUserService;

    @Autowired
    AppSettingsService appSettingsService;


    @GetMapping("/getAppSettings")
    public Optional<AppSettings> getAppSettings(@AuthenticationPrincipal User user) {
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return Optional.ofNullable(appUser.getAppSettings());
    }


    @PostMapping("/saveAppSettings")
    public void updateAppSettings(@RequestBody AppSettings appSettings, @AuthenticationPrincipal User user) {

        boolean isUpdating = appSettings.getId() != 0;

        if (isUpdating) {
            appSettingsService.updateAppSettings(appSettings);
        } else {
            AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
            appUser.setAppSettings(appSettings);
            appUserService.updateAppUserSettings(appUser);
        }
    }

    @PostMapping("/registerPushToken")
    public void registerPushToken(@RequestBody PushToken pushToken, @AuthenticationPrincipal User user) {
        System.out.println("Push Token has been received : " + pushToken);
        AppUser appUser = appUserService.getAppUser(user.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        setDefaultAppSettingsToAppUser(appUser);
        if(pushToken.getPlatform().equals("ios")) {
            appUser.getAppSettings().setIosPushToken(pushToken.getToken());
        } else if(pushToken.getPlatform().equals("android")) {
            appUser.getAppSettings().setAndroidPushToken(pushToken.getToken());
        }
        appUserService.updateAppUserSettings(appUser);
    }


    private void setDefaultAppSettingsToAppUser(AppUser appUser){
        if(appUser.getAppSettings() == null){
            appUser.setAppSettings(new AppSettings());
        }
    }


    @GetMapping("/getTestTypes")
    public List<TestTypeDTO> getTestTypes() {
        List<TestTypeDTO> allTypes =  Stream.of(WaterTestKind.values())
                .map(testType -> new TestTypeDTO(testType.getName(), testType.getUnit()))
                .collect(Collectors.toList());
        System.out.println("All TestTypes ==> "+ allTypes);
        return allTypes;
    }


}
