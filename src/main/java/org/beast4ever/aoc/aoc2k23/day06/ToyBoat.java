package org.beast4ever.aoc.aoc2k23.day06;

public class ToyBoat {
    public static final long INITIAL_SPEED = 0;

    private long speed;

    public ToyBoat() {
        speed = INITIAL_SPEED;
    }

    public void pushButton(Long duration) {
        speed = duration;
    }

    public void reset() {
        speed = INITIAL_SPEED;
    }

    public long getDistanceBrowsed(Long duration) {
        return speed*duration;
    }

}
