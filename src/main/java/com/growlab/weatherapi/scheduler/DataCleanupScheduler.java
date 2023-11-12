package com.growlab.weatherapi.scheduler;

import com.growlab.weatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataCleanupScheduler {

    private final WeatherRepository weatherRepository;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void cleanupOldData() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        Long deletedCount = weatherRepository.deleteByUpdateTimeBefore(oneMonthAgo.atStartOfDay());

        if (deletedCount > 0) {
            log.info("{} records deleted in old data cleanup.", deletedCount);
        } else {
            log.info("No records found for old data cleanup.");
        }
    }
}



