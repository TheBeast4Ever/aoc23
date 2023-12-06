package org.beast4ever.aoc.aoc2k23.day06;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.beast4ever.aoc.aoc2k23.day05.Almanac;
import org.beast4ever.aoc.aoc2k23.day05.AlmanacMinLocationCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@Slf4j
public class Day06Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;


    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<Long> raceDurations = parseLongValueslines(lines.get(0).substring(6));
        List<Long> bestRecordPerRace = parseLongValueslines(lines.get(1).substring(9));
        long response = 0;

        for(int i=0; i< raceDurations.size(); i++) {
            ToyBoat boat = new ToyBoat();
            int finalI = i;
            long nbOfWinPossibilities = LongStream.rangeClosed(0,raceDurations.get(i)).filter(pushDuration -> {
                boat.pushButton(pushDuration);
                long distanceBrowsed = boat.getDistanceBrowsed(raceDurations.get(finalI)-pushDuration);
                boat.reset();
                return distanceBrowsed>bestRecordPerRace.get(finalI);
            }).count();
            if (nbOfWinPossibilities>0) {
                if (response == 0) {
                    response=1;
                }
                response = response*nbOfWinPossibilities;
            }
        }


        return "" + response;
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        Long raceDuration = parseSingleLongValue(lines.get(0).substring(6));
        Long bestRecord = parseSingleLongValue(lines.get(1).substring(9));

        ToyBoat boat = new ToyBoat();

        long nbOfWinPossibilities = LongStream.rangeClosed(0,raceDuration).filter(pushDuration -> {
            boat.pushButton(pushDuration);
            long distanceBrowsed = boat.getDistanceBrowsed(raceDuration-pushDuration);
            boat.reset();
            return distanceBrowsed>bestRecord;
        }).count();

        return "" + nbOfWinPossibilities;
    }

    private List<Long> parseLongValueslines(String rawText) {
        StringTokenizer token = new StringTokenizer(rawText.trim(), " ");
        List<Long> numbersToReturn = new ArrayList<>();
        while (token.hasMoreTokens()) {
            numbersToReturn.add(Long.parseLong(token.nextToken()));
        }
        return numbersToReturn;
    }

    private Long parseSingleLongValue(String rawText) {
       return Long.parseLong(rawText.trim().replaceAll(" ", ""));
    }

}
