package org.beast4ever.aoc.aoc2k23.day11;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.day03.Point;
import org.paukov.combinatorics.CombinatoricsFactory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Universe {

    public static final Integer SIMPLE_EXPANSION_COEFF = 1;

    public static final Integer COMPLEX_EXPANSION_COEFF = 1000000;

    private List<Galaxy> galaxies;

    private Integer expandMultiplier;

    private char[][] internalRepresentation;

    public Universe(List<String> lines) {
        this(lines, SIMPLE_EXPANSION_COEFF);
    }

    public Universe(List<String> lines, Integer expansionCoeff) {
        this.expandMultiplier = expansionCoeff;
        Integer nbOfRows = lines.size();
        Integer nbOfCols = lines.get(0).length();
        this.internalRepresentation = new char[nbOfRows][nbOfCols];
        for (int row=0; row < nbOfRows; row++) {
            for (int col=0; col < nbOfCols; col++) {
                this.internalRepresentation[row][col] = lines.get(row).charAt(col);
            }
        }
        galaxies = new ArrayList<>();
    }


    public Integer getNonExpandedWidth() {
        return internalRepresentation[0].length;
    }

    public Integer getNonExpandedLength() {
        return internalRepresentation.length;
    }

    public List<Galaxy> scanGalaxies() {
        int x=0;
        int y=0;
        for (int row=0; row <getNonExpandedLength(); row++) {
            x=0;
            if (rowContainsGalaxy(row)) {
                for(int col=0; col<getNonExpandedWidth(); col++) {
                    if (columnContainsGalaxy(col)) {
                        if (cellContainsGalaxy(row, col)) {
                            Point p=new Point(x,y);
                            Galaxy galaxy = new Galaxy(p);
                            galaxies.add(galaxy);
                        }
                    } else {
                        x=x + ((expandMultiplier==1)?expandMultiplier:expandMultiplier-1);
                    }
                    x++;
                }

            } else {
                y= y + ((expandMultiplier==1)?expandMultiplier:expandMultiplier-1);
            }
            y++;
        }
        return galaxies;
    }

    public Long computeShortestPathSumBetweenGalaxies() {
        Long sumOfShortestPaths = 0l;

        ICombinatoricsVector<Galaxy> vector = CombinatoricsFactory.createVector(galaxies.toArray(new Galaxy[0]));
        Generator<Galaxy> combinationGalaxies = CombinatoricsFactory.createSimpleCombinationGenerator(vector, 2);
        log.info("Compute shortest path on {} combinations", combinationGalaxies.getNumberOfGeneratedObjects());
        for (ICombinatoricsVector<Galaxy> currCombination : combinationGalaxies) {
            Galaxy galaxySource = currCombination.getValue(0);
            Galaxy galaxyDest = currCombination.getValue(1);
            sumOfShortestPaths += galaxySource.getShortestPathToReach(galaxyDest);
        }

        log.info("Result is {}", sumOfShortestPaths);
        return sumOfShortestPaths;
    }

    private boolean cellContainsGalaxy(int rowId, int colId) {
        return internalRepresentation[rowId][colId] == '#';
    }

    private boolean rowContainsGalaxy(int rowId) {
        boolean found=false;
        for(int col=0; col<getNonExpandedWidth(); col++) {
            if (internalRepresentation[rowId][col] == '#') {
                found = true;
            }
        }
        return found;
    }


    private boolean columnContainsGalaxy(int columnId) {
        boolean found=false;
        for(int row=0; row<getNonExpandedLength(); row++) {
            if (internalRepresentation[row][columnId] == '#') {
                found = true;
            }
        }
        return found;
    }

}
