package org.beast4ever.aoc.aoc2k23.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RangeTest {
    @Test
    public void whenMulitplesSource_thenContainsRangeOK() {
        RangeDefinition rangeDef = new RangeDefinition(1, 50l, 98l, 2l);
        List<Long> sources = Arrays.asList(98l, 99l, 97l, 101l, -1l);
        List<Boolean> expectedResponses = Arrays.asList(true, true, false, false, false);
        List<Boolean> responses = new ArrayList<>();

        sources.forEach(source -> responses.add(rangeDef.containsSource(source)));
        Assertions.assertEquals(expectedResponses, responses);
    }

    @Test
    public void whenMulitplesSource_thenGetDestinationOK() {
        RangeDefinition rangeDef = new RangeDefinition(1, 50l, 98l, 2l);
        List<Long> sources = Arrays.asList(98l, 99l, 97l, 101l, -1l);
        List<Long> expectedResponses = Arrays.asList(50l, 51l, -1l, -1l, -1l);
        List<Long> responses = new ArrayList<>();

        sources.forEach(source -> responses.add(rangeDef.getDestination(source)));
        Assertions.assertEquals(expectedResponses, responses);
    }
}
