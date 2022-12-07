package de.martinschulzebeckendorf.aoc2022.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {

    private final Day07 puzzle;

    public Day07Test() {
        this.puzzle = new Day07("/Day07_TestInput.txt");
    }

    @Test
    void sumOfDirectorySizesMax100k() {
        assertEquals(95437, this.puzzle.sumOfDirectorySizesMax100k());
    }

    @Test
    void smallestDirSizeForDeletion() {
        assertEquals(24933642, this.puzzle.smallestDirSizeForDeletion(70000000, 30000000));
    }
}