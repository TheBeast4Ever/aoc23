package org.beast4ever.aoc.aoc2k23.day01;
import org.beast4ever.aoc.aoc2k23.day01.Day01Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Day01ComponentIntegrationTest {
    @Autowired
    Day01Component day01Component;

    @Test
    public void whenStarOneInputDataFile_thenFindResult() throws IOException {
        String expectedResponse = "74";

        String response = day01Component.resolveFirstStar("input-day01-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
