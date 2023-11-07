package com.crocusoft.weatherapi.dto.WeatherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sys {

    private Integer type;
    private Integer id;
    private String country;
    private Long sunrise;
    private Long sunset;

}
