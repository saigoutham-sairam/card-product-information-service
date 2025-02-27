package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/details")
public
class AscendingNumberGeneratorController {

    @GetMapping
    public
    List<Integer> getAscendingNumberGenerator(@RequestParam() int lead) {
        List<Integer> numList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int firstDigit = lead / 10;  // Tens place
            int secondDigit = lead % 10; // Ones place

            if (secondDigit != 0 && firstDigit != 0 && secondDigit >= firstDigit) {
                numList.add(lead);
            }
            lead++;
        }

        Collections.sort(numList);
        System.out.println(numList.toString());
        return numList;
    }

    public static
    List<Integer> getAscendingNumGen(int lead) {
        return IntStream.range(lead, lead + 100)
                .filter(num -> {
                    int firstDigit = num / 10;
                    int secondDigit = num % 10;
                    return secondDigit != 0 && firstDigit != 0 && secondDigit >= firstDigit;
                })
                .sorted()
                .boxed()
                .collect(Collectors.toList());

    }
}
