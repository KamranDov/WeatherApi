package com.growlab.weatherapi.dto.WeatherDto;

import com.growlab.weatherapi.domain.WeatherEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {

    private String cityName;
    private String country;
    private Long temperature;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateTime;

    public static WeatherDTO convert(WeatherEntity from) {
        return new WeatherDTO(from.getCityName()
                , from.getCountry()
                , from.getTemperature()
                , from.getUpdateTime());
    }

}
