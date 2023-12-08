package org.beast4ever.aoc.aoc2k23.day08;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

public class DesertRecursiveTask extends RecursiveTask<List<Long>> {

    private DesertMap desertMap;

    List<Node> startNodeList;

    private String directionsToFollow;

    private Pattern nodePatternToReach;

    public DesertRecursiveTask(DesertMap desertMap, List<Node> startNodeList, String directionsToFollow, Pattern nodePatternToReach) {
        this.desertMap = desertMap;
        this.startNodeList = startNodeList;
        this.directionsToFollow = directionsToFollow;
        this.nodePatternToReach = nodePatternToReach;
    }

    @Override
    protected List<Long> compute() {
        if (startNodeList.size()>1) {
            List<Long> nodesList = new ArrayList<>();
            ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .map(ForkJoinTask::join).forEach(startNode -> nodesList.addAll(startNode));
            return nodesList;
        } else {
            return Arrays.asList(getNumberOfStepsToReach(startNodeList.get(0), nodePatternToReach));
        }
    }

    private long getNumberOfStepsToReach(Node startNode, Pattern nodeToReachPattern) {
        long nbOfSteps = 0;
        Node currentNode = startNode;
        int currentIdDir = 0;
        do {
            Character currentCharDir = directionsToFollow.charAt(currentIdDir);
            int edgeId = (currentCharDir=='L')?0:1;
            currentNode = desertMap.getNode(currentNode.getNodeIdFromEdge(edgeId));
            nbOfSteps++;
            currentIdDir++;
            if (currentIdDir==directionsToFollow.length()) {
                currentIdDir=0;
            }
        } while(!nodeToReachPattern.matcher(currentNode.getId()).find());
        return nbOfSteps;
    }


    private Collection<DesertRecursiveTask> createSubtasks() {
        List<DesertRecursiveTask> dividedTasks = new ArrayList<>();
        for(int i=0;i<startNodeList.size();i++) {
            dividedTasks.add(new DesertRecursiveTask(desertMap, Arrays.asList(startNodeList.get(i)), directionsToFollow, nodePatternToReach));
        }
        return dividedTasks;
    }
}
