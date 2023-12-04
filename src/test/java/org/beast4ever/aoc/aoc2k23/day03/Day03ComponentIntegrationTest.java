package org.beast4ever.aoc.aoc2k23.day03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day03ComponentIntegrationTest {
    @Autowired
    Day03Component day03Component;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "54081";

        String response = day03Component.resolveFirstStar("input-day01-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "54649";

        String response = day03Component.resolveSecondStar("input-day01-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
