package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/districtList")
    public ResponseEntity<String> getDistrictList(@RequestParam String city){
        log.info("시/구/군 찾기 요청");


        return ResponseEntity.ok().body(weatherService.searchDistrict(city).toString());
    }
    @GetMapping("/neighborhoodList")
    public ResponseEntity<List<String>> getNeighborhoodList(@RequestParam String district){
        log.info("동/읍/리 찾기 요청");

        return ResponseEntity.ok().body(weatherService.searchNeighborhood(district));
    }

}