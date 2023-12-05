package org.beast4ever.aoc.aoc2k23.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day05ComponentIntegrationTest {
    @Autowired
    Day05Component day05Component;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "551761867";

        String response = day05Component.resolveFirstStar("input-day05-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "9236992";

        String response = day05Component.resolveSecondStar("input-day05-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "35";

        String response = day05Component.resolveFirstStar("test-input-day05-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "46";

        String response = day05Component.resolveSecondStar("test-input-day05-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
