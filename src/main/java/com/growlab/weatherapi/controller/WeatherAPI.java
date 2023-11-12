package com.growlab.weatherapi.controller;


import com.growlab.weatherapi.dto.WeatherDto.WeatherDTO;
import com.growlab.weatherapi.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/weather")
@AllArgsConstructor
public class WeatherAPI {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    @ResponseStatus(HttpStatus.CREATED)
    WeatherDTO getWeather(@PathVariable("city") String city){
        return weatherService.getWeatherByCityName(city);
    }
}
