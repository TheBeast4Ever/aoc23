package org.beast4ever.aoc.aoc2k23.day03;

import org.apache.catalina.Engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class EngineSchematic {
    private EngineSchematicPart[][] internalRepresentation;

    public Character[][] getData() {
        return data;
    }

    private Character[][] data;


    public EngineSchematic(int height, int width) {
        internalRepresentation = new EngineSchematicPart[height][width];
        data = new Character[height][width];
        for (int x=0; x<width; x++)  {
            for (int y=0; y<height; y++) {
                internalRepresentation[y][x] = new EngineSchematicPart('.');
                data[y][x] = '.';
            }
        }
    }

    private int getWidth() {
        return internalRepresentation[0].length;
    }

    private int getHeight() {
        return internalRepresentation.length;
    }

    public void addLine(String line, int row) {

        for (int x=0; x<line.length(); x++) {
            EngineSchematicPart part = new EngineSchematicPart(line.charAt(x));

            internalRepresentation[row][x] = part;

        }
    }

    public void addAllLines(List<String> lines) {
        for (int i=0; i<lines.size();i++) {
                this.addLine(lines.get(i), i);
        }

    }

    public List<EngineSchematicPart> getTotalPartsList() {
        List<EngineSchematicPart> partsList = new ArrayList<>();
        for(int x=0; x<getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                EngineSchematicPart currPart = internalRepresentation[x][y];
                partsList.add(currPart);
            }
        }
        return partsList;
    }

    public List<Integer> getEnginePartsList() {
        List<Integer> partsList = new ArrayList<>();

        for(int x=0; x<getWidth(); x++) {
            for (int y=0; y<getHeight(); y++) {
                Point p = new Point(x,y);
                Integer currPart = internalRepresentation[p.y()][p.x()].getFullPartRepresentation();
                List<Point> adjacentPoints = p.getAdjacentPointsWithOutOfBoundsControl(0, getWidth()-1, 0, getHeight()-1);
                for (int z=0; z<adjacentPoints.size(); z++) {
                    Point currPoint = adjacentPoints.get(z);
                    EngineSchematicPart currAdjPart = internalRepresentation[currPoint.y()][currPoint.x()];
                    if (currAdjPart.isSymbol() && currPart != null) {
                        if (!partsList.contains(currPart)) {
                            partsList.add(currPart);
                            break;
                        }
                    }
                }
            }
        }

        return partsList;
    }


    private Integer mergeValues(EngineSchematicPart... parts) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(parts).iterator().forEachRemaining(part -> sb.append(part.getContent()));
        return Integer.parseInt(sb.toString());
    }

}
