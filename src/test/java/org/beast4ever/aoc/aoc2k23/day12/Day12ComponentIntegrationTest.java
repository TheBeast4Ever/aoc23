package org.beast4ever.aoc.aoc2k23.day12;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.day11.Day11Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class Day12ComponentIntegrationTest {
    @Autowired
    Day12Component dayComponent;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "7163";

        String response = dayComponent.resolveFirstStar("input-day12-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "21";

        String response = dayComponent.resolveFirstStar("test-input-day12-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "17788038834112";

        String response = dayComponent.resolveSecondStar("input-day12-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "525152";

        String response = dayComponent.resolveSecondStar("test-input-day12-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

}
