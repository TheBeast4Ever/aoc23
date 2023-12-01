package org.beast4ever.aoc.aoc2k23.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrebuchetTest {
    @Test
    public void whenSomeInlineEntries_thenRecoverCalibrationWithoutWordsNumbersOK() {
        String inlineEntries = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;
        List<Integer> expectedResults = Arrays.asList(12, 38, 15, 77);
        List<Integer> results = new ArrayList<>();

        Trebuchet trebuchet = new Trebuchet();

        inlineEntries.lines().forEach(textEntry -> results.add(trebuchet.recoverCalibrationValue(textEntry)));

        Assertions.assertEquals(expectedResults, results);
        Assertions.assertEquals(142, results.stream().mapToLong(Long::valueOf).sum());
    }


    @Test
    public void whenSomeInlineEntries_thenRecoverCalibrationWithWordsNumbersOK() {
        String inlineEntries = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """;

        List<Integer> expectedResults = Arrays.asList(29, 83, 13, 24, 42, 14, 76);
        List<Integer> results = new ArrayList<>();

        Trebuchet trebuchet = new Trebuchet();

        inlineEntries.lines().forEach(textEntry -> results.add(trebuchet.recoverCalibrationValueWithWordsNumberRecognition(textEntry)));

        Assertions.assertEquals(expectedResults, results);
        Assertions.assertEquals(281, results.stream().mapToLong(Long::valueOf).sum());
    }

    @Test
    public void whenSomeInlineEntries_thenFindFirstIndexOfDigitInWordNumberOK() {
        String inlineEntries = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """;

        List<Integer> expectedResultsFromStart = Arrays.asList(0, 0, 3, 1, 1, 1, 6);
        List<Integer> expectedResultsFromEnd = Arrays.asList(4, 7, 7, 7, 10, 3, 6);
        List<Integer> resultsFromStart = new ArrayList<>();
        List<Integer> resultsFromEnd = new ArrayList<>();

        Trebuchet trebuchet = new Trebuchet();

        inlineEntries.lines().forEach(textEntry -> resultsFromStart.add(trebuchet.findFirstIndexOfDigitInWordNumber(textEntry, false).position()));
        inlineEntries.lines().forEach(textEntry -> resultsFromEnd.add(trebuchet.findFirstIndexOfDigitInWordNumber(textEntry, true).position()));

        Assertions.assertEquals(expectedResultsFromStart, resultsFromStart);
        Assertions.assertEquals(expectedResultsFromEnd, resultsFromEnd);
    }

}
