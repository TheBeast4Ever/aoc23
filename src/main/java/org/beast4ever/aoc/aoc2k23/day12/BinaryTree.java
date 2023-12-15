package org.beast4ever.aoc.aoc2k23.day12;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

@Slf4j
public class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public List<Node> getLeavesMatchingValue(Pattern p) {
        return getLeaves(root, p);
    }

    public List<Node> getLeaves(Node node, Pattern p) {
        List<Node> listOfNodes = new ArrayList<>();
            // If node is null, return
            if (node == null) {
                return listOfNodes;
            }

            // If node is leaf node, print its data
            if (node.getLeft() == null && node.getRight() == null)
            {
                if (p.matcher(node.getValue()).matches()) {
                    listOfNodes.add(node);
                }
            }

            // If left child exists, check for leaf
            // recursively
            if (node.getLeft() != null && node.getRight() != null) {
                listOfNodes.addAll(getLeaves(node.getLeft(), p));
                listOfNodes.addAll(getLeaves(node.getRight(), p));
            } else {
                if (node.getLeft() != null) {
                    listOfNodes.addAll(getLeaves(node.getLeft(), p));
                } else {
                    listOfNodes.addAll(getLeaves(node.getRight(), p));
                }
            }
            return listOfNodes;
    }

    public List<Node> returnNodesFromSameLevel(Integer level) {
        List<Node> values = new ArrayList<>();
        if (this.root == null) {
            return values;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(this.root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            if (level == null) {
                values.add(node);
            } else if ( node.getLevel()==level) {
                values.add(node);
            }

            if (node.getLeft() != null) {
                nodes.add(node.getLeft());
            }

            if (node.getRight() != null) {
                nodes.add(node.getRight());
            }
        }
        return values;
    }
}
