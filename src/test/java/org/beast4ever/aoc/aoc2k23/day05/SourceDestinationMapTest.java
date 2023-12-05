package org.beast4ever.aoc.aoc2k23.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SourceDestinationMapTest {
    @Test
    public void whenSourceDestinationMap_thenRangeDefFound() {
        String inlineSourceDestinationMap = """
                50 98 2
                52 50 48
                """;
        SourceDestinationMap map = new SourceDestinationMap("seed-to-soil", inlineSourceDestinationMap.lines().toList());
        List<Long> sources = Arrays.asList(79l, 99l);
        List<Integer> expectedResponses = Arrays.asList(2, 1);
        List<Integer> responses = new ArrayList<>();
        sources.forEach(source -> responses.add(map.getRangeDefForSource(source).getId()));
        Assertions.assertEquals(expectedResponses, responses);
    }

    @Test
    public void whenSourceDestinationMap_thenDestinationFoundOK() {
        String inlineSourceDestinationMap = """
                50 98 2
                52 50 48
                """;
        SourceDestinationMap map = new SourceDestinationMap("seed-to-soil", inlineSourceDestinationMap.lines().toList());
        List<Long> sources = Arrays.asList(79l, 99l);
        List<Long> expectedResponses = Arrays.asList(81l, 51l);
        List<Long> responses = new ArrayList<>();
        sources.forEach(source -> responses.add(map.getDestinationForSource(source)));
        Assertions.assertEquals(expectedResponses, responses);
    }
}
