package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {

    private final Day04 puzzle;

    public Day04Test() {
        this.puzzle = new Day04("/Day04_TestInput.txt");
    }

    @Test
    public void testAssignmentPairsFullyContained() {
        assertEquals(2, this.puzzle.assignmentPairsFullyContained());
    }

    @Test
    public void testAissignmentPairsOverlapping() {
        assertEquals(4, this.puzzle.assignmentPairsOverlapping());
    }
}