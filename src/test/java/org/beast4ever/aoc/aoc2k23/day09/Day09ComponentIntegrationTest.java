package org.beast4ever.aoc.aoc2k23.day09;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.day08.Day08Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class Day09ComponentIntegrationTest {
    @Autowired
    Day09Component dayComponent;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "1853145119";

        String response = dayComponent.resolveFirstStar("input-day09-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarOneInputTestDataFile_thenFindResult() throws IOException {
        String expectedResponse = "114";

        String response = dayComponent.resolveFirstStar("test-input-day09-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenStarTwoInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "923";

        String response = dayComponent.resolveSecondStar("input-day09-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenStarTwoTestInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "2";

        String response = dayComponent.resolveSecondStar("test-input-day09-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }




}
