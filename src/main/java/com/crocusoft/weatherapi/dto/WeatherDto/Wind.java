package com.crocusoft.weatherapi.dto.WeatherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wind {

    private Double speed;
    private Integer deg;
}
