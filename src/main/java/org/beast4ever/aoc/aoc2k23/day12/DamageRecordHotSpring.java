package org.beast4ever.aoc.aoc2k23.day12;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.RegexpUtilityService;


import java.util.*;
import java.util.regex.Pattern;

@Slf4j
public class DamageRecordHotSpring {
    private String part1;

    private List<Integer> part2;

    private String rawPart2;

    private BinaryTree treeOfPossibilities;

    public DamageRecordHotSpring(String part1, String part2) {
        this.part1 = part1;
        this.rawPart2 = part2;
        this.part2 = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(part2, ",");
        while(tokenizer.hasMoreTokens()) {
            this.part2.add(Integer.parseInt(tokenizer.nextToken()));
        }
    }

    public void unfold() {
        StringBuilder sbPart1 = new StringBuilder(part1);
        StringBuilder sbRawPart2 = new StringBuilder(rawPart2);
        List<Integer> newPart2 = new ArrayList<>();
        newPart2.addAll(part2);
        for(int i=1; i<5; i++) {
            sbPart1.append("?" + part1);
            sbRawPart2.append("," + rawPart2);
            newPart2.addAll(part2);
        }
        this.part1 = sbPart1.toString();
        this.rawPart2 = sbRawPart2.toString();
        this.part2 = newPart2;
    }

    public static List<Integer> determineConsecutiveDamagesList(String part1) {
        List<Integer> listToReturn = new ArrayList<>();

        int nbOfConsecutiveDamages=0;
        int groupId=-1;
        Character previousChar = '\t';
        for(int i=0; i<part1.length(); i++) {
            Character currentChar = part1.charAt(i);
            if (currentChar=='#' && previousChar.equals(currentChar)) {
                nbOfConsecutiveDamages++;
                listToReturn.set(groupId, nbOfConsecutiveDamages);
            } else if (part1.charAt(i)=='#' && !previousChar.equals(currentChar)) {
                groupId++;
                nbOfConsecutiveDamages=1;
                listToReturn.add(nbOfConsecutiveDamages);
            }
            previousChar = currentChar;
        }

        return listToReturn;
    }

    private Node buildPossibilitiesFrom(Node fromNode) {
        int indexOfFirstInterrogationPoint = fromNode.getValue().indexOf('?');
        String knownPart;
        String suffixPart;
        if (indexOfFirstInterrogationPoint==-1) {
            knownPart = fromNode.getValue();
            suffixPart = "";
        } else {
            knownPart = fromNode.getValue().substring(0, indexOfFirstInterrogationPoint);
            if (indexOfFirstInterrogationPoint==(fromNode.getValue().length()-1)) {
                suffixPart = "";
            } else {
                suffixPart = fromNode.getValue().substring(indexOfFirstInterrogationPoint+1);
            }
        }
        int indexofNextInterrogationPoint = suffixPart.indexOf("?");
        String knownSuffixPart = "";
        String unknownSuffixPart = "";
        if (indexofNextInterrogationPoint!=-1) {
            knownSuffixPart = suffixPart.substring(0, indexofNextInterrogationPoint);
            unknownSuffixPart = suffixPart.substring(indexofNextInterrogationPoint);
        } else {
            knownSuffixPart = suffixPart;
        }

        String possibility1 = knownPart+"#"+knownSuffixPart;
        String possibility2 = knownPart+"."+knownSuffixPart;
        List<Integer> listOfDamagesPossibility1 = determineConsecutiveDamagesList(possibility1);
        List<Integer> listOfDamagesPossibility2 = determineConsecutiveDamagesList(possibility2);

        if (isValidConsecutiveDamageList(listOfDamagesPossibility1)) {
            fromNode.setLeft(new Node(possibility1+unknownSuffixPart, fromNode.getLevel()+1));
        }
        if (isValidConsecutiveDamageList(listOfDamagesPossibility2)) {
            fromNode.setRight(new Node(possibility2+unknownSuffixPart, fromNode.getLevel()+1));
        }
        return fromNode;
    }

    public BinaryTree buildAndReturnTreeOfPossibilities() {
        int level=0;
        Node rootNode = new Node(part1, level);
        BinaryTree tree = new BinaryTree(rootNode);
        int indexOfNextInterrogationPoint=-1;
        int startIndex;

        do {
            startIndex=indexOfNextInterrogationPoint+1;
            indexOfNextInterrogationPoint = part1.indexOf('?', startIndex);
            if (indexOfNextInterrogationPoint != -1) {

                List<Node> nodes = tree.returnNodesFromSameLevel(level);
                nodes.forEach(n -> {
                    buildPossibilitiesFrom(n);
                });
                level++;
            }
        } while (indexOfNextInterrogationPoint != -1);

        return tree;
    }

    public Long getNumberOfPossibleDamagesArrangements() {
        if (treeOfPossibilities==null) {
            treeOfPossibilities=buildAndReturnTreeOfPossibilities();
        }

        RegexpUtilityService regexpService = new RegexpUtilityService();
        Pattern p = regexpService.computeRegexpForConsecutiveListOfSameChar(this.rawPart2, "#", ".");

        List<Node> possibilities = treeOfPossibilities.getLeavesMatchingValue(p);

        return (long) possibilities.size();
    }

    public boolean isValidConsecutiveDamageList(List<Integer> consecutiveDamageList) {
        boolean isValid = true;
        boolean consecutiveDamageListHasMoreElements = true;
        boolean part2HasMoreElements = true;

        for(int i=0; i<consecutiveDamageList.size() && i<this.part2.size(); i++) {
            consecutiveDamageListHasMoreElements = i<consecutiveDamageList.size()-1;
            part2HasMoreElements = i<part2.size()-1;
            Integer currentNb1 = consecutiveDamageList.get(i);
            Integer currentNb2 = this.part2.get(i);

            if (currentNb1>currentNb2) {
                isValid=false;
            } else if (currentNb1<currentNb2) {
                if ((consecutiveDamageListHasMoreElements&&part2HasMoreElements)
                        || (consecutiveDamageListHasMoreElements&&!part2HasMoreElements)) {
                    isValid=false;
                }
            }


            if (!isValid) {
                break;
            }
        }

       return isValid;
    }


    @Override
    public String toString() {
        return "DamageRecordsHotSpring{" +
                "rawRecord='" + part1 + '\'' +
                ", consecutiveDamages=" + part2 +
                '}';
    }
}
