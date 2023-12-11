package org.beast4ever.aoc.aoc2k23.day11;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.paukov.combinatorics.CombinatoricsFactory;
import org.paukov.combinatorics.CombinatoricsVector;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class Day11Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        Universe universe = new Universe(lines);
        List<Galaxy> galaxies = universe.scanGalaxies();
        Long sumOfShortestPaths = 0l;

        ICombinatoricsVector<Galaxy> vector = CombinatoricsFactory.createVector((Galaxy[]) galaxies.toArray());
        Generator<Galaxy> combinationGalaxies = CombinatoricsFactory.createSimpleCombinationGenerator(vector, 2);
        for (ICombinatoricsVector<Galaxy> currCombination : combinationGalaxies) {
            log.info("Current combination {}", currCombination);
            // TODO
        }

        return "" + sumOfShortestPaths;
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        return unimplementedResolution(inputFilePath);
    }

}
