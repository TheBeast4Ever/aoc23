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

    public void addRecursiveLeftAndRight(Node node, String valueLeft, String valueRight, int level) {
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

    public void addLeftAndRightWithNullConstraint(Node node, String valueLeft, String valueRight, int level) {
        List<Node> nodes = returnNodesForSpecifiedLevel(level-1);
        nodes.stream().forEach(n -> {
            if (valueLeft != null) {
                n.setLeft(new Node(valueLeft, level));
            }
            if (valueRight != null) {
                n.setRight(new Node(valueRight, level));
            }
        });
    }

    public List<String> returnValuesInDepthSearch(Node node) {
        List<String> values = new ArrayList<>();
        if (node != null) {
            values.addAll(returnValuesInDepthSearch((node.getLeft())));
            log.info("Node value : {}", node.getValue());
            values.add(node.getValue());
            values.addAll(returnValuesInDepthSearch((node.getRight())));
        }
        return values;
    }

    public Long getNbOfLeaves() {
        return getNbOfLeaves(root);
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

    public List<String> returnValuesInLevelOrder() {
        return returnValuesForSpecifiedLevel(null);
    }

    public List<String> returnValuesForSpecifiedLevel(Integer level) {
        List<String> values = new ArrayList<>();
        if (this.root == null) {
            return values;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(this.root);

        while (!nodes.isEmpty()) {

            Node node = nodes.remove();

            if (level == null) {
                values.add(node.getValue());
            } else if ( node.getLevel()==level) {
                values.add(node.getValue());
            }
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

    public List<Node> returnNodesForSpecifiedLevel(Integer level) {
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
