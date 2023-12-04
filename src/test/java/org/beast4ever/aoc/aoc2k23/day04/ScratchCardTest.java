package org.beast4ever.aoc.aoc2k23.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ScratchCardTest {


    @Test
    public void whenInlineData1_thenScratchCardEarnedPointsOK() {
        List<Integer> winningNumbers = Arrays.asList(41,48,83,86,17);
        List<Integer> playedNumbers = Arrays.asList(83,86,6,31,17,48,53);

        ScratchCard scratcCard = new ScratchCard(winningNumbers);
        playedNumbers.forEach(playedNumber -> scratcCard.playNumber(playedNumber));

        Assertions.assertEquals(8, scratcCard.getEarnedPoints());
    }

    @Test
    public void whenInlineData2_thenScratchCardEarnedPointsOK() {
        List<Integer> winningNumbers = Arrays.asList(13, 32, 20, 16, 61);
        List<Integer> playedNumbers = Arrays.asList(61,30,68,82,17,32,24,19);

        ScratchCard scratcCard = new ScratchCard(winningNumbers);
        playedNumbers.forEach(playedNumber -> scratcCard.playNumber(playedNumber));

        Assertions.assertEquals(2, scratcCard.getEarnedPoints());
    }

    @Test
    public void whenInlineData3_thenScratchCardEarnedPointsOK() {
        List<Integer> winningNumbers = Arrays.asList( 1, 21, 53, 59, 44);
        List<Integer> playedNumbers = Arrays.asList(69,82,63,72,16,21,14,1);

        ScratchCard scratcCard = new ScratchCard(winningNumbers);
        playedNumbers.forEach(playedNumber -> scratcCard.playNumber(playedNumber));

        Assertions.assertEquals(2, scratcCard.getEarnedPoints());
    }

    @Test
    public void whenInlineData4_thenScratchCardEarnedPointsOK() {
        List<Integer> winningNumbers = Arrays.asList(41,92,73,84,69);
        List<Integer> playedNumbers = Arrays.asList(59,84,76,51,58,5,54,83);

        ScratchCard scratcCard = new ScratchCard(winningNumbers);
        playedNumbers.forEach(playedNumber -> scratcCard.playNumber(playedNumber));

        Assertions.assertEquals(1, scratcCard.getEarnedPoints());
    }

    @Test
    public void whenInlineData5_thenScratchCardEarnedPointsOK() {
        List<Integer> winningNumbers = Arrays.asList(87,83,26,28,32);
        List<Integer> playedNumbers = Arrays.asList(88,30,70,12,93,22,82,36);

        ScratchCard scratcCard = new ScratchCard(winningNumbers);
        playedNumbers.forEach(playedNumber -> scratcCard.playNumber(playedNumber));

        Assertions.assertEquals(0, scratcCard.getEarnedPoints());
    }

    @Test
    public void whenInlineData6_thenScratchCardEarnedPointsOK() {
        List<Integer> winningNumbers = Arrays.asList(31,18,13,56,72);
        List<Integer> playedNumbers = Arrays.asList(74,77,10,23,35,67,36,11);

        ScratchCard scratcCard = new ScratchCard(winningNumbers);
        playedNumbers.forEach(playedNumber -> scratcCard.playNumber(playedNumber));

        Assertions.assertEquals(0, scratcCard.getEarnedPoints());
    }

    @Test
    public void whenInlineData1_thenScratchCardEarnedCardsOK() {
        List<Integer> winningNumbers = Arrays.asList(41,48,83,86,17);
        List<Integer> playedNumbers = Arrays.asList(83,86,6,31,17,48,53);

        ScratchCard scratcCard = new ScratchCard(winningNumbers);
        playedNumbers.forEach(playedNumber -> scratcCard.playNumber(playedNumber));

        Assertions.assertEquals(8, scratcCard.getEarnedPoints());
    }
}
