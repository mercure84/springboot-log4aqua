package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.batch.NotificationScheduler;
import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.model.waterTest.WaterTest;
import com.centropyge92.log4aqua.model.waterTest.SingleTest;
import com.centropyge92.log4aqua.model.waterTest.WaterTestKind;
import com.centropyge92.log4aqua.service.AquariumService;
import com.centropyge92.log4aqua.service.WaterTestService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class TrashToolController {

    @Autowired
    WaterTestService waterTestService;

    @Autowired
    AquariumService aquariumService;

    @Autowired
    NotificationScheduler notificationScheduler;

    @GetMapping("/saveBigDataInBase")
    public void saveBigDataInBase() throws FileNotFoundException {

        CSVReader reader = new CSVReader(new FileReader("src/main/resources/water_test.csv"));
        Optional<Aquarium> aquariumJulien = aquariumService.getAquarium(652);
        List<WaterTest> waterTestsFromCSV = new ArrayList<>();

        try {
            // Lire les lignes du CSV (assurez-vous que le fichier CSV est bien structuré)
            List<String[]> records = reader.readAll();
            records.removeFirst();

            for (String[] record : records) {
                WaterTest waterTest = new WaterTest();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(record[4]);

                // Mapper les données CSV dans un objet WaterTest
                waterTest.setDate(date);  // Si tu veux remplir la date à partir du CSV, tu peux la parser ici

                // Créer une liste de SingleTest à partir du CSV
                List<SingleTest> singleTests = new ArrayList<>();
                if (!Objects.equals(record[1], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.KH.getName());
                    singleTest.setUnit(WaterTestKind.KH.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[1]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }
                if (!Objects.equals(record[3], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.CALCIUM.getName());
                    singleTest.setUnit(WaterTestKind.CALCIUM.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[3]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }
                if (!Objects.equals(record[5], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.MAGNESIUM.getName());
                    singleTest.setUnit(WaterTestKind.MAGNESIUM.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[5]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }
                if (!Objects.equals(record[6], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.NITRATES.getName());
                    singleTest.setUnit(WaterTestKind.NITRATES.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[6]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }
                if (!Objects.equals(record[8], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.PH.getName());
                    singleTest.setUnit(WaterTestKind.PH.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[8]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }
                if (!Objects.equals(record[9], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.PHOSPHATES.getName());
                    singleTest.setUnit(WaterTestKind.PHOSPHATES.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[9]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }
                if (!Objects.equals(record[10], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.SALINITY.getName());
                    singleTest.setUnit(WaterTestKind.SALINITY.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[10]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }
                if (!Objects.equals(record[12], "NULL")) {
                    SingleTest singleTest = new SingleTest();
                    singleTest.setName(WaterTestKind.TEMPERATURE.getName());
                    singleTest.setUnit(WaterTestKind.TEMPERATURE.getUnit());// Nomme les tests ou récupère d'autres valeurs
                    singleTest.setValue((float) Float.parseFloat(record[12]));  // Exemple de valeur de test
                    singleTests.add(singleTest);
                }





                waterTest.setTests(singleTests);
                waterTest.setAquarium(aquariumJulien.get());

                System.out.println("New Test added in Big Data : " + waterTest.toString());

                waterTestsFromCSV.add(waterTest);
            }

            // Fermer le reader
            reader.close();

            // Passer la liste au service pour la sauvegarder dans la base de données
            waterTestService.saveMassWaterTests(waterTestsFromCSV);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/testPushNotification")
    public void testPushNotification() {
        notificationScheduler.sendWaterTestReminder();

    }



}
