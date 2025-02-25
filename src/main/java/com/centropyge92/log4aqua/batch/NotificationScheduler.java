
package com.centropyge92.log4aqua.batch;

import com.centropyge92.log4aqua.model.AppSettings;
import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.model.task.WaterTestReminder;
import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.centropyge92.log4aqua.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableScheduling
public class NotificationScheduler {

    @Autowired
    PushNotificationService pushNotificationService;


    @Autowired
    MaintenanceTaskService maintenanceTaskService;


    @Autowired
    AppUserService appUserService;

    @Autowired
    AquariumService aquariumService;


    @Scheduled(cron = "0 0 10 * * ?")
    public void sendWaterTestReminder() {

        Optional<List<AppUser>> optionalAppUsers = appUserService.getAllAppUsers();
        optionalAppUsers.ifPresent(appUsers -> {
            for (AppUser appUser : appUsers) {
                Optional<List<Aquarium>> aquarium = aquariumService.getAllAquariumsByUserId(appUser.getId());
                if (aquarium.isPresent()) {
                    Optional<List<WaterTest>> optionalWaterTests = Optional.ofNullable(aquarium.get().getFirst().getWaterTestList());
                    List<WaterTest> sortedWaterTests = optionalWaterTests.get().stream()
                            .sorted((wt1, wt2) -> wt2.getDate().compareTo(wt1.getDate()))
                            .toList();
                    Date lastWaterTestDate = sortedWaterTests.get(0).getDate();
                    AppSettings appSettings = appUser.getAppSettings();
                    if (appSettings.getAndroidPushToken() == null && appSettings.getIosPushToken() == null) {
                        System.out.println("No push token found for user " + appUser.getId());
                        continue;
                    }
                    Optional<WaterTestReminder> waterTestReminder = maintenanceTaskService.getWaterTestReminder(aquarium.get().getFirst().getId());
                    if (waterTestReminder.isEmpty() || !waterTestReminder.get().isRememberToTestWater()) {
                        System.out.println("No reminder set for user " + appUser.getId());
                        continue;
                    }

                    int delay = waterTestReminder.get().getDayInterval();
                    if (new Date().getTime() - lastWaterTestDate.getTime() >= (long) delay * 24 * 60 * 60 * 1000) {
                        System.out.println("Sending reminder to user " + appUser.getId());
                        String iOSToken = appSettings.getIosPushToken();
                        String androidToken = appSettings.getAndroidPushToken();
                        if (iOSToken != null) {
                            pushNotificationService
                                    .sendNotificationToDevice(appSettings
                                            .getIosPushToken(), "Ding Dong 🔔 ! Testez votre eau :)", "Ding Dong ! Testez votre eau :)");
                        }

                        if (androidToken != null) {
                            pushNotificationService
                                    .sendNotificationToDevice(appSettings
                                            .getAndroidPushToken(), "Ding Dong 🔔 ! Testez votre eau :)", "Ding Dong ! Testez votre eau :)");
                        }
                        waterTestReminder.get().setLastReminder(new Date());
                        maintenanceTaskService.saveWaterTestReminder(waterTestReminder.get());

                    }
                }
            }
        });
    }
}