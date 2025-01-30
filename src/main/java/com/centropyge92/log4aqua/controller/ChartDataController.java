package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.model.waterTest.ChartData;
import com.centropyge92.log4aqua.model.waterTest.DataTestForChart;
import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.centropyge92.log4aqua.model.waterTest.WaterTestKind;
import com.centropyge92.log4aqua.service.AquariumService;
import com.centropyge92.log4aqua.service.WaterTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ChartDataController {

    @Autowired
    AquariumService aquariumService;

    @Autowired
    WaterTestService waterTestService;

    @GetMapping("/getChartData/{aquariumId}/{testKind}")
    public Optional<ChartData> getChartData(@PathVariable int aquariumId, @PathVariable String testKind, @AuthenticationPrincipal User user) {
        System.out.println("Requested Chart Data for: " + testKind + " , aquarium Id " + aquariumId);

        List<WaterTest> waterTests = waterTestService.getWaterTestsByAquariumIdOrderByDateDesc(aquariumId);
        if (waterTests.isEmpty()) {
            return Optional.empty();
        }

        WaterTestKind waterTestKind;
        try {
            waterTestKind = WaterTestKind.valueOf(testKind.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }

        // Filtrer les tests par testKind
        List<WaterTest> filteredTests = waterTests.stream()
                .filter(test -> test.getTests().stream().anyMatch(singleTest -> singleTest.getName().equals(testKind)))
                .toList();

        // Créer la liste des données du graphique
        List<DataTestForChart> data = filteredTests.stream()
                .flatMap(filteredTest -> filteredTest.getTests().stream()
                        .filter(singleTest -> singleTest.getName().equals(waterTestKind.getName()))
                        .map(singleTest -> new DataTestForChart(filteredTest.getDate(), singleTest.getValue()))
                )
                .toList();

        ChartData chartData = new ChartData(aquariumId, waterTestKind, data);
        System.out.println("CHART DATA ==> " + chartData);

        return Optional.of(chartData);
    }




}
