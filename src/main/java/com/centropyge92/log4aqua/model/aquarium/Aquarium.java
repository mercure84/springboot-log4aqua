package com.centropyge92.log4aqua.model.aquarium;


import com.centropyge92.log4aqua.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public abstract class Aquarium {


    @Id
    @GeneratedValue
    private int id;
    private String name;

    private int length;
    private int width;
    private int height;

    private Date startDate = new Date();

    @ManyToOne
    private AppUser appUser;
    private int sumpVolume;

    public float getRawVolume() {
        return (float) (this.length * this.width * this.height) /1000;
    }
}
