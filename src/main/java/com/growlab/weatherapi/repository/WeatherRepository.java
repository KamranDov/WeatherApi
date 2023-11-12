package com.growlab.weatherapi.repository;

import com.growlab.weatherapi.domain.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, UUID> {

    Optional<WeatherEntity> findFirstByRequestCityNameOrderByUpdateTimeDesc(String city);

    Long deleteByUpdateTimeBefore(LocalDateTime thresholdTime);
}



