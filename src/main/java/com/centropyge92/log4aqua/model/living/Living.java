package com.centropyge92.log4aqua.model.living;

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
public abstract class Living {

    @Id
    @GeneratedValue
    private int id;
    private String name ;
    private Date incomingDate = new Date();
    private Date exitDate;
    private Date deathDate ;
    private String notes = null ;
    private int quantity = 1;


    @ManyToOne
    @JsonIgnore
    private Aquarium aquarium;
}
