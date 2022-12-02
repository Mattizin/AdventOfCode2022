package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    private final Day01 puzzle;
    public Day01Test() {
        this.puzzle = new Day01("/Day01_TestInput.txt");
    }

    @Test
    public void testTotalCaloriesMostCarryingElf() {
        assertEquals(24000, puzzle.getCaloriesOfElfCarryingMost());
    }

    @Test
    public void testTotalCaloriesTop3CarryingElfes() {
        assertEquals(45000, puzzle.getCaloriesOf3ElfesCarryingMost());
    }
}
