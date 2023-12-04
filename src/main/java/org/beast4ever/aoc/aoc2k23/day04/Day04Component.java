package org.beast4ever.aoc.aoc2k23.day04;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class Day04Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> scratchCardsEntries = fileUtilityParser.readFileSplitByLines(inputFilePath);
        Integer totalEarnedPoints = 0;

        for(int i=0; i<scratchCardsEntries.size(); i++) {
            ScratchCard scratchCard = ScratchCard.parseAndPlaycratchCard(scratchCardsEntries.get(i));
            totalEarnedPoints += scratchCard.getEarnedPoints();
        }

        return "" + totalEarnedPoints;
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> scratchCardsEntries = fileUtilityParser.readFileSplitByLines(inputFilePath);

        Map<Integer, Integer> cardsToPLay = new HashMap<>();

        for(int i=1; i<=scratchCardsEntries.size(); i++) {
            cardsToPLay.put(i, (cardsToPLay.containsKey(i))?cardsToPLay.get(i)+1:1);
            ScratchCard scratchCard = ScratchCard.parseAndPlaycratchCard(scratchCardsEntries.get(i-1));
            Integer nbOfCopiesEarned = scratchCard.getNbOfCopyOfNextScratchCardsEarned();
            if (scratchCard.getNbOfCopyOfNextScratchCardsEarned()>0) {
                for(int j=i+1; j<=i+nbOfCopiesEarned; j++) {
                    cardsToPLay.put(j, (cardsToPLay.containsKey(j))?cardsToPLay.get(j)+cardsToPLay.get(i):cardsToPLay.get(i));
                }
            }
        }

        return "" +  cardsToPLay.values().stream().mapToLong(Long::valueOf).sum();
    }

    private Map<Integer, List<String>> convertToMapScratchCardsEntries(List<String> scratchCardsEntries) {
        Map<Integer, List<String>> mapToBuild = new HashMap<>();
        for(int i=0; i<scratchCardsEntries.size(); i++) {
            mapToBuild.put(i+1, Arrays.asList(scratchCardsEntries.get(i)));
        }
        return mapToBuild;
    }

    private Map<Integer, String> getCopiesOfScratchCardEntries(List<String> scratchCardsEntries, Integer from, Integer to) {
        Map<Integer, String> copiesToAdd = new HashMap<>();
        for (int i=from; i<to; i++) {
            if (i< scratchCardsEntries.size()) {
                String currentScratchCardEntry = scratchCardsEntries.get(i);
                copiesToAdd.put(i+1, currentScratchCardEntry);
            }
        }

       return copiesToAdd;
    }

}
