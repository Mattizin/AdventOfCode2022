package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {

    @Test
    void positionsVisitedByTailLength1() {
        Day09 puzzle = new Day09("/Day09_TestInput.txt");
        assertEquals(13, puzzle.distinctPositionsVisitedByTail(1));
    }

    @Test
    void positionsVisitedByTailLength9() {
        Day09 puzzle = new Day09("/Day09_TestInput_2.txt");
        assertEquals(36, puzzle.distinctPositionsVisitedByTail(9));
    }
}