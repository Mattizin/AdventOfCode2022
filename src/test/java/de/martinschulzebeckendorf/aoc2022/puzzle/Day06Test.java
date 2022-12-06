package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {

    private final Day06 puzzle;

    public Day06Test() {
        this.puzzle = new Day06("/Day06_TestInput.txt");
    }

    @Test
    void testCalcStartOfPacketMarker() {
        assertEquals(7, this.puzzle.calcStartOfPacketMarker());
    }

    @Test
    void testCalcStartOfMessageMarker() {
        assertEquals(19, this.puzzle.calcStartOfMessageMarker());
    }
}