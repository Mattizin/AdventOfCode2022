package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

public abstract class Day {

    protected final BufferedReader inputText;

    public Day(String inputFileName) {
        this.inputText = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day.class.getResourceAsStream(inputFileName))));
    }
}
