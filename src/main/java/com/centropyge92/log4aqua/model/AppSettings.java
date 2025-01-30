package com.centropyge92.log4aqua.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AppSettings {

    @Id
    @GeneratedValue
    private int id;
    private boolean rememberToTestWater = false;
    private int dayInterval = 7;
    private int themeColor = 0;
    private String iosPushToken;
    private String androidPushToken;

}
