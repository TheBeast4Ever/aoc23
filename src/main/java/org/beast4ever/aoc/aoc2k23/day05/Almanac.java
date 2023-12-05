package org.beast4ever.aoc.aoc2k23.day05;

import javax.xml.transform.Source;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Almanac {
    private Map<String, SourceDestinationMap> sourceDestinationMap;

    private Map<String, String> sourceToDestinationMap;

    public static final String ORIGIN = "ORIGIN";

    public static final String DESTINATION = "DESTINATION";

    public static final String SEED_TO_SOIL = "seed-to-soil";
    public static final String SOIL_TO_FERTILIZER = "soil-to-fertilizer";
    public static final String FERTILIZER_TO_WATER = "fertilizer-to-water";
    public static final String WATER_TO_LIGHT = "water-to-light";
    public static final String LIGHT_TO_TEMPERATURE = "light-to-temperature";
    public static final String TEMPERATURE_TO_HUMIDITY = "temperature-to-humidity";
    public static final String HUMIDITY_TO_LOCATION = "humidity-to-location";

    public Almanac(List<String> rangesSeedToSoil, List<String> rangesSoilToFertilizer,
                   List<String> rangesFertilizerToWater, List<String> rangesWaterToLight,
                   List<String> rangeslightToTemperature, List<String> rangesTemperatureToHumidity,
                   List<String> rangesHumidityToLocation) {
        sourceDestinationMap = new HashMap<>();

        sourceDestinationMap.put(SEED_TO_SOIL, new SourceDestinationMap(SEED_TO_SOIL, rangesSeedToSoil));
        sourceDestinationMap.put(SOIL_TO_FERTILIZER, new SourceDestinationMap(SOIL_TO_FERTILIZER, rangesSoilToFertilizer));
        sourceDestinationMap.put(FERTILIZER_TO_WATER, new SourceDestinationMap(FERTILIZER_TO_WATER, rangesFertilizerToWater));
        sourceDestinationMap.put(WATER_TO_LIGHT, new SourceDestinationMap(WATER_TO_LIGHT, rangesWaterToLight));
        sourceDestinationMap.put(LIGHT_TO_TEMPERATURE, new SourceDestinationMap(LIGHT_TO_TEMPERATURE, rangeslightToTemperature));
        sourceDestinationMap.put(TEMPERATURE_TO_HUMIDITY, new SourceDestinationMap(TEMPERATURE_TO_HUMIDITY, rangesTemperatureToHumidity));
        sourceDestinationMap.put(HUMIDITY_TO_LOCATION, new SourceDestinationMap(HUMIDITY_TO_LOCATION, rangesHumidityToLocation));

        sourceToDestinationMap = new HashMap<>();
        sourceToDestinationMap.put(ORIGIN, SEED_TO_SOIL);
        sourceToDestinationMap.put(SEED_TO_SOIL, SOIL_TO_FERTILIZER);
        sourceToDestinationMap.put(SOIL_TO_FERTILIZER, FERTILIZER_TO_WATER);
        sourceToDestinationMap.put(FERTILIZER_TO_WATER, WATER_TO_LIGHT);
        sourceToDestinationMap.put(WATER_TO_LIGHT, LIGHT_TO_TEMPERATURE);
        sourceToDestinationMap.put(LIGHT_TO_TEMPERATURE, TEMPERATURE_TO_HUMIDITY);
        sourceToDestinationMap.put(TEMPERATURE_TO_HUMIDITY, HUMIDITY_TO_LOCATION);
        sourceToDestinationMap.put(HUMIDITY_TO_LOCATION, DESTINATION);
    }

    public Long getDestinationFromMapToOtherMap (String mapSourceName, String mapDestinationName, Long sourceNumber) {
        String currentMapName = mapSourceName;
        Long currentSourceValue = sourceNumber;
        Long currentDestinationValue = 0l;
        SourceDestinationMap currentMap;

            do {
                currentMap = sourceDestinationMap.get(currentMapName);
                currentDestinationValue = currentMap.getDestinationForSource(currentSourceValue);
                currentSourceValue = currentDestinationValue;
                currentMapName = sourceToDestinationMap.get(currentMap.getName());
            } while (!currentMap.getName().equals(mapDestinationName));
            return currentDestinationValue;

    }

    public Long getLocationFromSeed(Long sourceNumber) {
        return getDestinationFromMapToOtherMap(SEED_TO_SOIL, HUMIDITY_TO_LOCATION, sourceNumber);
    }

    public Long getFertilizerFromSeed(Long sourceNumber) {
        return getDestinationFromMapToOtherMap(SEED_TO_SOIL, SOIL_TO_FERTILIZER, sourceNumber);
    }

    public Long getSoilFromSeed(Long sourceNumber) {
        return sourceDestinationMap.get(SEED_TO_SOIL).getDestinationForSource(sourceNumber);
    }
}
