package com.centropyge92.log4aqua.model.living;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Invertebrate extends Animal {


    private TypeOfInvertebrate typeOfInvertebrate;


    public enum TypeOfInvertebrate {
        CRUSTACEAN, MOLLUSK, WORM, OTHER, SPS, LPS, ANEMONE, STARFISH, SEA_URCHIN, SEA_CUCUMBER, SHRIMP, CRAB, SNAIL, CLAM, OYSTER, OCTOPUS, SLUG, OTHER_INVERTEBRATE
    }


}
