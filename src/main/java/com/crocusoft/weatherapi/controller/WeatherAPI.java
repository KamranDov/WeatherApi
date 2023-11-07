package com.crocusoft.weatherapi.controller;


import com.crocusoft.weatherapi.dto.WeatherDto.WeatherDTO;
import com.crocusoft.weatherapi.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/weather")
@AllArgsConstructor
public class WeatherAPI {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    ResponseEntity<WeatherDTO> getWeather(@PathVariable("city") String city){
        return ResponseEntity.ok(weatherService.getWeatherByCityName(city));
    }
}
