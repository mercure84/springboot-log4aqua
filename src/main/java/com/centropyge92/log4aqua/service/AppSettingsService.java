package com.centropyge92.log4aqua.service;

import com.centropyge92.log4aqua.model.AppSettings;
import com.centropyge92.log4aqua.repository.AppSettingsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class AppSettingsService {


    @Autowired
    AppSettingsRepository appSettingsRepository;

    public AppSettings updateAppSettings(AppSettings appSettings) {
        return appSettingsRepository.save(appSettings);
    }

    public AppSettings getAppSettings(int id) {
        return appSettingsRepository.findById(id);
    }
}
