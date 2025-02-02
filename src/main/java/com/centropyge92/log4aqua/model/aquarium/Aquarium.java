package com.centropyge92.log4aqua.model.aquarium;


import com.centropyge92.log4aqua.model.AppUser;
import com.centropyge92.log4aqua.model.Equipment;
import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Aquarium {


    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String modelName;


    private int volume;

    private Date startDate = new Date();

    @ManyToOne
    private AppUser appUser;
    private int sumpVolume = 0;

    private WaterType waterType;

    @OneToMany(mappedBy = "aquarium", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    private List<Equipment> equipmentList = new ArrayList<>();

    @OneToMany(mappedBy = "aquarium", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    private List<WaterTest> waterTestList = new ArrayList<>();

}
