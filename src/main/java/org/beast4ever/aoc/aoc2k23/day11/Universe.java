package org.beast4ever.aoc.aoc2k23.day11;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Universe {

    private List<Galaxy> galaxies;

    private char[][] internalRepresentation;

    public Universe(List<String> lines) {
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
        for (int row=0; row <getNonExpandedLength(); row++) {
            if (rowContainsGalaxy(row)) {

            } else {
                //
            }
        }
        return galaxies;
    }



    private boolean rowContainsGalaxy(int rowId) {
        boolean found=false;
        for(int col=0; col<internalRepresentation[rowId].length; col++) {
            if (internalRepresentation[rowId][col] == '#') {
                found = true;
            }
        }
        return found;
    }


    private boolean columnContainsGalaxy(int columnId) {
        boolean found=false;
        for(int row=0; row<internalRepresentation[row].length; row++) {
            if (internalRepresentation[row][columnId] == '#') {
                found = true;
            }
        }
        return found;
    }

}
