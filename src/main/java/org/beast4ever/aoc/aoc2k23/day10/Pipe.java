package org.beast4ever.aoc.aoc2k23.day10;

import org.beast4ever.aoc.aoc2k23.day03.Point;

import java.util.List;

public class Pipe {

    public static enum TYPES {
        VERTICAL_PIPE('|'),
        HORIZONTAL_PIPE('-'),
        BEND_N_E('L'),
        BEND_N_W('J'),
        BEND_S_W('7'),
        BEND_S_E('F');

        Character code() {
            return code;
        }

        private final Character code;

        TYPES(Character code) {
            this.code = code;
        }
    }

    public void setStart(Boolean start) {
        isStart = start;
    }

    private Boolean isStart;

    public Point getCoords() {
        return coords;
    }

    private Point coords;

    public Pipe getInPipe() {
        return inPipe;
    }

    public Pipe getOutPipe() {
        return outPipe;
    }


    public void connectPipeToIn(Pipe inPipe) {
        this.inPipe = inPipe;
    }

    public void connectPipeToOut(Pipe outPipe) {
        this.outPipe = outPipe;
    }

    private Pipe inPipe;
    private Pipe outPipe;
    public Pipe(Point coords){
        this.coords = coords;
        this.isStart = false;
    }

    public boolean isContainedInLoop() {
        boolean deadEndDetected = false;
        Pipe nextPipe = this.outPipe;
        while (nextPipe != this && !deadEndDetected) {
            if (nextPipe==null) {
                deadEndDetected=true;
            } else {
                nextPipe = nextPipe.outPipe;
            }
        }
        return nextPipe == this;
    }

}
