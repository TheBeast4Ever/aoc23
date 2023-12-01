package org.beast4ever.aoc.aoc2k23.day01;

import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Day01Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        return unimplementedResolution(inputFilePath);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
       return unimplementedResolution(inputFilePath);
    }

}
