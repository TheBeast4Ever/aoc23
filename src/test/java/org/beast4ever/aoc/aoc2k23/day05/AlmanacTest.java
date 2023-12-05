package org.beast4ever.aoc.aoc2k23.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlmanacTest {

    @Test
    public void whenInlineData_thenSoilFound() {
        Almanac almanac = buildInlineAlmanac();
        List<Long> sources = Arrays.asList(79l, 14l);
        List<Long> expectedResults = Arrays.asList(81l, 14l);
        List<Long> results = new ArrayList<>();

        sources.forEach(source -> results.add(almanac.getSoilFromSeed(source)));

        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenInlineData_thenFertilizerFound() {
        Almanac almanac = buildInlineAlmanac();
        List<Long> sources = Arrays.asList(79l, 14l);
        List<Long> expectedResults = Arrays.asList(81l, 53l);
        List<Long> results = new ArrayList<>();

        sources.forEach(source -> results.add(almanac.getFertilizerFromSeed(source)));

        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenInlineData_thenLocationsFoundBuilt() {
        Almanac almanac = buildInlineAlmanac();
        List<Long> sources = Arrays.asList(79l, 14l, 55l, 13l);
        List<Long> expectedResults = Arrays.asList(82l, 43l, 86l, 35l);
        List<Long> results = new ArrayList<>();

        sources.forEach(source -> results.add(almanac.getLocationFromSeed(source)));

        Assertions.assertEquals(expectedResults, results);
    }

    private Almanac buildInlineAlmanac() {
        String seedToSoilMapData = """
                50 98 2
                52 50 48
                """;
        String soilToFertilizerMapData = """
                0 15 37
                37 52 2
                39 0 15
                """;
        String fertilizerToWaterMapData = """
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                """;
        String waterToLightMapData = """
                88 18 7
                18 25 70
                """;
        String lightToTemperature = """
                45 77 23
                81 45 19
                68 64 13
                """;
        String temperatureToHumidity = """
                0 69 1
                1 0 69
                """;
        String humidityToLocation = """
                60 56 37
                56 93 4
                """;

        Almanac almanac = new Almanac(seedToSoilMapData.lines().toList(), soilToFertilizerMapData.lines().toList(),
                fertilizerToWaterMapData.lines().toList(), waterToLightMapData.lines().toList(),
                lightToTemperature.lines().toList(), temperatureToHumidity.lines().toList(),
                humidityToLocation.lines().toList());

        return almanac;
    }
}
