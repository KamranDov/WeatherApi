package com.growlab.weatherapi.feign;

import com.growlab.weatherapi.dto.WeatherDto.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "WEATHER-SERVICE", url = "http://api.openweathermap.org/data/2.5")
public interface WeatherServiceClient {

    @GetMapping("/weather?q={city}&appid=(apiKey}")
    ResponseEntity<WeatherResponse> getWeather(@PathVariable("city") String city, @RequestHeader("x-api-key") String apiKey);
}