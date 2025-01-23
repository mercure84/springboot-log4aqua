package com.centropyge92.log4aqua.controller;

import com.centropyge92.log4aqua.model.waterTest.TestTypeDTO;
import com.centropyge92.log4aqua.model.waterTest.WaterTestKind;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class AppConfigController {

    @GetMapping("/getTestTypes")
    public List<TestTypeDTO> getTestTypes() {
        List<TestTypeDTO> allTypes =  Stream.of(WaterTestKind.values())
                .map(testType -> new TestTypeDTO(testType.getName(), testType.getUnit()))
                .collect(Collectors.toList());
        System.out.println("All TestTypes ==> "+ allTypes);
        return allTypes;
    }

}
