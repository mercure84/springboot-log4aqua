package com.centropyge92.log4aqua.model.waterTest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

@Data
@Entity
public class WaterTest {

    @Id
    @GeneratedValue
    private int id;

    private Date date = new Date();

    @ElementCollection
    @CollectionTable
    @MapKeyColumn
    @Column
    @Enumerated(EnumType.STRING)
    private Map<WaterTestType, Float> tests = new EnumMap<>(WaterTestType.class);

    @ManyToOne
    @JsonIgnore
    private Aquarium aquarium;
}
