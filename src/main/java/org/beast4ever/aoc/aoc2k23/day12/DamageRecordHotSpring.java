package org.beast4ever.aoc.aoc2k23.day12;

import lombok.extern.slf4j.Slf4j;


import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class DamageRecordHotSpring {
    private String rawRecord;

    private BinaryTree treeOfPossibilities;

    private Pattern regexp;

    public DamageRecordHotSpring(String rawRecord, String groupsCount) {
        this.rawRecord = rawRecord;
        this.regexp = buildRegexp(groupsCount);
        this.treeOfPossibilities = buildTreeOfPossibilities(this.rawRecord, this.regexp);
    }

    private Pattern buildRegexp(String groupsCount) {
        StringTokenizer tokenize = new StringTokenizer(groupsCount, ",");

        StringBuilder sb = new StringBuilder();
        sb.append("[.]*");
        while (tokenize.hasMoreTokens()) {
            int currNbOfConsecutiveDamages = Integer.parseInt(tokenize.nextToken());
            sb.append("[#]{"+currNbOfConsecutiveDamages+"}");
            if (tokenize.hasMoreTokens()) {
                sb.append("[.]+");
            } else {
                sb.append("[.]*");
            }
        }

        return Pattern.compile(sb.toString());
    }

    private BinaryTree buildTreeOfPossibilities(String input, Pattern regexp) {
        int level=0;
        Node rootNode = new Node(input, level);
        BinaryTree tree = new BinaryTree(rootNode);
        int indexOfNextInterrogationPoint=-1;
        int startIndex;
        StringBuilder prefixKnownStr = new StringBuilder();

        do {
            startIndex=indexOfNextInterrogationPoint+1;
            indexOfNextInterrogationPoint = input.indexOf('?', startIndex);
            if (indexOfNextInterrogationPoint != -1) {
                prefixKnownStr.append(input.substring(startIndex, indexOfNextInterrogationPoint));
                String suffixStr = indexOfNextInterrogationPoint<input.length()?input.substring(indexOfNextInterrogationPoint+1):"";
                level++;
                String leftValue = isValidPrefix(prefixKnownStr.toString()+"#"+suffixStr)?prefixKnownStr.toString()+"#"+suffixStr:null;
                String rightValue = isValidPrefix(prefixKnownStr.toString()+"."+suffixStr)?prefixKnownStr.toString()+"."+suffixStr:null;
                tree.addLeftAndRightWithNullConstraint(rootNode, leftValue, rightValue, level);
            }
        } while (indexOfNextInterrogationPoint != -1);
        return tree;
    }

    public Long getNumberOfPossibleDamagesArrangements() {
        return treeOfPossibilities.getNbOfLeaves();
    }

    public boolean isValidPrefix(String prefixKnownStr) {
        boolean isValid = true;
        int consecutiveDamagesNb=0;
        int consecutiveGroupId=0;

        return isValid;
    }

    @Override
    public String toString() {
        return "DamageRecordsHotSpring{" +
                "rawRecord='" + rawRecord + '\'' +
                ", regexp=" + regexp +
                '}';
    }
}
