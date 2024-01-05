package org.beast4ever.aoc.aoc2k23.day12;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.beast4ever.aoc.aoc2k23.day11.Universe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
@Slf4j
public class Day12Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
       List<Long> arrangements = new ArrayList<>();

       lines.forEach(l -> {
           StringTokenizer tokenize = new StringTokenizer(l," ");
           DamageRecordHotSpring record = new DamageRecordHotSpring(tokenize.nextToken(), tokenize.nextToken());
           String knownPart = record.getNextKnownPart("");
           arrangements.add(record.computeRecursivelyNbOfPossibilities(knownPart));
       });

       arrangements.stream().forEach(a -> log.info("" + a));

        return "" + arrangements.stream().mapToLong(Long::valueOf).sum();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<Long> arrangements = new ArrayList<>();

        lines.forEach(l -> {
            StringTokenizer tokenize = new StringTokenizer(l," ");
            DamageRecordHotSpring record = new DamageRecordHotSpring(tokenize.nextToken(), tokenize.nextToken());
            record.unfold();
            String knownPart = record.getNextKnownPart("");
            arrangements.add(record.computeRecursivelyNbOfPossibilities(knownPart));
            log.info("Arrangement calculated");
        });

        return "" + arrangements.stream().mapToLong(Long::valueOf).sum();
    }

}
