package com.centropyge92.log4aqua.model.waterTest;

import lombok.AllArgsConstructor;
import lombok.Data;

//classe utilitaire pour transporter les données au format JSON dans notre controller
@Data
@AllArgsConstructor
public class TestTypeDTO {
    private String name;
    private String unit;
}
