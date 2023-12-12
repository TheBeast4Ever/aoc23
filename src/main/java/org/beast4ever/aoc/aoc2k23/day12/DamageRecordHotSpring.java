package org.beast4ever.aoc.aoc2k23.day12;

import lombok.extern.slf4j.Slf4j;


import java.util.*;

@Slf4j
public class DamageRecordHotSpring {
    private String rawRecord;
    private List<Integer> consecutiveDamagedSpringGroups;

    public DamageRecordHotSpring(String rawRecord, String groupsCount) {
        this.rawRecord = rawRecord;
        StringTokenizer tokenize = new StringTokenizer(groupsCount, ",");
        consecutiveDamagedSpringGroups = new ArrayList<>();
        while (tokenize.hasMoreTokens()) {
            consecutiveDamagedSpringGroups.add(Integer.parseInt(tokenize.nextToken()));
        }
    }


    public Long getNumberOfPossibleDamagesArrangements() {
        Node rootNode = new Node('?');
        BinaryTree tree = new BinaryTree(rootNode);
        tree.addRecursiveLeftAndRight(rootNode, '#', '.', nbOfInterrogationPoints());
        return tree.getNbOfLeaves(rootNode);
    }


    private Integer nbOfInterrogationPoints() {
        int indexOfNextInterrogationPoint=-1;
        int startIndex;
        String subRawRecord = rawRecord;
        int nbToReturn=0;
        do {
            startIndex=indexOfNextInterrogationPoint+1;
            indexOfNextInterrogationPoint = subRawRecord.indexOf('?', startIndex);
            if (indexOfNextInterrogationPoint != -1) {
                nbToReturn++;
            }
        } while (indexOfNextInterrogationPoint != -1);

        return nbToReturn;
    }

    @Override
    public String toString() {
        return "DamageRecordsHotSpring{" +
                "rawRecord='" + rawRecord + '\'' +
                ", consecutiveDamagedSpringGroups=" + consecutiveDamagedSpringGroups +
                '}';
    }
}
