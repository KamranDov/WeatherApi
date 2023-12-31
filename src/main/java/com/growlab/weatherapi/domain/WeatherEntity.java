package com.growlab.weatherapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "weather_responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "request_city_name")
    private String requestCityName;

    @Column(name = "city_name")
    @NotBlank(message = "City name is not blank")
    private String cityName;

    @Column(name = "country")
    private String country;

    @Column(name = "temperature")
    private Long temperature;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "response_local_time")
    private LocalDateTime responseLocalTime;

    public WeatherEntity(String requestCityName, String cityName, String country, Long temperature, LocalDateTime updateTime, LocalDateTime responseLocalTime) {
        this.id = "";
        this.requestCityName = requestCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updateTime = updateTime;
        this.responseLocalTime = responseLocalTime;
    }
}
