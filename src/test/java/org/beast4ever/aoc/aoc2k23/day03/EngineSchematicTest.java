package org.beast4ever.aoc.aoc2k23.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EngineSchematicTest {

    @Test
    public void whenInlineData_thenBuildStructureOK() {
        List<String> inlineEntries = Arrays.asList(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598..");

        EngineSchematic engineSchematic = new EngineSchematic(10,10);
        engineSchematic.addAllLines(inlineEntries);
        Assertions.assertEquals(100, engineSchematic.getTotalPartsList().stream().count());

    }

    @Test
    public void whenInlineData_thenGetPartsListOK() {
        List<String> inlineEntries = Arrays.asList(
                "467..114..",
                "...*......",
                "..35..633.",
                "......#...",
                "617*......",
                ".....+.58.",
                "..592.....",
                "......755.",
                "...$.*....",
                ".664.598..");
        List<Integer> expectedResult = Arrays.asList(467,35,633,617,592,755,664,598);
        EngineSchematic engineSchematic = new EngineSchematic(10,10);
        engineSchematic.addAllLines(inlineEntries);
        List<Integer> result = engineSchematic.getEnginePartsList();
        Assertions.assertEquals(expectedResult, result);

    }
}
