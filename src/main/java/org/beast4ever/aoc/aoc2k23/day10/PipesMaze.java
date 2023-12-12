package org.beast4ever.aoc.aoc2k23.day10;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k23.day03.Point;

import java.util.List;

@Slf4j
public class PipesMaze {
    private Character[][] internalRawDataRepresentation;

    public Pipe getStartPipe() {
        return startPipe;
    }

    private Pipe startPipe;

    public PipesMaze(List<String> rawData) {
        internalRawDataRepresentation = new Character[rawData.size()][rawData.get(0).length()];
        for(int row=0; row<rawData.size(); row++) {
            for (int col=0; col<rawData.get(row).length(); col++) {
                Character currentChar = rawData.get(row).charAt(col);
                internalRawDataRepresentation[row][col] = currentChar;
            }
        }
        joinPipesFromStartPoint();
    }

    private void joinPipesFromStartPoint() {
        Point coords = seekStartPoint();
        if (coords != null) {
            this.startPipe = new Pipe(coords);
            this.startPipe.setStart(true);
            Point startCoords = startPipe.getCoords();
            Point[] points = getConnectedPointsForStartPoint(startCoords);
            Point nextPoint = points[1];
            Pipe previousPipe = startPipe;
            Point previousPoint = previousPipe.getCoords();

            do {
                Pipe nextPipe = new Pipe(nextPoint);
                nextPipe.connectPipeToIn(previousPipe);
                previousPipe.connectPipeToOut(nextPipe);
                Character currDirection = internalRawDataRepresentation[nextPoint.y()][nextPoint.x()];
                Point[] nextPoints = getConnectedPoints(nextPoint, currDirection);
                if (nextPoints[0].equals(previousPoint)) {
                    previousPoint = nextPoint;
                    nextPoint=nextPoints[1];
                } else {
                    previousPoint = nextPoint;
                    nextPoint=nextPoints[0];
                }
                previousPipe = nextPipe;

            } while(!nextPoint.equals(startCoords));
            startPipe.connectPipeToIn(previousPipe);
            previousPipe.connectPipeToOut(startPipe);
        }
    }

    private Point seekStartPoint() {
        for(int row=0; row < internalRawDataRepresentation.length; row++) {
            for(int col=0; col < internalRawDataRepresentation[row].length; col++) {
                Character currentChar = internalRawDataRepresentation[row][col];
                if (currentChar == 'S') {
                    Point startPoint = new Point(col, row);
                    log.info("Start point at coords {}", startPoint);
                    return startPoint;
                }
            }
        }
        log.error("Start point not found.");
        return null;
    }

    private Point[] getConnectedPointsForStartPoint(Point startPoint) {
        Character charToNorth = internalRawDataRepresentation[startPoint.y()-1][startPoint.x()];
        Character charToWest = internalRawDataRepresentation[startPoint.y()][startPoint.x()-1];
        Character charToSouth = internalRawDataRepresentation[startPoint.y()+1][startPoint.x()];
        Character charToEast = internalRawDataRepresentation[startPoint.y()][startPoint.x()+1];
        Point[] pointsToReturn = new Point[2];
        int i=0;

        if (i<=1 && (charToNorth == Pipe.TYPES.VERTICAL_PIPE.code()
                || charToNorth == Pipe.TYPES.BEND_S_E.code()
                || charToNorth == Pipe.TYPES.BEND_S_W.code())) {
            pointsToReturn[i] = new Point(startPoint.x(), startPoint.y()-1);
            i++;
        }
        if (i<=1 && (charToWest == Pipe.TYPES.HORIZONTAL_PIPE.code()
                || charToWest == Pipe.TYPES.BEND_N_E.code()
                || charToWest == Pipe.TYPES.BEND_S_E.code())) {
            pointsToReturn[i] = new Point(startPoint.x()-1, startPoint.y());
            i++;
        }
        if (i<=1 && (charToSouth == Pipe.TYPES.VERTICAL_PIPE.code()
                || charToSouth == Pipe.TYPES.BEND_N_E.code()
                || charToSouth == Pipe.TYPES.BEND_N_W.code())) {
            pointsToReturn[i] = new Point(startPoint.x(), startPoint.y()+1);
            i++;
        }
        if (i<=1 && (charToEast == Pipe.TYPES.HORIZONTAL_PIPE.code()
                || charToEast == Pipe.TYPES.BEND_N_W.code()
                || charToEast == Pipe.TYPES.BEND_S_W.code())) {
            pointsToReturn[i] = new Point(startPoint.x()+1, startPoint.y());
            i++;
        }

        return pointsToReturn;
    }

    /**
     *
     * @param encodedDirection
     * @return Array of two cells
     */
    private Point[] getConnectedPoints(Point currentPoint, Character encodedDirection) {
        Point[] pointsToReturn = new Point[2];
        int x = currentPoint.x();
        int y = currentPoint.y();
        switch (encodedDirection) {
            case '|':
                pointsToReturn[0] = new Point(x, y-1);
                pointsToReturn[1] = new Point(x, y+1);
                break;
            case '-' :
                pointsToReturn[0] = new Point(x-1, y);
                pointsToReturn[1] = new Point(x+1, y);
                break;
            case 'L' :
                pointsToReturn[0] = new Point(x, y-1);
                pointsToReturn[1] = new Point(x+1, y);
                break;
            case 'J' :
                pointsToReturn[0] = new Point(x, y-1);
                pointsToReturn[1] = new Point(x-1, y);
                break;
            case '7' :
                pointsToReturn[0] = new Point(x, y+1);
                pointsToReturn[1] = new Point(x-1, y);
                break;
            case 'F' :
                pointsToReturn[0] = new Point(x+1, y);
                pointsToReturn[1] = new Point(x, y+1);
                break;
            case '.' :
                log.info("empty point detected");
                break;
            case 'S' :
                log.info("start point detected");
                break;
            default :
                log.error("unrecognized format");
                break;
        }
        return pointsToReturn;
    }

}
