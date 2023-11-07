package com.crocusoft.weatherapi.dto.WeatherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private Integer id;
    private String main;
    private String description;
    private String icon;

}
