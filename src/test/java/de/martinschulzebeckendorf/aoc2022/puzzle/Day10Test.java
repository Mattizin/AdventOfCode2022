package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    private final Day10 puzzle;

    public Day10Test() {
        this.puzzle = new Day10("/Day10_TestInput.txt");
    }

    @Test
    void sumSignalStrengthsAtCycles() {
        assertEquals(13140, this.puzzle.sumSignalStrengthsAtCycles(List.of(20, 60, 100, 140, 180, 220)));
    }
}