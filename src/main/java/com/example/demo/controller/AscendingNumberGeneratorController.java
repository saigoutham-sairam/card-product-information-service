package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/details")
public
class AscendingNumberGeneratorController {

    @GetMapping
    public
    List<Integer> getAscendingNumberGenerator(@RequestParam() int lead) {
        List<Integer> numList = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            System.out.println("lead value " + lead);
            int firstDigit = lead / 10;  // Tens place
            int secondDigit = lead % 10; // Ones place
            if ((secondDigit != 0) && (firstDigit != 0)) {
                if ((secondDigit >= firstDigit)) {
                    String appendedNum = String.valueOf(firstDigit) + secondDigit;
                    numList.add(Integer.parseInt(appendedNum));
                }
            }
            lead++;
        }
        Collections.sort(numList);
        System.out.println("builder value " + numList);
        return numList;
    }
}
