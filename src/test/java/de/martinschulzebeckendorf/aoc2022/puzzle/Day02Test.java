package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    private final Day02 puzzle;

    public Day02Test() {
        this.puzzle = new Day02("/Day02_TestInput.txt");
    }

    @Test
    public void testCalculateTotalScorePart1() {
        assertEquals(15, puzzle.calculateTotalScorePart1());
    }

    @Test
    public void testCalculateTotalScorePart2() {
        assertEquals(12, puzzle.calculateTotalScorePart2());
    }
}