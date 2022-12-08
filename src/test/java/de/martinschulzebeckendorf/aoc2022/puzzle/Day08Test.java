package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

    private Day08 puzzle;

    public Day08Test() {
        this.puzzle = new Day08("/Day08_TestInput.txt");
    }

    @Test
    void treesVisible() {
        assertEquals(21, this.puzzle.treesVisible());
    }

    @Test
    void maxTreeSenicScore() {
        assertEquals(8, this.puzzle.maxTreeScenicScore());
    }
}