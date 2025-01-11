package com.centropyge92.log4aqua.model.aquarium;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class SaltWaterAquarium extends Aquarium {

    private TypeOfMaintenance typeOfMaintenance ;
    private MainPopulation mainPopulation;
    private int liveRocksWeigth;
    private int otherRocksWeigth;
    
    public enum TypeOfMaintenance { BERLINOIS, JAUBERT, OTHER}
    public enum MainPopulation { FISH, MIX, SPS, LPS, ALGAE, OTHER}



}

