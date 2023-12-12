package org.beast4ever.aoc.aoc2k23.day10;

import org.beast4ever.aoc.aoc2k23.day03.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PipesMazeTest {
    @Test
    public void whenInlineData_thenMazeBuiltOK() {
        PipesMaze maze = buildInlineSimpleMaze();
        Point expectedResponse = new Point(1, 1);
        Assertions.assertEquals(expectedResponse, maze.getStartPipe().getCoords());
    }

    private PipesMaze buildInlineSimpleMaze() {
        String inlineData = """
                .....
                .S-7.
                .|.|.
                .L-J.
                .....
                """;
        PipesMaze maze = new PipesMaze(inlineData.lines().toList());

        return maze;
    }
}
