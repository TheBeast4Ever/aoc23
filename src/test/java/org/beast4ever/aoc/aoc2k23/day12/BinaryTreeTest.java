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
        List<Character> expectedResults = Arrays.asList('d', 'c', 'e', 'a', 'g', 'f', 'h', '|', 'j', 'i', 'k', 'b', 'm', 'l', 'n');
        List<Character> results = tree.returnValuesInDepthSearch(tree.getRoot());
        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenTreeWith3Levels_thenLevelOrderSearchOK() {
        BinaryTree tree = buildThreeLevelsTree();
        List<Character> expectedResults = Arrays.asList('|', 'a', 'b', 'c', 'f', 'i', 'l', 'd', 'e', 'g', 'h', 'j', 'k', 'm', 'n');
        List<Character> results = tree.returnValuesInLevelOrder();
        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void when3LevelsRequired_thenBuildTreeOK() {
        Node rootNode = new Node('?');
        BinaryTree tree = new BinaryTree(rootNode);
        tree.addRecursiveLeftAndRight(rootNode, '#', '.', 3);
        Long result = tree.getNbOfLeaves(rootNode);
        Assertions.assertEquals(8, result);
    }

    @Test
    public void when10LevelsRequired_thenBuildTreeOK() {
        Node rootNode = new Node('|');
        BinaryTree tree = new BinaryTree(rootNode);
        tree.addRecursiveLeftAndRight(rootNode, '#', '.', 10);
        Long result = tree.getNbOfLeaves(rootNode);
        Assertions.assertEquals(1024, result);
    }

    private BinaryTree buildThreeLevelsTree() {
        Node node1  = new Node('a');
        Node node2  = new Node('b');
        Node node11  = new Node('c');
        Node node111  = new Node('d');
        Node node112  = new Node('e');
        Node node12  = new Node('f');
        Node node121  = new Node('g');
        Node node122  = new Node('h');
        Node node21  = new Node('i');
        Node node211  = new Node('j');
        Node node212  = new Node('k');
        Node node22  = new Node('l');
        Node node221  = new Node('m');
        Node node222  = new Node('n');
        Node rootNode = new Node('|');


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
