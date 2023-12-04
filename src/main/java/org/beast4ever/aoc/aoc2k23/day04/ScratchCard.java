package org.beast4ever.aoc.aoc2k23.day04;

import org.beast4ever.aoc.aoc2k23.StringUtilityParser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ScratchCard {
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private Integer id;

    private List<Integer> winningNumbers = new ArrayList<>();

    private List<Integer> playedNumbers = new ArrayList<>();

    public Integer getEarnedPoints() {
        return earnedPoints;
    }

    private Integer earnedPoints;

    public Integer getNbOfCopyOfNextScratchCardsEarned() {
        return nbOfCopyOfNextScratchCardsEarned;
    }

    private Integer nbOfCopyOfNextScratchCardsEarned;

    public ScratchCard(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
        earnedPoints = 0;
        nbOfCopyOfNextScratchCardsEarned=0;
    }


    public void playNumber(Integer number) {
        if (!playedNumbers.contains(number)) {
            playedNumbers.add(number);
            if (winningNumbers.contains(number)) {
                nbOfCopyOfNextScratchCardsEarned++;
                if (earnedPoints==0) {
                    earnedPoints = 1;
                } else {
                    earnedPoints = earnedPoints * 2;
                }
            }
        }
    }

    public void playNumbers(List<Integer> numbers) {
        numbers.forEach(number -> playNumber(number));
    }

    public static ScratchCard parseAndPlaycratchCard(String textEntry) {
        StringTokenizer tokenize = new StringTokenizer(textEntry, "|");
        String firstToken = tokenize.nextToken();
        Integer indexOfPunctuation = firstToken.indexOf(":");
        Integer id = Integer.parseInt(firstToken.substring(4, indexOfPunctuation).trim());
        String strListWinningNumbers = firstToken.substring(indexOfPunctuation+1).trim();
        List<Integer> winningNumbers = StringUtilityParser.parseListOfIntegers(strListWinningNumbers, " ");
        ScratchCard scratchCard = new ScratchCard(winningNumbers);
        scratchCard.setId(id);

        String secondToken = tokenize.nextToken();
        List<Integer> playedNumbers = StringUtilityParser.parseListOfIntegers(secondToken, " ");
        scratchCard.playNumbers(playedNumbers);
        return scratchCard;
    }

}
