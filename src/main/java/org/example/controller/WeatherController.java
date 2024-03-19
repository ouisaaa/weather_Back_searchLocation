package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    //test
    @GetMapping("/test")
    public void testGet(){
        System.out.println(weatherService.test());

    }
    //주소를 입력 받으면 그것을 DB에서 찾아서 변환 후 API 요청하여 저장
    @GetMapping("/getWeather")
    public void getWeather(@RequestParam String address){

    }

}