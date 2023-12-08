package org.beast4ever.aoc.aoc2k23.day08;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class DesertMap {
    private Map<String, Node> nodesMap;

    public DesertMap(List<String> rawText) {
        buildNodesMap(rawText);
    }

    private void buildNodesMap(List<String> rawText) {
        nodesMap = new HashMap<>();
        for (int i=0; i<rawText.size(); i++) {
            String currentLine = rawText.get(i);
            String nodeId = currentLine.substring(0, 3);
            Node currentNode = getNodeAndCreateEventually(nodeId);
            StringTokenizer tokenize = new StringTokenizer(currentLine.substring(7, currentLine.length()-1), ",");
            String nodeIdLeft = tokenize.nextToken().trim();
            String nodeIdRight = tokenize.nextToken().trim();
            currentNode.addTwoEdges(nodeIdLeft, nodeIdRight);
            nodesMap.put(nodeId, currentNode);
        }
    }

    private Node getNodeAndCreateEventually(String nodeId) {
        Node node;
        if (!nodesMap.containsKey(nodeId)) {
            node = new Node(nodeId);
        }else {
            node = nodesMap.get(nodeId);
        }
        return node;
    }

    public Integer getNbOfNodes() {
        return nodesMap.size();
    }

    public List<Node> getNodesEndsWith(String endLetter) {
        List<Node> nodeslist = new ArrayList<>();
        nodesMap.keySet().stream().filter(key -> key.endsWith(endLetter)).forEach(key -> nodeslist.add(nodesMap.get(key)));
        return nodeslist;
    }

    public Node getNode(String idNode) {
        if (nodesMap.containsKey(idNode)) {
            return nodesMap.get(idNode);
        } else {
            log.error("Something went wrong");
            return null;
        }
    }
}
