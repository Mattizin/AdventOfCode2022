package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.List;
import java.util.stream.Collectors;

public class Day04 extends Day {

    private final List<Pair> pairs;

    public Day04(String inputFileName) {
        super(inputFileName);

        this.pairs = this.inputText.lines()
                .map(s -> {
                    String[] pairs = s.split(",");
                    String[] first = pairs[0].split("-");
                    String[] second = pairs[1].split("-");
                    return new Pair(new IntRange(first[0], first[1]), new IntRange(second[0], second[1]));
                })
                .collect(Collectors.toList());
    }

    public long assignmentPairsFullyContained() {
        return this.pairs.stream()
                .filter(pair -> {
                    return pair.first().containsOther(pair.second()) || pair.second().containsOther(pair.first());
                })
                .count();
    }

    public long assignmentPairsOverlapping() {
        return this.pairs.stream()
                .filter(pair -> {
                    return pair.first().overlapsOther(pair.second()) || pair.second().overlapsOther(pair.first());
                })
                .count();
    }
}

record Pair(IntRange first, IntRange second) {
}

record IntRange(int start, int end) {

    public IntRange(String start, String end) {
        this(Integer.parseInt(start), Integer.parseInt(end));
    }

    public boolean containsOther(IntRange other) {
        return this.start <= other.start && this.end >= other.end;
    }

    public boolean overlapsOther(IntRange other) {
        return this.start <= other.end && this.end >= other.start;
    }
}