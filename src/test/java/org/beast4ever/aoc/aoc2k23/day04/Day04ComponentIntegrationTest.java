package org.beast4ever.aoc.aoc2k23.day04;

import org.beast4ever.aoc.aoc2k23.day03.Day03Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day04ComponentIntegrationTest {
    @Autowired
    Day04Component day04Component;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "23028";

        String response = day04Component.resolveFirstStar("input-day04-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "9236992";

        String response = day04Component.resolveSecondStar("input-day04-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "30";

        String response = day04Component.resolveSecondStar("test-input-day04-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
