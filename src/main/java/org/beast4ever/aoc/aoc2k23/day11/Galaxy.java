package org.beast4ever.aoc.aoc2k23.day11;

import org.beast4ever.aoc.aoc2k23.day03.Point;

import java.util.Objects;

public class Galaxy {
    public Point getCoords() {
        return coords;
    }

    private Point coords;

    public Galaxy(Point coords) {
        this.coords = coords;
    }

    public Long getShortestPathToReach(Galaxy otherGalaxy) {
        int rowDiff = Math.abs(coords.y() - otherGalaxy.getCoords().y());
        int colDiff = Math.abs(coords.x() - otherGalaxy.getCoords().x());

        return (long) rowDiff+colDiff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return Objects.equals(coords, galaxy.coords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coords);
    }

    @Override
    public String toString() {
        return "Galaxy{" +
                "coords=" + coords +
                '}';
    }
}
