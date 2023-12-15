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

    public static int compareStrings(String s1, String s2) {
        int comparison = 0;
        int c1, c2;
        for(int i = 0; i < s1.length() && i < s2.length(); i++) {
            c1 = (int) s1.toLowerCase().charAt(i);   // See note 1
            c2 = (int) s2.toLowerCase().charAt(i);   // See note 1
            comparison = c1 - c2;   // See note 2

            if(comparison != 0)     // See note 3
                return comparison;
        }
        if(s1.length() > s2.length())    // See note 4
            return 1;
        else if (s1.length() < s2.length())
            return -1;
        else
            return 0;
    }

    public static boolean isDot(Character character) {
        return character == '.' ;
    }

    public static boolean isSymbol(Character character) {
        return !isDot(character) && !Character.isDigit(character) ;
    }

    public static boolean isStar(Character character) {
        return character == '*';
    }

    public static boolean isDigit(Character character) {
        return Character.isDigit(character);
    }
}
