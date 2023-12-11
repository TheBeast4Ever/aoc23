package org.beast4ever.aoc.aoc2k23.day11;

import org.beast4ever.aoc.aoc2k23.day03.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GalaxyTest {
    @Test
    public void whenSomeGalaxies_thenCalculateShortestPath() {
        Galaxy galaxy1 = new Galaxy(new Point(4, 0));
        Galaxy galaxy7 = new Galaxy(new Point(9, -10));
        Galaxy galaxy3 = new Galaxy(new Point(0, -2));
        Galaxy galaxy6 = new Galaxy(new Point(12, -7));
        Galaxy galaxy8 = new Galaxy(new Point(0, -11));
        Galaxy galaxy9 = new Galaxy(new Point(5, -11));

        Assertions.assertEquals(15, galaxy1.getShortestPathToReach(galaxy7));
        Assertions.assertEquals(15, galaxy7.getShortestPathToReach(galaxy1));
        Assertions.assertEquals(17, galaxy3.getShortestPathToReach(galaxy6));
        Assertions.assertEquals(17, galaxy6.getShortestPathToReach(galaxy3));
        Assertions.assertEquals(5, galaxy8.getShortestPathToReach(galaxy9));
        Assertions.assertEquals(5, galaxy9.getShortestPathToReach(galaxy8));
    }
}
