package org.beast4ever.aoc.aoc2k23.day05;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
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

    /**
     * Algo à revoir en :
     * - utilisation autre chose qu'une Hashmap (tableau trié ?), faire une recherche dichotomique
     * - en partitionnant globalement les seeds et non par intervalles fournis en entrés
     * - ci-dessous les stats actuelles et réponse de la min value trouvé pour chaque intervalle
     * Thread	Temps multi	Nb seeds	Cadence multi (millions / min)	Min value
     * 1	    14	        235 794 528	16 842 466	                    802 908 268
     * 2	    17	        325 625 103	19 154 418	                    103 047 735
     * 3	    13	        218 217 413	16 785 955	                    462 683 166
     * 4	    7	        102 999 617	14 714 231	                    1 879 134 746
     * 5	    9	        143 268 116	15 918 680	                    565 221 568
     * 6	    11	        185 836 835	16 894 258	                    57 451 709	<-
     * 7	    14	        241 888 600	17 277 757	                    303 641 457
     * 8	    33	        806 228 439	24 431 165	                    94 721 280
     * 9	    4	        56 204 204	14 051 051	                    551 761 867
     * 10	    13	        224 520 829	17 270 833	                    776 669 237
     * @param inputFilePath
     * @return
     * @throws IOException
     */
    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        Almanac almanac = parseInputAlmanac(lines.subList(2, lines.size()));


        List<Long> seedRanges = parseSeed(lines.get(0));
        Integer nbOfPartitions = seedRanges.size()/2;
        List<Long> minValueFromEachPartition = new ArrayList<>();
        AlmanacMinLocationCalculator almanacCalculator = new AlmanacMinLocationCalculator(nbOfPartitions);
        List<Future<Long>> minValuesFuture = new ArrayList<>();
        int[] partitions = IntStream.rangeClosed(1, nbOfPartitions).toArray();
        for (int i=0; i<partitions.length; i++) {
            Integer currentPartition = partitions[i];
            int indexOfStartNumber = 2*(currentPartition-1);
            minValuesFuture.add(almanacCalculator
                    .getMinLocation(seedRanges.get(indexOfStartNumber), seedRanges.get(indexOfStartNumber+1), almanac));
        }

        long nbOfPartitionsDone;
        do {
            nbOfPartitionsDone = getNbPartitionsDone(minValuesFuture);
            try {
                log.info("Nb of partitions done : {}", nbOfPartitionsDone);
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (nbOfPartitionsDone>0);

        minValuesFuture.stream().forEach(currMin -> {
            try {
                log.info("Current partition, min value is {}", currMin.get());
                minValueFromEachPartition.add(currMin.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        return "" + minValueFromEachPartition.stream().mapToLong(Long::valueOf).min().getAsLong();
    }

    private long getNbPartitionsDone(List<Future<Long>> futureList) {
        return futureList.stream().filter(currentFutureTask -> currentFutureTask.isDone()).count();
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
