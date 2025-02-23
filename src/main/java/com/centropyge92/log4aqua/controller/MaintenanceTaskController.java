package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.model.task.WaterTestReminder;
import com.centropyge92.log4aqua.service.AquariumService;
import com.centropyge92.log4aqua.service.MaintenanceTaskService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MaintenanceTaskController {

    @Autowired
    MaintenanceTaskService maintenanceTaskService;

    @Autowired
    AquariumService aquariumService;

    @GetMapping("/getWaterTestReminder/{id}")
    public ResponseEntity<Optional<WaterTestReminder>> getWaterTestReminders(@PathVariable int id, @AuthenticationPrincipal User user) {
        System.out.println("FETCHING WATER TEST REMINDERS");
        Optional<WaterTestReminder> reminder = maintenanceTaskService.getWaterTestReminder(id);
        return ResponseEntity.ok(reminder);
    }

    @PostMapping("/saveWaterTestReminder/{id}")
    public void saveWaterTestReminder(@PathVariable int id, @RequestBody WaterTestReminder reminder, @AuthenticationPrincipal User user) {
        Optional<Aquarium> aquarium = aquariumService.getAquarium(id);
        if (aquarium.isEmpty()) {
            throw new RuntimeException("Aquarium not found for this id");
        }

        // Vérifier s'il y a déjà un WaterTestReminder pour cet aquarium
        Optional<WaterTestReminder> existingReminder = maintenanceTaskService.getWaterTestReminder(id);

        if (existingReminder.isPresent()) {
            // Mettre à jour l'existant
            WaterTestReminder existing = existingReminder.get();
            existing.setDayInterval(reminder.getDayInterval());
            existing.setLastReminder(reminder.getLastReminder());
            existing.setRememberToTestWater(reminder.isRememberToTestWater());
            maintenanceTaskService.saveWaterTestReminder(existing);
        } else {
            // Sauvegarder un nouveau
            reminder.setAquarium(aquarium.get());
            maintenanceTaskService.saveWaterTestReminder(reminder);
        }
    }

}
