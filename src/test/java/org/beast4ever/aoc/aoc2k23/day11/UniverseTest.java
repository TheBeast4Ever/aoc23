package org.beast4ever.aoc.aoc2k23.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

        simpleUniverse.scanGalaxies();
        normalUniverse.scanGalaxies();

        Assertions.assertEquals(3, simpleUniverse.getGalaxies().size());
        Assertions.assertEquals(9, normalUniverse.getGalaxies().size());
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

}
