package org.beast4ever.aoc.aoc2k23.day07;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.beast4ever.aoc.aoc2k23.day06.ToyBoat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.LongStream;

@Component
@Slf4j
public class Day07Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
       return resolveTwoStars(inputFilePath, false);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        return resolveTwoStars(inputFilePath, true);
    }

    private String resolveTwoStars(String inputFilePath, Boolean withJokers) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<CamelCardHand> cardHands = new ArrayList<>();
        for (int i=0; i<lines.size(); i++) {
            cardHands.add(new CamelCardHand(lines.get(i).substring(0,5), Integer.parseInt(lines.get(i).substring(5).trim()), withJokers));
        }
        List<Integer> scores = new ArrayList<>();
        Collections.sort(cardHands);

        for (int rank=1; rank<=cardHands.size(); rank++) {
            scores.add(rank*(cardHands.get(rank-1).getBid()));
        }

        return "" + scores.stream().mapToLong(Long::valueOf).sum();
    }

}
