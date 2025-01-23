package com.centropyge92.log4aqua.model.waterTest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;

import java.util.Date;
import java.util.EnumMap;
import java.util.List;

@Data
@Entity
public class WaterTest {

    @Id
    @GeneratedValue
    private int id;
    private Date date = new Date();
    private String notes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SingleTest> tests;
    @ManyToOne
    @JsonIgnore
    private Aquarium aquarium;
}
