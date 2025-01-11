package com.centropyge92.log4aqua.model.living;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Fish extends Animal{

    private String typeOfFish;
    private FishOrigin fishOrigin;


    public enum FishOrigin {
        WILD, CAPTIVEBRED
    }

}
