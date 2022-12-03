package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends Day {

    private final List<String> input = new ArrayList<>();

    public Day02(String inputFileName) {
        super(inputFileName);

        this.inputText.lines().forEach(this.input::add);
    }

    public int calculateTotalScorePart1() {
        List<Move> moves = new ArrayList<>();
        this.input.forEach(s -> {
            moves.add(new Move(
                    Shape.getShapeBySymbol(s.charAt(0)),
                    Shape.getShapeBySymbol(s.charAt(2))));
        });
        return this.calculateTotalScore(moves);
    }

    public int calculateTotalScorePart2() {
        List<Move> moves = new ArrayList<>();
        this.input.forEach(s -> {
            Shape play = Shape.getShapeBySymbol(s.charAt(0));
            moves.add(new Move(
                    play,
                    Shape.getShapeByMoveOutcome(play, s.charAt(2))));
        });
        return this.calculateTotalScore(moves);
    }

    private int calculateTotalScore(List<Move> moves) {
        return moves.stream()
                .map(Move::moveScore)
                .mapToInt(Integer::valueOf)
                .sum();
    }
}

record Move(Shape play, Shape response) {
    public int moveScore() {
        return response.getScore() + outcomeScore();
    }

    private int outcomeScore() {
        if (play.equals(response)) {
            return 3;
        }
        if ((response.equals(Shape.ROCK) && play.equals(Shape.SCISSORS))
                || (response.equals(Shape.Paper) && play.equals(Shape.ROCK))
                || (response.equals(Shape.SCISSORS) && play.equals(Shape.Paper))) {
            return 6;
        }
        return 0;
    }
}

enum Shape {
    ROCK(1),
    Paper(2),
    SCISSORS(3);
    private final int score;

    Shape(int score) {
        this.score = score;
    }

    public static Shape getShapeBySymbol(char playSymbol) {
        switch (playSymbol) {
            case 'A', 'X' -> {
                return Shape.ROCK;
            }
            case 'B', 'Y' -> {
                return Shape.Paper;
            }
            case 'C', 'Z' -> {
                return Shape.SCISSORS;
            }
        }
        return null;
    }

    public static Shape getShapeByMoveOutcome(Shape play, char outComeSymbol) {
        switch (outComeSymbol) {
            case 'X' -> { // Loose
                switch (play) {
                    case ROCK -> {
                        return SCISSORS;
                    }
                    case Paper -> {
                        return ROCK;
                    }
                    case SCISSORS -> {
                        return Paper;
                    }
                }
            }
            case 'Y' -> { // Draw
                return play;
            }
            case 'Z' -> { // Win
                switch (play) {
                    case ROCK -> {
                        return Paper;
                    }
                    case Paper -> {
                        return SCISSORS;
                    }
                    case SCISSORS -> {
                        return ROCK;
                    }
                }
            }
        }
        return null;
    }

    public int getScore() {
        return score;
    }
}