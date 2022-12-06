package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.stream.Collectors;

public class Day06 extends Day {

    private final String inputData;

    public Day06(String inputFileName) {
        super(inputFileName);

        this.inputData = this.inputText.lines().collect(Collectors.joining());
    }

    public int calcStartOfPacketMarker() {
        return this.getDataMarker(this.inputData, 4);
    }

    public int calcStartOfMessageMarker() {
        return this.getDataMarker(this.inputData, 14);
    }

    private int getDataMarker(String data, int markerLength) {
        for (int i = markerLength; i < data.length(); i++) {
            String checkPart = data.substring(i - markerLength, i);
            if (this.onlyDistinctChars(checkPart)) {
                return i;
            }
        }
        return -1;
    }

    private boolean onlyDistinctChars(String input) {
        return input.chars().distinct().count() == input.length();
    }
}
