package com.crocusoft.weatherapi.repository;

import com.crocusoft.weatherapi.domain.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WeatherRepository extends JpaRepository<WeatherEntity, UUID> {

    Optional<WeatherEntity> findFirstByRequestCityNameOrderByUpdateTimeDesc(String city);

}
