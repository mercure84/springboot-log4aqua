package com.centropyge92.log4aqua.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class AppSettings {

    @Id
    @GeneratedValue
    private int id;
    private int themeColor = 0;
    private String iosPushToken;
    private String androidPushToken;

}
