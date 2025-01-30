package com.centropyge92.log4aqua.model.waterTest;

import lombok.Data;

import java.util.List;

@Data
public class ChartData {

    private int aquariumId;
    private WaterTestKind waterTestKind;
    private List<DataTestForChart> dataTests;

    public ChartData(int aquariumId, WaterTestKind waterTestKind, List<DataTestForChart> dataTests) {
        this.aquariumId = aquariumId;
        this.waterTestKind = waterTestKind;
        this.dataTests = dataTests;
    }
}
