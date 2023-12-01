package org.beast4ever.aoc.aoc2k23.day01;

import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Trebuchet {
    private static Map<String, Integer> wordsNumbersMapConversion;

    static {
        wordsNumbersMapConversion = Map.ofEntries(
                new AbstractMap.SimpleEntry<String, Integer>("one", 1),
                new AbstractMap.SimpleEntry<String, Integer>("two", 2),
                new AbstractMap.SimpleEntry<String, Integer>("three", 3),
                new AbstractMap.SimpleEntry<String, Integer>("four", 4),
                new AbstractMap.SimpleEntry<String, Integer>("five", 5),
                new AbstractMap.SimpleEntry<String, Integer>("six", 6),
                new AbstractMap.SimpleEntry<String, Integer>("seven", 7),
                new AbstractMap.SimpleEntry<String, Integer>("eight", 8),
                new AbstractMap.SimpleEntry<String, Integer>("nine", 9)
        );
    }


    public Integer recoverCalibrationValue(String amendedCalibration) {
        StringBuilder recoveredString = new StringBuilder();

        recoveredString.append(findFirstDigit(amendedCalibration, false,false));

        recoveredString.append(findFirstDigit(amendedCalibration, true,false));

        return Integer.parseInt(recoveredString.toString());
    }

    public Integer recoverCalibrationValueWithWordsNumberRecognition(String amendedCalibration) {
        StringBuilder recoveredString = new StringBuilder();

        recoveredString.append(findFirstDigit(amendedCalibration, false,true));
        recoveredString.append(findFirstDigit(amendedCalibration, true,true));

        return Integer.parseInt(recoveredString.toString());
    }

    public Integer findFirstDigit(String input, Boolean fromEnd, Boolean withWordsNumberRecognition) {
        Integer digitToReturn = 0;
        int lastIndexToFind = (fromEnd?0:input.length()-1);

        if (withWordsNumberRecognition) {
            WordPosition wordPosition = findFirstIndexOfDigitInWordNumber(input, fromEnd);
            if (wordPosition.position != -1) {
                lastIndexToFind = wordPosition.position;
                digitToReturn = wordsNumbersMapConversion.get(wordPosition.word);
            }
        }
        if (fromEnd) {
            for (int j=input.length()-1; j>=lastIndexToFind; j--) {
                if (Character.isDigit(input.charAt(j))) {
                    digitToReturn = Integer.parseInt("" + input.charAt(j));
                    break;
                }
            }
        } else {
            for (int i = 0; i <= lastIndexToFind; i++) {
                if (Character.isDigit(input.charAt(i))) {
                    digitToReturn = Integer.parseInt("" + input.charAt(i));
                    break;
                }
            }
        }

        return digitToReturn;
    }

    public WordPosition findFirstIndexOfDigitInWordNumber(String input, Boolean fromEnd) {
        AtomicInteger indexOfFirstDigit = (fromEnd)? new AtomicInteger(-1):new AtomicInteger(input.length());
        AtomicReference<String> firstWordFound = new AtomicReference<>("");

        wordsNumbersMapConversion.keySet().stream().filter(currWord -> input.contains(currWord)).forEach(currWord -> {
            int indexOfFirstLetter = (fromEnd)?input.lastIndexOf(currWord):input.indexOf(currWord);
            if ((fromEnd && indexOfFirstLetter > indexOfFirstDigit.get()) ||
                    (!fromEnd && indexOfFirstLetter < indexOfFirstDigit.get())) {
                indexOfFirstDigit.set(indexOfFirstLetter);
                firstWordFound.set(currWord);
            }
        });

        return new WordPosition(firstWordFound.get(), indexOfFirstDigit.get());
    }

    public record WordPosition(String word, Integer position){}

}
