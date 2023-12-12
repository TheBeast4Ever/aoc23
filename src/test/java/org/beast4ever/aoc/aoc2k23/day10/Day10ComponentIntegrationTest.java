package org.beast4ever.aoc.aoc2k23.day10;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.day09.Day09Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class Day10ComponentIntegrationTest {
    @Autowired
    Day10Component dayComponent;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "1853145119";

        String response = dayComponent.resolveFirstStar("input-day10-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTest1DataFile_thenFindResult() throws IOException {
        String expectedResponse = "4";

        String response = dayComponent.resolveFirstStar("test-input-day10-S1-1.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "8";

        String response = dayComponent.resolveSecondStar("input-day10-S1-2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenStarTwoTestInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "2";

        String response = dayComponent.resolveSecondStar("test-input-day10-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }




}
