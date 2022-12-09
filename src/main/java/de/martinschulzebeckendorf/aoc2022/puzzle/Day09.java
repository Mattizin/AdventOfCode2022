package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day09 extends Day {

    private final List<Motion> motions = new ArrayList<>();

    public Day09(String inputFileName) {
        super(inputFileName);

        this.inputText.lines().forEach(instruction -> this.motions.add(Motion.createFromInstructionText(instruction)));
    }

    public int distinctPositionsVisitedByTail(int tailLength) {
        // Start position
        Position headPos = new Position(0, 0);
        Position[] tailPositions = new Position[tailLength];
        for (int i = 0; i < tailLength; i++) {
            tailPositions[i] = new Position(0, 0);
        }
        // Store Distinct head positions visited
        Set<Position> visited = new HashSet<>();
        // Add start Position
        visited.add(headPos);

        for (Motion motion : motions) {
            // To follow up every Step has to be calculated separate after each other, though direction is the same
            for (int i = 1; i <= motion.steps(); i++) {
                switch (motion.direction()) {
                    case RIGHT -> headPos = headPos.move(1, 0);
                    case LEFT -> headPos = headPos.move(-1, 0);
                    case UP -> headPos = headPos.move(0, 1);
                    case DOWN -> headPos = headPos.move(0, -1);
                }
                // First tail knot moves after head
                tailPositions[0] = tailPositions[0].moveCloseAfter(headPos);
                // Every other tail knot moves after tail knot before
                for (int j = 1; j < tailPositions.length; j++) {
                    tailPositions[j] = tailPositions[j].moveCloseAfter(tailPositions[j - 1]);
                }
                // Add tail knot to set, accepts only distincts, does nothing if not changed/was there already
                visited.add(tailPositions[tailPositions.length - 1]);
            }
        }

        return visited.size();
    }
}

record Motion(Direction direction, int steps) {

    public static Motion createFromInstructionText(String instruction) {
        // Example: R 4
        String[] parts = instruction.split(" ");
        Direction dir = switch (parts[0]) {
            case "U" -> Direction.UP;
            case "D" -> Direction.DOWN;
            case "L" -> Direction.LEFT;
            case "R" -> Direction.RIGHT;
            default -> throw new IllegalStateException("Unexpected direction instruction value: " + parts[0]);
        };
        return new Motion(dir, Integer.parseInt(parts[1]));
    }
}

record Position(int x, int y) {

    public Position move(int x, int y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position moveCloseAfter(Position target) {
        // Don't move up to target if on same position or x & y are only 1 apart
        if (this.equals(target)
                || (Math.abs(target.x() - this.x()) < 2 && Math.abs(target.y() - this.y()) < 2)) {
            return this;
        }
        int newX = target.x == this.x ? this.x : target.x - this.x > 0 ? this.x + 1 : this.x - 1;
        int newY = target.y == this.y ? this.y : target.y - this.y > 0 ? this.y + 1 : this.y - 1;
        return new Position(newX, newY);
    }
}

enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;
}