package com.growlab.weatherapi.service;

import com.growlab.weatherapi.domain.WeatherEntity;
import com.growlab.weatherapi.dto.WeatherDto.WeatherDTO;
import com.growlab.weatherapi.dto.WeatherDto.WeatherResponse;
import com.growlab.weatherapi.feign.WeatherServiceClient;
import com.growlab.weatherapi.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherService {


    private static final String API_KEY = "d72c5bb68f0ab076df52f5b8df4cad77";

    private final WeatherRepository weatherRepository;
    private final WeatherServiceClient weatherServiceClient;

    public WeatherDTO getWeatherByCityName(String city) {
        Optional<WeatherEntity> weatherEntityOptional = weatherRepository.findFirstByRequestCityNameOrderByUpdateTimeDesc(city);

        return weatherEntityOptional.map(weather -> {
            if (weather.getUpdateTime().isBefore(LocalDateTime.now().minusMinutes(40))) {
                log.info("Using cached weather data for city: {}", city);
                return WeatherDTO.convert(getWeatherFromWeatherStack(city));
            }
            return WeatherDTO.convert(weather);
        }).orElseGet(() -> WeatherDTO.convert(getWeatherFromWeatherStack(city)));

    }

    private WeatherEntity getWeatherFromWeatherStack(String city) {

        ResponseEntity<WeatherResponse> responseEntity = weatherServiceClient.getWeather(city, API_KEY);

        WeatherResponse weatherResponse = responseEntity.getBody();
        log.info("Fetched weather data from external API for city: {}", city);
        return saveWeatherEntity(city, weatherResponse);
    }

    private WeatherEntity saveWeatherEntity(String city, WeatherResponse weatherResponse) {

        Double temperatureKelvin = weatherResponse.getMain().getTemp();
        Long temperatureCelsius = kelvinToCelsius(temperatureKelvin);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Long dt = weatherResponse.getDt();
        String currentDateTime = LocalDateTime.now().format(formatter);
        String dtDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(dt), ZoneOffset.UTC).format(formatter);

        WeatherEntity entity = new WeatherEntity(city
                , weatherResponse.getName()
                , weatherResponse.getSys().getCountry()
                , temperatureCelsius
                , LocalDateTime.parse(currentDateTime, formatter)
                , LocalDateTime.parse(dtDateTime, formatter));

        log.info("Saved weather data to the database for city: {}", city);
        return weatherRepository.save(entity);
    }

    private Long kelvinToCelsius(Double temperatureKelvin) {
        Double temperatureCelsius = temperatureKelvin - 273.15;
        return Math.round(temperatureCelsius);
    }
}
