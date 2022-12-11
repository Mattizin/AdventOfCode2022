package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    private final Day11 puzzle;

    public Day11Test() {
        this.puzzle = new Day11("/Day11_TestInput.txt");
    }

    @Test
    void businessLevelMostActiveMonkey20Rounds() {
        assertEquals(10605, this.puzzle.businessLevelMostActiveMonkey(20, true));
    }

    @Test
    void businessLevelMostActiveMonkey10000RoundsNoRelief() {
        assertEquals(2713310158L, this.puzzle.businessLevelMostActiveMonkey(10000, false));
    }
}