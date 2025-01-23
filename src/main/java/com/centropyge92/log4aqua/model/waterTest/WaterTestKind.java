package com.centropyge92.log4aqua.model.waterTest;

import lombok.Getter;

@Getter
public enum WaterTestKind {
    TEMPERATURE("Temperature", "°C"),
    SALINITY("Salinity", "ppt"),
    DENSITY("Density", "kg/m³"),
    KH("KH", "dKH"),
    GH("GH", "dGH"),
    PH("pH", ""),
    CALCIUM("Calcium", "mg/L"),
    MAGNESIUM("Magnesium", "mg/L"),
    AMMONIAC("Ammoniac", "mg/L"),
    NITRATES("Nitrates", "mg/L"),
    NITRITES("Nitrites", "mg/L"),
    PHOSPHATES("Phosphates", "mg/L"),
    SILICATES("Silicates", "mg/L"),
    CUIVRE("Cuivre", "mg/L"),
    FER("Fer", "mg/L");


    private final String name;
    private final String unit;

    WaterTestKind(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

}
