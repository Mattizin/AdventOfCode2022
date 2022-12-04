package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {

    private final Day03 puzzle;

    public Day03Test() {
        this.puzzle = new Day03("/Day03_TestInput.txt");
    }

    @Test
    public void testPrioritySumOfSharedItems() {
        assertEquals(157, this.puzzle.calcPrioritySumOfSharedItems());
    }

    @Test
    public void testPrioritySumOfBadgeItems() {
        assertEquals(70, this.puzzle.calcPioritySumOfBadgeItems());
    }
}