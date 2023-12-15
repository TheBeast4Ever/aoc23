package org.beast4ever.aoc.aoc2k23;

import org.springframework.stereotype.Component;

import java.util.StringTokenizer;
import java.util.regex.Pattern;
@Component
public class RegexpUtilityService {
    public Pattern computeRegexpForConsecutiveListOfSameChar(String consecutiveNbOfSameChar, String character, String otherPossibleChars) {
        StringTokenizer tokenize = new StringTokenizer(consecutiveNbOfSameChar, ",");

        StringBuilder sb = new StringBuilder();
        sb.append("[" + otherPossibleChars + "]*");
        while (tokenize.hasMoreTokens()) {
            int currNbOfConsecutiveDamages = Integer.parseInt(tokenize.nextToken());
            sb.append("[" + character + "]{"+currNbOfConsecutiveDamages+"}");
            if (tokenize.hasMoreTokens()) {
                sb.append("[" + otherPossibleChars + "]+");
            } else {
                sb.append("[" + otherPossibleChars + "]*");
            }
        }

        return Pattern.compile(sb.toString());
    }
}
