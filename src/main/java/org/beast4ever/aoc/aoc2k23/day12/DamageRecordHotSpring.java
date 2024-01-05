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

    public String getNextKnownPart(String knownPart) {
        int nbOfKnownChars = knownPart.length();
        if (nbOfKnownChars >= part1.length()) {
            return knownPart;
        }

        int relativeIndexOfNextUnknownChar = part1.substring(nbOfKnownChars).indexOf('?');
        int indexOfNextUnknownChar = nbOfKnownChars + relativeIndexOfNextUnknownChar;

        StringBuilder sb = new StringBuilder(knownPart);
        if (relativeIndexOfNextUnknownChar != -1) {
            sb.append(part1.substring(nbOfKnownChars, indexOfNextUnknownChar));
        } else {
            sb.append(part1.substring(nbOfKnownChars));
        }

        return sb.toString();
    }

    public Long computeRecursivelyNbOfPossibilities(String knownPart)  {
        if (knownPart.length()<this.part1.length()) {
            String possibility1 = getNextKnownPart(knownPart + "#");
            String possibility2 = getNextKnownPart(knownPart + ".");

            List<Integer> listOfDamagesPossibility1 = determineConsecutiveDamagesList(possibility1);
            List<Integer> listOfDamagesPossibility2 = determineConsecutiveDamagesList(possibility2);

            boolean isPossibility1Valid = isValidConsecutiveDamageList(listOfDamagesPossibility1);
            boolean isPossibility2Valid = isValidConsecutiveDamageList(listOfDamagesPossibility2);
            boolean isTwoPossibilitiesValid = isPossibility1Valid && isPossibility2Valid;
            long nbOfArrangements = 0;
            if (isTwoPossibilitiesValid) {
                nbOfArrangements = computeRecursivelyNbOfPossibilities(possibility1) + computeRecursivelyNbOfPossibilities(possibility2);
            } else if (isPossibility1Valid) {
                nbOfArrangements = computeRecursivelyNbOfPossibilities(possibility1);
            } else if (isPossibility2Valid) {
                nbOfArrangements = computeRecursivelyNbOfPossibilities(possibility2);
            } else {
                nbOfArrangements = 0;
            }
            return nbOfArrangements;
        } else {
            List<Integer> listOfDamages = determineConsecutiveDamagesList(knownPart);
            boolean isListValid = listOfDamages.equals(part2);
            return isListValid?1l:0l;
        }
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
