package org.beast4ever.aoc.aoc2k23.day09;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component
@Slf4j
public class Day09Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<OasisMetric> metrics = new ArrayList<>();

        lines.forEach(line -> metrics.add(new OasisMetric(line)));

        metrics.forEach(m -> m.addValue(m.getNextValue()));

        return "" + metrics.stream().mapToLong(OasisMetric::getLastMetric).sum();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<OasisMetric> metrics = new ArrayList<>();

        lines.forEach(line -> metrics.add(new OasisMetric(line)));

        metrics.forEach(m -> m.addFirstValue(m.getPreviousValue()));

        return "" + metrics.stream().mapToLong(OasisMetric::getFirstMetric).sum();
    }

}
