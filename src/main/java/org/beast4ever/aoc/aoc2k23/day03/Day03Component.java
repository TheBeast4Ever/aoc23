package org.beast4ever.aoc.aoc2k23.day03;

import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.beast4ever.aoc.aoc2k23.StringUtilityParser;
import org.beast4ever.aoc.aoc2k23.day01.Trebuchet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Day03Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);

        EngineSchematic engineSchematic = new EngineSchematic(140,140);
        engineSchematic.addAllLines(lines);

        Character[][] data = engineSchematic.getData();

        for(int y=0; y<data.length; y++) {
            StringBuilder sb = new StringBuilder();
            for(int x=0; x<data[y].length; x++) {
                if (StringUtilityParser.isDigit(data[y][x])) {
                    sb.append(data[y][x]);
                    Point pointToCompare = new Point(x,y);
                    // Recherche des adjcantes
                    List<Point> adjacentsPoints = pointToCompare.getAdjacentPointsWithOutOfBoundsControl(0, data[y].length, 0, data.length);
                    Long nbAdjSymbol = adjacentsPoints.stream().filter(adjPoint -> StringUtilityParser.isSymbol(data[adjPoint.y()][adjPoint.x()])).count();
                    if (nbAdjSymbol>0) {
                        // Mettre bool à true
                    }

                } else {
                    // Mettre en mémoire le nombre et vider
                }
            }
        }


        return unimplementedResolution(inputFilePath);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        return unimplementedResolution(inputFilePath);
    }
}
