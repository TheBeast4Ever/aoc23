package org.beast4ever.aoc.aoc2k23.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Point (int x, int y){
    public boolean isAdjacent(Point otherPoint) {
        List<Point> adjacentPoints = getAdjacentPoints();

        return adjacentPoints.contains(otherPoint);
    }
    public List<Point> getAdjacentPoints() {
        return getAdjacentPointsWithOutOfBoundsControl(false, 0, 0, 0, 0);
    }

    public List<Point> getAdjacentPointsWithOutOfBoundsControl(int minX, int maxX, int minY, int maxY) {
        return getAdjacentPointsWithOutOfBoundsControl(true, minX, maxX, minY, maxY);
    }

    private List<Point> getAdjacentPointsWithOutOfBoundsControl(boolean checkOutOfBounds, int minX, int maxX, int minY, int maxY) {
        List<Point> listToReturn = new ArrayList<>();

        for(int i=x-1; i<=x+1; i++) {
            for (int j=y-1; j<=y+1; j++) {
                if (checkOutOfBounds) {
                    if (i>=minX && i<=maxX && j>=minY && j<=maxY) {
                        listToReturn.add(new Point(i, j));
                    }
                } else {
                    listToReturn.add(new Point(i, j));
                }
            }
        }

        return listToReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
