package org.beast4ever.aoc.aoc2k23.day12;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Character value;

    public Character getValue() {
        return value;
    }

    private Node left;
    private Node right;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node(Character value) {
        this.value = value;
    }

}
