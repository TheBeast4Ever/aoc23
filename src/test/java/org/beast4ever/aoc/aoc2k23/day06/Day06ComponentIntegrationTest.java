package org.beast4ever.aoc.aoc2k23.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day06ComponentIntegrationTest {
    @Autowired
    Day06Component day06Component;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "449550";

        String response = day06Component.resolveFirstStar("input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "28360140";

        String response = day06Component.resolveSecondStar("input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "288";

        String response = day06Component.resolveFirstStar("test-input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "71503";

        String response = day06Component.resolveSecondStar("test-input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
