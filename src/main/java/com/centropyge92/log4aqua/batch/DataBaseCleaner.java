package com.centropyge92.log4aqua.batch;


import com.centropyge92.log4aqua.model.aquarium.Aquarium;
import com.centropyge92.log4aqua.service.AquariumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DataBaseCleaner {


    @Autowired
    AquariumService aquariumService;



    @GetMapping("/cleanAquariumWithoutUser")
    public void cleanAquariumWithoutUser() {

        Optional<List<Aquarium>> aquariumList = aquariumService.getAllAquariumsWithoutUser();
        System.out.println("aquariumList = " + aquariumList);
        if (aquariumList.isPresent()) {
            for (Aquarium aquarium : aquariumList.get()) {
                System.out.println("delete aquarium = " + aquarium);
                aquariumService.deleteAquarium(aquarium.getId());
            }
        } else {
            System.out.println("No aquariums without user found");
        }
        // TODO : clean aquariums without user
    }







}
