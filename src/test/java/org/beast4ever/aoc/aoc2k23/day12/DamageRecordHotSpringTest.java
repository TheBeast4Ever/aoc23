package org.beast4ever.aoc.aoc2k23.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void whenRecordPart1_thenDetermineConsecutiveDamagesOK() {
        String recordPart1 = ".###..#?????";
        int indexOfFirstUnknownValue = recordPart1.indexOf('?');
        List<Integer> part2 = DamageRecordHotSpring.determineConsecutiveDamagesList(recordPart1.substring(0,indexOfFirstUnknownValue));

        Assertions.assertEquals(Arrays.asList(3, 1), part2);
    }

    @Test
    public void whenUnfoldBigRecords_thenNbOfArrangementsOK() {
        DamageRecordHotSpring record1 = new DamageRecordHotSpring("???.?#??????", "1,5,1");
        record1.unfold();
        DamageRecordHotSpring record2 = new DamageRecordHotSpring("?.????????#???", "1,2,2");
        record2.unfold();

        Long result1 = record1.computeRecursivelyNbOfPossibilities("");
        Assertions.assertEquals(712044, result1);
        // Long result2 = record2.getNumberOfPossibleDamagesArrangements(); // OoM l'arbre
        // Assertions.assertEquals(1, result2);
    }


    @Test
    public void whenTwoDamagesListNumbers_thenControlOfValidityOK() {
        List<Integer> list1 = Arrays.asList(4,1);
        List<Integer> list2 = Arrays.asList(1);
        List<Integer> list3 = Arrays.asList(1,1);
        List<Integer> list4 = Arrays.asList(3,1);
        List<Integer> list5 = Arrays.asList(3,3);
        List<Integer> list6 = Arrays.asList(3,1,1);
        List<Integer> list7 = Arrays.asList(3,2);
        List<Integer> list8 = Arrays.asList(3,2,2);
        List<Integer> list9 = Arrays.asList(3,2,1);
        List<Integer> list10 = Arrays.asList(3,4,1);
        List<Integer> list11 = Arrays.asList(1,3,1,1);

        DamageRecordHotSpring record6 = new DamageRecordHotSpring("?###????????", "3,2,2");
        DamageRecordHotSpring record3 = new DamageRecordHotSpring("?#?#?#?#?#?#?#?", "1,3,1,6");

        Assertions.assertTrue(record6.isValidConsecutiveDamageList(list2));
        Assertions.assertTrue(record6.isValidConsecutiveDamageList(list4));
        Assertions.assertTrue(record6.isValidConsecutiveDamageList(list7));
        Assertions.assertTrue(record6.isValidConsecutiveDamageList(list8));
        Assertions.assertTrue(record6.isValidConsecutiveDamageList(list9));
        Assertions.assertTrue(record3.isValidConsecutiveDamageList(list11));

        Assertions.assertFalse(record6.isValidConsecutiveDamageList(list1));
        Assertions.assertFalse(record6.isValidConsecutiveDamageList(list3));
        Assertions.assertFalse(record6.isValidConsecutiveDamageList(list5));
        Assertions.assertFalse(record6.isValidConsecutiveDamageList(list6));
        Assertions.assertFalse(record6.isValidConsecutiveDamageList(list10));

    }
}
