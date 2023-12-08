package org.beast4ever.aoc.aoc2k23.day08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day08ComponentIntegrationTest {
    @Autowired
    Day08Component day08Component;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "18673";

        String response = day08Component.resolveFirstStar("input-day08-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "250057090";

        String response = day08Component.resolveSecondStar("input-day08-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenStarTwoTestInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "6";

        String response = day08Component.resolveSecondStar("test-input-day08-S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTest1DataFile_thenFindResult() throws IOException {
        String expectedResponse = "2";

        String response = day08Component.resolveFirstStar("test-input-day08-S1-1.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTest2DataFile_thenFindResult() throws IOException {
        String expectedResponse = "6";

        String response = day08Component.resolveFirstStar("test-input-day08-S1-2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
