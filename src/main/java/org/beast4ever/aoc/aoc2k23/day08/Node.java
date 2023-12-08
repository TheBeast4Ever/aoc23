package org.beast4ever.aoc.aoc2k23.day08;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Node {
    public String getId() {
        return id;
    }

    private String id;
    private List<String> outputEdges;

    public Node(String id) {
        this.id = id;
        outputEdges = new ArrayList<>();
    }

    public String getNodeIdFromEdge(int indexOfEdge) {
        if (indexOfEdge>=0 && indexOfEdge<outputEdges.size()) {
            return outputEdges.get(indexOfEdge);
        } else {
            log.error("Something went wrong");
            return null;
        }
    }

    public void addTwoEdges(String leftNode, String rightLeft) {
        outputEdges.add(leftNode);
        outputEdges.add(rightLeft);
    }
}
