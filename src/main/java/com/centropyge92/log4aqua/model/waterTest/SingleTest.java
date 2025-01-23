package com.centropyge92.log4aqua.model.waterTest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SingleTest  {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String unit;
    private float value;

}
