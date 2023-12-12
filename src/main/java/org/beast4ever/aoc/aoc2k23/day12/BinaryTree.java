package org.beast4ever.aoc.aoc2k23.day12;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
public class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void addRecursiveLeftAndRight(Node node, Character valueLeft, Character valueRight, int level) {
        addRecursiveLeftAndRight(node, valueLeft, valueRight, level, null);
    }

    public void addRecursiveLeftAndRight(Node node, Character valueLeft, Character valueRight, int level, String constraint) {
        if (level > 1) {
            node.setLeft(new Node(valueLeft));
            node.setRight(new Node(valueRight));
            addRecursiveLeftAndRight(node.getLeft(), valueLeft, valueRight, level-1);
            addRecursiveLeftAndRight(node.getRight(), valueLeft, valueRight, level-1);

        } else {
            node.setLeft(new Node(valueLeft));
            node.setRight(new Node(valueRight));
        }
    }

    public List<Character> returnValuesInDepthSearch(Node node) {
        List<Character> values = new ArrayList<>();
        if (node != null) {
            values.addAll(returnValuesInDepthSearch((node.getLeft())));
            log.info("Node value : {}", node.getValue());
            values.add(node.getValue());
            values.addAll(returnValuesInDepthSearch((node.getRight())));
        }
        return values;
    }

    public Long getNbOfLeaves(Node node) {
            // If node is null, return
            if (node == null) {
                return 0l;
            }

            // If node is leaf node, print its data
            if (node.getLeft() == null && node.getRight() == null)
            {
                return 1l;
            }

            // If left child exists, check for leaf
            // recursively
            if (node.getLeft() != null && node.getRight() != null) {
                return getNbOfLeaves(node.getLeft()) + getNbOfLeaves(node.getRight());
            } else {
                if (node.getLeft() != null) {
                    return getNbOfLeaves(node.getLeft());
                } else {
                    return getNbOfLeaves(node.getRight());
                }
            }
    }

    public List<Character> returnValuesInLevelOrder() {
        List<Character> values = new ArrayList<>();
        if (this.root == null) {
            return values;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(this.root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            values.add(node.getValue());
            log.info("Node value : {}", node.getValue());

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
