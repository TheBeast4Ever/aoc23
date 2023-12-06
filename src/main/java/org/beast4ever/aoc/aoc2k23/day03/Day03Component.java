package org.beast4ever.aoc.aoc2k23.day03;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.beast4ever.aoc.aoc2k23.StringUtilityParser;
import org.beast4ever.aoc.aoc2k23.day01.Trebuchet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Integer> gears = new ArrayList<>();
        try {
            List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
            int rowsSize = lines.get(0).length();
            String contentFile = fileUtilityParser.readAllContentFile(ClassLoader.getSystemResource(inputFilePath).toURI());

            char[] flatGrid = contentFile.replaceAll("\n", "").replaceAll("\r", "").toCharArray();
            for (int x=0; x<flatGrid.length; x++) {
                if (flatGrid[x]=='*') {
                    List<Integer> adjacentCells = getAdjacentIndicesFromPreviousAndNextRows(x, flatGrid, rowsSize);
                    log.info("Nb of adj cells : {}", adjacentCells.size());
                    List<Integer> potentialGears = new ArrayList<>();
                    adjacentCells.stream().forEach(currentPosition -> {
                        if (Character.isDigit(flatGrid[currentPosition])) {
                            Integer potentialGearNumber = readEntireNumberFromPosition(flatGrid, currentPosition);
                            potentialGears.add(potentialGearNumber);
                        }
                    });
                    if (potentialGears.size()==2) {
                        gears.add(potentialGears.get(0)*potentialGears.get(1));
                    }
                }
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

       return "" + gears.stream().mapToInt(Integer::valueOf).sum();
    }


    private List<Integer> getAdjacentIndicesFromPreviousAndNextRows(int index, char flatGrid[], int rowsSize) {
        List<Integer> listOfIndicesToReturn = new ArrayList<>();
        if (index>=0 && index < flatGrid.length) {
            int startIndexInPreviousRow = index - (rowsSize+1);
            int endIndexInPreviousRow = index - (rowsSize-1);
            boolean outOfBounds = false;
            boolean digitAlreadyAdded = false;
            int x=endIndexInPreviousRow;
            if (x>=0 && x < flatGrid.length) {
                do {
                    if (Character.isDigit(flatGrid[x]) && !digitAlreadyAdded) {
                        listOfIndicesToReturn.add(x);
                        digitAlreadyAdded=true;
                    }

                    if (!Character.isDigit(flatGrid[x]) && digitAlreadyAdded) {
                        digitAlreadyAdded=false;
                    }

                    x--;
                    if (x<0) {
                        outOfBounds = true;
                    }
                } while (!outOfBounds && x>=startIndexInPreviousRow);
            }

            int startIndexInCurrentRow = index + 1;
            int endIndexInCurrentRow = index -1;
            outOfBounds = false;
            x=startIndexInCurrentRow;
            if (x>=0 && x < flatGrid.length) {
                digitAlreadyAdded=false;
                do {
                    if (Character.isDigit(flatGrid[x]) && !digitAlreadyAdded) {
                        listOfIndicesToReturn.add(x);
                        digitAlreadyAdded=true;
                    }

                    if (!Character.isDigit(flatGrid[x]) && digitAlreadyAdded) {
                        digitAlreadyAdded=false;
                    }
                    x--;
                    if (x<0) {
                        outOfBounds = true;
                    }
                } while (!outOfBounds && x>=endIndexInCurrentRow);
            }

            int startIndexInNextRow = index + (rowsSize-1);
            int endIndexInNextRow = index + (rowsSize+1);
            outOfBounds = false;
            x=startIndexInNextRow;
            if (x>=0 && x < flatGrid.length) {
                digitAlreadyAdded=false;
                do {
                    if (Character.isDigit(flatGrid[x]) && !digitAlreadyAdded) {
                        listOfIndicesToReturn.add(x);
                        digitAlreadyAdded=true;
                    }

                    if (!Character.isDigit(flatGrid[x]) && digitAlreadyAdded) {
                        digitAlreadyAdded=false;
                    }
                    x++;
                    if (x==flatGrid.length) {
                        outOfBounds = true;
                    }
                } while (!outOfBounds && x<=endIndexInNextRow);
            }

        }
        return listOfIndicesToReturn;
    }

    private Integer readEntireNumberFromPosition(char[] flatGrid, int position) {
        if (position>=0 && position<flatGrid.length && Character.isDigit(flatGrid[position])) {
            boolean goLeft=true;
            boolean goRight=true;
            int x=position;
            List<Character> charsList = new ArrayList<>();
            charsList.add(flatGrid[x]);
            while(goLeft) {
                x--;
                if (x>=0 && Character.isDigit(flatGrid[x])) {
                    charsList.add(0, flatGrid[x]);
                } else {
                    goLeft=false;
                }
            }
            x=position;
            while(goRight) {
                x++;
                if (x<flatGrid.length && Character.isDigit(flatGrid[x])) {
                    charsList.add(flatGrid[x]);
                } else {
                    goRight=false;
                }
            }
            return Integer.parseInt(charsList.stream().map(String::valueOf).collect(Collectors.joining()));
        } else {
            return null;
        }
    }
}
