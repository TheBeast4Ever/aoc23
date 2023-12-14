package org.beast4ever.aoc.aoc2k23.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BinaryTreeTest {
    @Test
    public void whenTreeWith3Levels_thenBreadthFirstSearchOK() {
        BinaryTree tree = buildThreeLevelsTree();
        tree.returnValuesInLevelOrder();
    }

    @Test
    public void whenTreeWith3Levels_thenDepthFirstSearchOK() {
        BinaryTree tree = buildThreeLevelsTree();
        List<String> expectedResults = Arrays.asList("d", "c", "e", "a", "g", "f", "h", "|", "j", "i", "k", "b", "m", "l", "n");
        List<String> results = tree.returnValuesInDepthSearch(tree.getRoot());
        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenTreeWith3Levels_thenLevelOrderSearchOK() {
        BinaryTree tree = buildThreeLevelsTree();
        List<String> expectedResults = Arrays.asList("|", "a", "b", "c", "f", "i", "l", "d", "e", "g", "h", "j", "k", "m", "n");
        List<String> results = tree.returnValuesInLevelOrder();
        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenTreeWith3Levels_thenGetNodesAtSpecifiedLevelOK() {
        BinaryTree tree = buildThreeLevelsTree();
        List<String> expectedResults = Arrays.asList("d", "e", "g", "h", "j", "k", "m", "n");
        List<String> results = tree.returnValuesForSpecifiedLevel(3);
        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void when3LevelsRequired_thenBuildTreeOK() {
        Node rootNode = new Node("?");
        BinaryTree tree = new BinaryTree(rootNode);
        tree.addRecursiveLeftAndRight(rootNode, "#", ".", 3);
        Long result = tree.getNbOfLeaves(rootNode);
        Assertions.assertEquals(8, result);
    }

    @Test
    public void when10LevelsRequired_thenBuildTreeOK() {
        Node rootNode = new Node("|");
        BinaryTree tree = new BinaryTree(rootNode);
        tree.addRecursiveLeftAndRight(rootNode, "#", ".", 10);
        Long result = tree.getNbOfLeaves(rootNode);
        Assertions.assertEquals(1024, result);
    }

    private BinaryTree buildThreeLevelsTree() {
        Node node1  = new Node("a", 1);
        Node node2  = new Node("b", 1);
        Node node11  = new Node("c", 2);
        Node node111  = new Node("d", 3);
        Node node112  = new Node("e", 3);
        Node node12  = new Node("f", 2);
        Node node121  = new Node("g", 3);
        Node node122  = new Node("h", 3);
        Node node21  = new Node("i", 2);
        Node node211  = new Node("j", 3);
        Node node212  = new Node("k", 3);
        Node node22  = new Node("l", 2);
        Node node221  = new Node("m", 3);
        Node node222  = new Node("n", 3);
        Node rootNode = new Node("|", 0);


        node21.setLeft(node211);
        node21.setRight(node212);
        node22.setLeft(node221);
        node22.setRight(node222);

        node11.setLeft(node111);
        node11.setRight(node112);
        node12.setLeft(node121);
        node12.setRight(node122);

        node1.setLeft(node11);
        node1.setRight(node12);
        node2.setLeft(node21);
        node2.setRight(node22);

        rootNode.setLeft(node1);
        rootNode.setRight(node2);
        BinaryTree tree = new BinaryTree(rootNode);
        return tree;
    }
}
