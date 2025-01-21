package com.centropyge92.log4aqua.model.aquarium;


import com.centropyge92.log4aqua.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

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


}
