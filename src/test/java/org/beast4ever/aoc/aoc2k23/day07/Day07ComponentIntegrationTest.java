package org.beast4ever.aoc.aoc2k23.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day07ComponentIntegrationTest {
    @Autowired
    Day07Component day07Component;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "248812215";

        String response = day07Component.resolveFirstStar("input-day07-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "250057090";

        String response = day07Component.resolveSecondStar("input-day07-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "6440";

        String response = day07Component.resolveFirstStar("test-input-day07-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "5905";

        String response = day07Component.resolveSecondStar("test-input-day07-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
