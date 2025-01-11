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
public class Event {

    @Id
    @GeneratedValue
    private int id;
    private EventType type = EventType.OTHER;

    @ManyToOne
    @JsonIgnore
    private Aquarium aquarium;

    private Date data = new Date();
    private String title;
    private String description;
    public enum EventType { TREATEMENT, MAINTENANCE, ANIMAL, OTHER}
}

