package org.beast4ever.aoc.aoc2k23.day12;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    private Integer level;

    private String value;

    public String getValue() {
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

    public Node(String value) {
        this.value = value;
    }

    public Node(String value, Integer level) {
        this.level = level;
        this.value = value;
    }

}
