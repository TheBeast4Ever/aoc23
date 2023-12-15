package org.beast4ever.aoc.aoc2k23;

import org.beast4ever.aoc.aoc2k23.RegexpUtilityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

public class RegexpUtilityServiceTest {
    @Test
    public void whenTwoRegexp_thenOneIncludedInOther() {
        Pattern pattern1 = Pattern.compile("[.]*[#]{3}[.]+[#]{2}[.]+[#]{1}[.]*");
        Pattern pattern2 = Pattern.compile("[.]{1}[#]{3}[.]{1}[#]{1}");

        String recordPart1 = ".###..#?????";



        RegexpUtilityService regexpService = new RegexpUtilityService();
        Pattern regexpToCompare = regexpService.computeRegexpForConsecutiveListOfSameChar("3,2,1", "#", ".");
    }

    @Test
    public void whenConsecutiveDamagesList_thenBuildRegexpOK() {
        RegexpUtilityService regexpService = new RegexpUtilityService();
        Pattern result = regexpService.computeRegexpForConsecutiveListOfSameChar("1,1,3", "#", ".");
        Pattern expectedResult = Pattern.compile("[.]*[#]{1}[.]+[#]{1}[.]+[#]{3}[.]*");

        Assertions.assertEquals(expectedResult.pattern(), result.pattern());
    }
}
