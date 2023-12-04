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
        String expectedResponse = "509115";

        String response = day03Component.resolveFirstStar("input-day03-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "54649";

        String response = day03Component.resolveSecondStar("input-day03-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "4361";

        String response = day03Component.resolveFirstStar("test-input-day03-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "467835";

        String response = day03Component.resolveSecondStar("test-input-day03-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
