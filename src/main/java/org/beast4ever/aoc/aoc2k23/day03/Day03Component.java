package org.beast4ever.aoc.aoc2k23.day03;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class Day03Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);

        EngineSchematic engineSchematic = new EngineSchematic(lines.size(),lines.get(0).length());
        engineSchematic.addAllLines(lines);

        Character[][] data = engineSchematic.getData();
        List<Integer> engineParts = new ArrayList<>();

        for(int y=0; y<data.length; y++) {
            StringBuilder sb = new StringBuilder();
            Boolean addToEngineParts = false;
            for(int x=0; x<data[y].length; x++) {
                if (StringUtilityParser.isDigit(data[y][x])) {
                    sb.append(data[y][x]);
                    Point pointToCompare = new Point(x,y);

                    List<Point> adjacentsPoints = pointToCompare.getAdjacentPointsWithOutOfBoundsControl(0, data[y].length-1, 0, data.length-1);
                    Long nbAdjSymbol = adjacentsPoints.stream().filter(adjPoint -> StringUtilityParser.isSymbol(data[adjPoint.y()][adjPoint.x()])).count();
                    if (nbAdjSymbol>0) {
                        addToEngineParts = true;
                    }
                    if (addToEngineParts && !sb.isEmpty() && x==data[y].length-1) {
                        engineParts.add(Integer.parseInt(sb.toString()));
                        sb = new StringBuilder();
                        addToEngineParts = false;
                    }
                } else {
                    if (addToEngineParts && !sb.isEmpty()) {
                        engineParts.add(Integer.parseInt(sb.toString()));
                    }
                    sb = new StringBuilder();
                    addToEngineParts = false;
                }
            }
        }

        return "" + engineParts.stream().mapToLong(Long::valueOf).sum();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
       return unimplementedResolution(inputFilePath);
    }
}
