package com.centropyge92.log4aqua.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;

import java.util.Date;

@Data
@Entity
public class WaterTest {

    @Id
    @GeneratedValue
    private int id;
    private Date date = new Date();
    private float temperature;
    private float salinity;
    private float density;
    private float kh;
    private float gh;
    private float pH;
    private int calcium;
    private int magnesium;
    private float ammoniac;
    private float nitrates;
    private float nitrites;
    private float phosphates;
    private float silicates;

    @ManyToOne
    @JsonIgnore
    private Aquarium aquarium;


}
