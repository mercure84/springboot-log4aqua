package com.centropyge92.log4aqua.model.waterTest;

import lombok.Data;

import java.util.Date;

@Data
public class DataTestForChart {
    private Date date;
    private float value;

    public DataTestForChart(Date date, float value) {
        this.date = date;
        this.value = value;
    }
}
