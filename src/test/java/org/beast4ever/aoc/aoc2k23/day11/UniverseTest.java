package org.beast4ever.aoc.aoc2k23.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UniverseTest {
    @Test
    public void whenInlineData_thenBuildUniverseOK() {
        Universe simpleUniverse = buildSimpleInlineUniverseData();
        Universe normalUniverse = buildComplexInlineUniverseData();

        Assertions.assertEquals(10, simpleUniverse.getNonExpandedWidth());
        Assertions.assertEquals(3, simpleUniverse.getNonExpandedLength());
        Assertions.assertEquals(10, normalUniverse.getNonExpandedWidth());
        Assertions.assertEquals(10, normalUniverse.getNonExpandedLength());
    }

    @Test
    public void whenInlineData_thenScanGalaxiesOK() {
        Universe simpleUniverse = buildSimpleInlineUniverseData();
        Universe normalUniverse = buildComplexInlineUniverseData();

        List<Galaxy> simpleGalaxies = simpleUniverse.scanGalaxies();
        List<Galaxy> normalGalaxies = normalUniverse.scanGalaxies();

        Assertions.assertEquals(3, simpleGalaxies.size());
        Assertions.assertEquals(9, normalGalaxies.size());
    }

    @Test
    public void whenInlineData_thenComputeShortestPathsOK() {
        Universe universe = buildComplexInlineUniverseData();

        List<Galaxy> galaxies = universe.scanGalaxies();

        Long sumOfShortestPath = universe.computeShortestPathSumBetweenGalaxies();

        Assertions.assertEquals(374, sumOfShortestPath);
    }

    @Test
    public void whenExpandedInlineData_thenComputeShortestPathsOK() {
        Universe universe1 = buildComplexInlineUniverseData(10);
        Universe universe2 = buildComplexInlineUniverseData(100);

        universe1.scanGalaxies();
        universe2.scanGalaxies();

        Long sumOfShortestPath1 = universe1.computeShortestPathSumBetweenGalaxies();
        Long sumOfShortestPath2 = universe2.computeShortestPathSumBetweenGalaxies();

        Assertions.assertEquals(1030, sumOfShortestPath1);
        Assertions.assertEquals(8410, sumOfShortestPath2);
    }

    private Universe buildSimpleInlineUniverseData() {
        String data = """
                ...#......
                .......#..
                #.........
                """;

        Universe universe = new Universe(data.lines().toList());
        return universe;
    }

    private Universe buildComplexInlineUniverseData() {
        String data = """
                ...#......
                .......#..
                #.........
                ..........
                ......#...
                .#........
                .........#
                ..........
                .......#..
                #...#.....
                """;

        Universe universe = new Universe(data.lines().toList());
        return universe;
    }

    private Universe buildComplexInlineUniverseData(Integer expansionCoeff) {
        String data = """
                ...#......
                .......#..
                #.........
                ..........
                ......#...
                .#........
                .........#
                ..........
                .......#..
                #...#.....
                """;

        Universe universe = new Universe(data.lines().toList(), expansionCoeff);
        return universe;
    }

}
