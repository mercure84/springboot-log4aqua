package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.model.task.MaintenanceTask;
import com.centropyge92.log4aqua.model.task.WaterTestReminder;
import com.centropyge92.log4aqua.repository.MaintenanceTaskRepository;
import com.centropyge92.log4aqua.repository.WaterTestReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaintenanceTaskService {


    @Autowired
    MaintenanceTaskRepository maintenanceTaskRepository;

    @Autowired
    WaterTestReminderRepository waterTestReminderRepository;


    public Optional<WaterTestReminder> getWaterTestReminder(int id) {
        return waterTestReminderRepository.findByAquariumId(id);
    }


    public void saveWaterTestReminder(WaterTestReminder testReminder) {
        waterTestReminderRepository.save(testReminder);
    }


    public void deleteWaterTestReminder(WaterTestReminder testReminder) {
        waterTestReminderRepository.delete(testReminder);
    }


    public void saveMaintenanceTask(MaintenanceTask maintenanceTask) {
        maintenanceTaskRepository.save(maintenanceTask);
    }

    public void deleteMaintenanceTask(MaintenanceTask maintenanceTask) {
        maintenanceTaskRepository.delete(maintenanceTask);
    }


}
