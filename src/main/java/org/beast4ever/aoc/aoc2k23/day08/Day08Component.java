package org.beast4ever.aoc.aoc2k23.day08;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k23.FileUtilityParser;
import org.beast4ever.aoc.aoc2k23.day07.CamelCardHand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

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
        Node currentNode = desertMap.getNode("AAA");
        int currentIdDir = 0;
        do {
            Character currentCharDir = directions.charAt(currentIdDir);
            int edgeId = (currentCharDir=='L')?0:1;
            currentNode = desertMap.getNode(currentNode.getNodeIdFromEdge(edgeId));
            nbOfSteps++;
            currentIdDir++;
            if (currentIdDir==directions.length()) {
                currentIdDir=0;
            }
        } while(!currentNode.getId().equals("ZZZ"));

        if (currentNode.getId().equals("ZZZ")) {
            log.info("Destination reached in {} steps", nbOfSteps);
        } else {
            log.error("Destination not reached !!!");
        }

       return "" + nbOfSteps;
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> lines = fileUtilityParser.readFileSplitByLines(inputFilePath);
        String directions = lines.get(0);
        DesertMap desertMap = new DesertMap(lines.subList(2, lines.size()));

        log.info("nb of nodes of the map {}", desertMap.getNbOfNodes());

        List<Node> currentNodesList = desertMap.getNodesEndsWith("A");
        int currentIdDir = 0;
        int nbOfSteps = 0;
        do {
            Character currentCharDir = directions.charAt(currentIdDir);
            int edgeId = (currentCharDir == 'L') ? 0 : 1;
            DesertRecursiveTask desertRecursiveTask = new DesertRecursiveTask(desertMap, currentNodesList, edgeId);
            currentNodesList = desertRecursiveTask.compute();
            nbOfSteps++;
            currentIdDir++;
            if (currentIdDir==directions.length()) {
                currentIdDir=0;
            }
        } while (!allCurrentNodesEndsWithZ(currentNodesList));

        return "" + nbOfSteps;
    }

    private Boolean allCurrentNodesEndsWithZ(List<Node> currentNodes) {
        long nbOfNodesEndsWithZ = currentNodes.stream().filter(node -> node.getId().endsWith("Z")).count();
        if (nbOfNodesEndsWithZ==currentNodes.size()-1) {
            log.info("5 : on est tout prêt");
        } else if (nbOfNodesEndsWithZ==currentNodes.size()-2) {
            log.info("4 : on se rapproche");
        }
        return nbOfNodesEndsWithZ == currentNodes.size();
    }

}
