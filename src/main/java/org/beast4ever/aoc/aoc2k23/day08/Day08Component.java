package org.beast4ever.aoc.aoc2k23.day08;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

@Component
@Slf4j
public class Day08Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        String directions = lines.get(0);
        DesertMap desertMap = new DesertMap(lines.subList(2, lines.size()));
        int nbOfSteps = 0;
        log.info("nb of nodes of the map {}", desertMap.getNbOfNodes());
        Node currentNode = desertMap.getNodesMatchesWithPattern(Pattern.compile("AAA")).get(0);
        int currentIdDir = 0;
        DesertRecursiveTask desertRecursiveTask = new DesertRecursiveTask(desertMap, Arrays.asList(currentNode), directions, Pattern.compile("ZZZ"));

        List<Long> nbOfStepsForEachStartNode = desertRecursiveTask.compute();

        if (nbOfStepsForEachStartNode.size()==1) {
            log.info("Destination reached in {} steps", nbOfStepsForEachStartNode.get(0));
        } else {
            log.error("Destination not reached !!!");
        }

       return "" + nbOfStepsForEachStartNode.get(0);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        String directions = lines.get(0);
        DesertMap desertMap = new DesertMap(lines.subList(2, lines.size()));

        List<Node> startNodes = desertMap.getNodesMatchesWithPattern(Pattern.compile("..A"));

        DesertRecursiveTask desertRecursiveTask = new DesertRecursiveTask(desertMap, startNodes, directions, Pattern.compile("..Z"));

        List<Long> nbOfStepsForEachStartNode = desertRecursiveTask.compute();

        Long nbOfSteps = getLCM(nbOfStepsForEachStartNode);

        log.info("Nb of steps = {}", nbOfSteps);

        return "" + nbOfSteps;
    }

    private long getLCM(List<Long> numbers) {
        long lcm = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;

            for (int i = 0; i < numbers.size(); i++) {

                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.

                if (numbers.get(i) == 0) {
                    return 0;
                } else if (numbers.get(i) < 0) {
                    numbers.set(i, numbers.get(i) * (-1));
                }
                if (numbers.get(i) == 1) {
                    counter++;
                }

                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (numbers.get(i) % divisor == 0) {
                    divisible = true;
                    numbers.set(i, numbers.get(i) / divisor);
                }
            }

            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm = lcm * divisor;
            } else {
                divisor++;
            }

            // Check if all element_array is 1 indicate
            // we found all factors and terminate while loop.
            if (counter == numbers.size()) {
                return lcm;
            }
        }
    }
}
