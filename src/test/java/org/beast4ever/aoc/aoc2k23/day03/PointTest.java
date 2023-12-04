package org.beast4ever.aoc.aoc2k23.day03;

import org.beast4ever.aoc.aoc2k23.day01.Trebuchet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointTest {
    @Test
    public void whenAdjacentPoint_thenAdjacentOK() {
        Point pointToCompare = new Point(1,1);

        Point point00 = new Point(0,0);
        Point point10 = new Point(1,0);
        Point point20 = new Point(2,0);
        Point point01 = new Point(0,1);
        Point point21 = new Point(2,1);
        Point point02 = new Point(0,2);
        Point point12 = new Point(1,2);
        Point point22 = new Point(2,2);

        List<Point> pointsList = Arrays.asList(point00, point10, point20, point01,point21, point02, point12, point22);
        pointsList.forEach(point -> Assertions.assertTrue(point.isAdjacent(pointToCompare)));
    }

    @Test
    public void whenNonAdjacentPoint_thenAdjacentKO() {
        Point pointToCompare = new Point(4,2);

        Point point00 = new Point(0,0);
        Point point10 = new Point(1,0);
        Point point20 = new Point(2,0);
        Point point01 = new Point(0,1);
        Point point21 = new Point(2,1);
        Point point02 = new Point(0,2);
        Point point12 = new Point(1,2);
        Point point22 = new Point(2,2);

        List<Point> pointsList = Arrays.asList(point00, point10, point20, point01,point21, point02, point12, point22);
        pointsList.forEach(point -> Assertions.assertTrue(!point.isAdjacent(pointToCompare)));
    }
}
