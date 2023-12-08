package org.beast4ever.aoc.aoc2k23.day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class DesertRecursiveTask extends RecursiveTask<List<Node>> {

    private DesertMap desertMap;

    List<Node> startNodeList;

    private int indexOfEdge;

    public DesertRecursiveTask(DesertMap desertMap, List<Node> startNodeList, int indexOfEdge) {
        this.desertMap = desertMap;
        this.startNodeList = startNodeList;
        this.indexOfEdge = indexOfEdge;
    }

    @Override
    protected List<Node> compute() {
        if (startNodeList.size()>1) {
            List<Node> nodesList = new ArrayList<>();
            ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .map(ForkJoinTask::join).forEach(nextNodeId -> nodesList.addAll(nextNodeId));
            return nodesList;
        } else {
            return Arrays.asList(desertMap.getNode(startNodeList.get(0).getNodeIdFromEdge(indexOfEdge)));
        }
    }

    private Collection<DesertRecursiveTask> createSubtasks() {
        List<DesertRecursiveTask> dividedTasks = new ArrayList<>();
        for(int i=0;i<startNodeList.size();i++) {
            dividedTasks.add(new DesertRecursiveTask(desertMap, Arrays.asList(startNodeList.get(i)), indexOfEdge));
        }
        return dividedTasks;
    }
}
