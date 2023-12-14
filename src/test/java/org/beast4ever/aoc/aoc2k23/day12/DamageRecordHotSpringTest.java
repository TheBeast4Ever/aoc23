package org.beast4ever.aoc.aoc2k23.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class DamageRecordHotSpringTest {
    @Test
    public void whenInlineRawData_thenRecordsBuiltOK() {
        String inlineData = """
                #.#.### 1,1,3
                .#...#....###. 1,1,3
                .#.###.#.###### 1,3,1,6
                ####.#...#... 4,1,1
                #....######..#####. 1,6,5
                .###.##....# 3,2,1
                """;
        List<DamageRecordHotSpring> records = new ArrayList<>();

        inlineData.lines().forEach(line -> {
            StringTokenizer tokenize = new StringTokenizer(line, " ");
            records.add(new DamageRecordHotSpring(tokenize.nextToken(), tokenize.nextToken()));
        });

        Assertions.assertEquals(6, records.size());
    }

    @Test
    public void whenSimpleRecords_thenNbOfArrangementsOK() {
        DamageRecordHotSpring record1 = new DamageRecordHotSpring("???.###", "1,1,3");
        /*
        DamageRecordHotSpring record2 = new DamageRecordHotSpring(".??..??...?##.", "1,1,3");
        DamageRecordHotSpring record3 = new DamageRecordHotSpring("?#?#?#?#?#?#?#?", "1,3,1,6");
        DamageRecordHotSpring record4 = new DamageRecordHotSpring("????.#...#...", "4,1,1");
        DamageRecordHotSpring record5 = new DamageRecordHotSpring("????.######..#####.", "1,6,5");
        DamageRecordHotSpring record6 = new DamageRecordHotSpring("?###????????", "3,2,1");
        */
        Assertions.assertEquals(1, record1.getNumberOfPossibleDamagesArrangements());
        // Assertions.assertEquals(4, record2.getNumberOfPossibleDamagesArrangements());
        // Assertions.assertEquals(1, record3.getNumberOfPossibleDamagesArrangements());
        // Assertions.assertEquals(1, record4.getNumberOfPossibleDamagesArrangements());
        // Assertions.assertEquals(4, record5.getNumberOfPossibleDamagesArrangements());
        // Assertions.assertEquals(10, record6.getNumberOfPossibleDamagesArrangements());
    }

    @Test
    public void whenConsecutiveDamagesList_thenBuildRegexpOK() {
        String damagesDef = "1,1,3";
        String sample = "#.#.###";
        Pattern pattern1 = Pattern.compile("[.]*[#]{1}[.]+[#]{1}[.]+[#]{3}[.]*");

        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(damagesDef, ",");
        sb.append("[.]*");
        do {
            int currNbOfConsecutiveDamages = Integer.parseInt(tokenizer.nextToken());
            sb.append("[#]{"+currNbOfConsecutiveDamages+"}");
            if (tokenizer.hasMoreTokens()) {
                sb.append("[.]+");
            } else {
                sb.append("[.]*");
            }
        } while (tokenizer.hasMoreTokens());

        Pattern pattern2 = Pattern.compile(sb.toString());

        Assertions.assertEquals(true, pattern1.matcher(sample).matches());
        Assertions.assertEquals(true, pattern2.matcher(sample).matches());

    }
}
