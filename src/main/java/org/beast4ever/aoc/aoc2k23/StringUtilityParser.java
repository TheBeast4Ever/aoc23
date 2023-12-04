package org.beast4ever.aoc.aoc2k23;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringUtilityParser {
    public static List<Integer> parseListOfIntegers(String textToParse, String delimiter) {
        List<Integer> listToReturn = new ArrayList<>();
        StringTokenizer tokenize = new StringTokenizer(textToParse, delimiter);
        while (tokenize.hasMoreTokens()) {
            listToReturn.add(Integer.parseInt(tokenize.nextToken()));
        }

        return listToReturn;
    }
}
