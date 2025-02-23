package com.centropyge92.log4aqua.model.task;


import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class MaintenanceTask {

    @Id
    @GeneratedValue
    private int id;
    private String label;
    private int hour;
    private int dayInterval;
    private Date startDate;

    @ManyToOne
    @JsonIgnore
    private Aquarium aquarium;

}
