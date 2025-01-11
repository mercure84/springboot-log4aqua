package com.centropyge92.log4aqua.model;

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
public class Equipment {

    @Id
    @GeneratedValue
    private int id;
    private Date dateInstallation = new Date();
    private TypeOfEquipment typeOfEquipment;
    private String model;
    private String description;
    private float power = 0;
    private int quantity = 1;

    @ManyToOne
    @JsonIgnore
    private Aquarium aquarium;

    public enum TypeOfEquipment {SKIMMER, CO2INJECTOR, OZONATOR, OSMOLATOR, OSMOSIS, STREAMPUMP, RETURNPUMP, LIGHT, DOSINGPUMP, AIRPUMP, CONTROLLER, FILTER, HEATING, ULTRA_V, OTHER}

}