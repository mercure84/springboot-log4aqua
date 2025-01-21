package com.centropyge92.log4aqua.model.waterTest;

import lombok.Getter;

@Getter
public enum WaterTestType {
    TEMPERATURE("Temperature", "°C"),
    SALINITY("Salinity", "ppt"),
    DENSITY("Density", "kg/m³"),
    KH("KH", "dKH"),
    GH("GH", "ppm"),
    PH("pH", ""),
    CALCIUM("Calcium", "mg/L"),
    MAGNESIUM("Magnesium", "mg/L"),
    AMMONIAC("Ammoniac", "mg/L"),
    NITRATES("Nitrates", "mg/L"),
    NITRITES("Nitrites", "mg/L"),
    PHOSPHATES("Phosphates", "mg/L"),
    SILICATES("Silicates", "mg/L");

    private final String name;
    private final String unit;

    WaterTestType(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

}
