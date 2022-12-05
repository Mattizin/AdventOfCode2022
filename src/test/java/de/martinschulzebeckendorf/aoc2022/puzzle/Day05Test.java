package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {

    private final Day05 puzzle;

    public Day05Test() {
        this.puzzle = new Day05("/Day05_TestInput.txt");
    }

    @Test
    public void testCalcGratesOnTop() {
        assertEquals("CMZ", this.puzzle.calcGratesOnTop());
    }

    @Test
    public void testCalcGratesOnTopCrateMover9001() {
        assertEquals("MCD", this.puzzle.calcGratesOnTopCrateMover9001());
    }
}