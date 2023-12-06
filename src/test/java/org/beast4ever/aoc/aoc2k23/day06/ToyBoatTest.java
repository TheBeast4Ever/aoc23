package org.beast4ever.aoc.aoc2k23.day06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.stream.LongStream;

public class ToyBoatTest {
    @Test
    public void whenRace1_thenBoatBrowsedDistanceOK() {
        long raceDuration = 7;
        long bestRaceDistance = 9;

        ToyBoat boat = new ToyBoat();

        long nbOfWinPossibilities = LongStream.rangeClosed(0,raceDuration).filter(pushDuration -> {
            boat.pushButton(pushDuration);
            long distanceBrowsed = boat.getDistanceBrowsed(raceDuration-pushDuration);
            boat.reset();
            return distanceBrowsed>bestRaceDistance;
        }).count();


        Assertions.assertEquals(4, nbOfWinPossibilities);
    }

    @Test
    public void whenRace2_thenBoatBrowsedDistanceOK() {
        long raceDuration = 15;
        long bestRaceDistance = 40;

        ToyBoat boat = new ToyBoat();

        long nbOfWinPossibilities = LongStream.rangeClosed(0,raceDuration).filter(pushDuration -> {
            boat.pushButton(pushDuration);
            long distanceBrowsed = boat.getDistanceBrowsed(raceDuration-pushDuration);
            boat.reset();
            return distanceBrowsed>bestRaceDistance;
        }).count();


        Assertions.assertEquals(8, nbOfWinPossibilities);
    }

    @Test
    public void whenRace3_thenBoatBrowsedDistanceOK() {
        long raceDuration = 30;
        long bestRaceDistance = 200;

        ToyBoat boat = new ToyBoat();

        long nbOfWinPossibilities = LongStream.rangeClosed(0,raceDuration).filter(pushDuration -> {
            boat.pushButton(pushDuration);
            long distanceBrowsed = boat.getDistanceBrowsed(raceDuration-pushDuration);
            boat.reset();
            return distanceBrowsed>bestRaceDistance;
        }).count();


        Assertions.assertEquals(9, nbOfWinPossibilities);
    }

    @Test
    public void whenRace4_thenBoatBrowsedDistanceOK() {
        long raceDuration = 71530;
        long bestRaceDistance = 940200;

        ToyBoat boat = new ToyBoat();

        long nbOfWinPossibilities = LongStream.rangeClosed(0,raceDuration).filter(pushDuration -> {
            boat.pushButton(pushDuration);
            long distanceBrowsed = boat.getDistanceBrowsed(raceDuration-pushDuration);
            boat.reset();
            return distanceBrowsed>bestRaceDistance;
        }).count();


        Assertions.assertEquals(71503, nbOfWinPossibilities);
    }

}
