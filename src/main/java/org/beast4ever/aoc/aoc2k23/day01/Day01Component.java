package org.beast4ever.aoc.aoc2k23.day01;

import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Day01Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        return resolveTwoStars(inputFilePath, false);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        return resolveTwoStars(inputFilePath, true);
    }

    private String resolveTwoStars(String inputFilePath, Boolean wordRecognitionActivated) throws IOException {

        List<String> amendedLines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<Integer> recoveredValibrationValues = new ArrayList<>();

        Trebuchet trebuchet = new Trebuchet();

        if (wordRecognitionActivated) {
            amendedLines.forEach(amendedLine -> recoveredValibrationValues.add(trebuchet.recoverCalibrationValueWithWordsNumberRecognition(amendedLine)));
        } else {
            amendedLines.forEach(amendedLine -> recoveredValibrationValues.add(trebuchet.recoverCalibrationValue(amendedLine)));
        }


        return "" + recoveredValibrationValues.stream().mapToLong(Long::valueOf).sum();

    }

}
