package org.beast4ever.aoc.aoc2k23.day05;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class AlmanacMinLocationCalculator {
    private ExecutorService executor;

    public AlmanacMinLocationCalculator(Integer nThreads) {
        executor = Executors.newFixedThreadPool(nThreads);
    }

    public Future<Long> getMinLocation(Long startNumber, Long nbOfSeeds, Almanac almanac) {
        return executor.submit(() -> {
            Long minLocationValue = Long.MAX_VALUE;
            log.info("Seed interval started.");
            for (long j=startNumber; j < startNumber+nbOfSeeds; j++) {
                long currentLocationValue = almanac.getLocationFromSeed(j);
                if (currentLocationValue<minLocationValue) {
                    minLocationValue = currentLocationValue;
                }
            }
            log.info("Seed interval completed.");
            return minLocationValue;
        });
    }
}
