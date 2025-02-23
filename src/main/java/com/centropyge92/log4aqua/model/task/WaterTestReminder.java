package com.centropyge92.log4aqua.model.task;

import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class WaterTestReminder {

    @Id
    @GeneratedValue
    int id;
    private boolean rememberToTestWater = false;
    private int dayInterval = 7;
    private Date lastReminder;

    @OneToOne
    @JsonIgnore
    private Aquarium aquarium;

}
