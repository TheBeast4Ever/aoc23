package org.beast4ever.aoc.aoc2k23.day05;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.beast4ever.aoc.aoc2k23.day04.ScratchCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@Slf4j
public class Day05Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;


    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<Long> seedList = parseSeed(lines.get(0));
        Almanac almanac = parseInputAlmanac(lines.subList(2, lines.size()));

        OptionalLong minLocationValue = seedList.stream().mapToLong(currentSeed -> almanac.getLocationFromSeed(currentSeed)).min();

        return "" + minLocationValue.getAsLong();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);

        Almanac almanac = parseInputAlmanac(lines.subList(2, lines.size()));

        List<Long> seedRanges = parseSeed(lines.get(0));

        Long minLocationValue = Long.MAX_VALUE;
        for (int i=0; i<seedRanges.size(); i=i+2) {
            Long startNumber = seedRanges.get(i);
            Long nbOfSeed = seedRanges.get(i+1);
            for (long j=startNumber; j < startNumber+nbOfSeed; j++) {
                long currentLocationValue = almanac.getLocationFromSeed(j);
                if (currentLocationValue<minLocationValue) {
                    minLocationValue = currentLocationValue;
                }
            }
            log.info("Seed interval completed : {}", i+1);
        }

        return "" + minLocationValue;
    }

    private List<Long> parseSeed(String rawtext) {
        StringTokenizer tokenize = new StringTokenizer(rawtext.substring(7), " ");
        List<Long> seeds = new ArrayList<>();
        while (tokenize.hasMoreTokens()) {
            seeds.add(Long.parseLong(tokenize.nextToken()));
        }
        return seeds;
    }

    private List<Long> parseSeedIntervals(String rawtext) {
        StringTokenizer tokenize = new StringTokenizer(rawtext.substring(7), " ");
        List<Long> seeds = new ArrayList<>();
        int i=0;
        Long startSeedNumber = 0l;
        Long numberOfSeedsFromStartNumber = 0l;
        while (tokenize.hasMoreTokens()) {
            if (i%2 == 0) {
                startSeedNumber = Long.parseLong(tokenize.nextToken());
            } else {
                numberOfSeedsFromStartNumber = Long.parseLong(tokenize.nextToken());
                long[] seedNumbersGenerated = LongStream.range(startSeedNumber, startSeedNumber+numberOfSeedsFromStartNumber).toArray();
                for (int j=0; j<seedNumbersGenerated.length; j++) {
                    seeds.add(Long.valueOf(seedNumbersGenerated[j]));
                }
            }

            i++;
        }
        return seeds;
    }



    private Almanac parseInputAlmanac(List<String> lines) throws IOException {
        List<String> seedToSoilMapData = parseInputMap(Almanac.SEED_TO_SOIL, lines);

        List<String> soilToFertilizerMapData = parseInputMap(Almanac.SOIL_TO_FERTILIZER, lines);

        List<String> fertilizerToWaterMapData = parseInputMap(Almanac.FERTILIZER_TO_WATER, lines);

        List<String> waterToLightMapData = parseInputMap(Almanac.WATER_TO_LIGHT, lines);

        List<String> lightToTemperature = parseInputMap(Almanac.LIGHT_TO_TEMPERATURE, lines);

        List<String> temperatureToHumidity = parseInputMap(Almanac.TEMPERATURE_TO_HUMIDITY, lines);

        List<String> humidityToLocation =  parseInputMap(Almanac.HUMIDITY_TO_LOCATION, lines);

        Almanac almanac = new Almanac(seedToSoilMapData, soilToFertilizerMapData,
                fertilizerToWaterMapData, waterToLightMapData,
                lightToTemperature, temperatureToHumidity,
                humidityToLocation);

        return almanac;
    }

    private List<String> parseInputMap(String mapName, List<String> lines) {
        int indexOfStartOfMap=0;
        int indexOfEndOfMap=0;

        for (int i=0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (currentLine.startsWith(mapName)) {
                indexOfStartOfMap = i+1;
                break;
            }
        }

        for (int i=indexOfStartOfMap; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            if (currentLine.isEmpty() || i==lines.size()-1) {
                indexOfEndOfMap = i;
                break;
            }
        }


        return lines.subList(indexOfStartOfMap, indexOfEndOfMap);
    }
}
