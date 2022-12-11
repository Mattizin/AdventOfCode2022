package de.martinschulzebeckendorf.aoc2022;

import de.martinschulzebeckendorf.aoc2022.puzzle.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2022!");

        System.out.println("Day 1");
        Day01 puzzle01 = new Day01("/Day01_Input.txt");
        System.out.println("Part 1: " + puzzle01.getCaloriesOfElfCarryingMost());
        System.out.println("Part 2: " + puzzle01.getCaloriesOf3ElfesCarryingMost());

        System.out.println("Day 2");
        Day02 puzzle02 = new Day02("/Day02_Input.txt");
        System.out.println("Part 1: " + puzzle02.calculateTotalScorePart1());
        System.out.println("Part 2: " + puzzle02.calculateTotalScorePart2());

        System.out.println("Day 3");
        Day03 puzzle03 = new Day03("/Day03_Input.txt");
        System.out.println("Part 1: " + puzzle03.calcPrioritySumOfSharedItems());
        System.out.println("Part 2: " + puzzle03.calcPioritySumOfBadgeItems());

        System.out.println("Day 4");
        Day04 puzzle04 = new Day04("/Day04_Input.txt");
        System.out.println("Part 1: " + puzzle04.assignmentPairsFullyContained());
        System.out.println("Part 2: " + puzzle04.assignmentPairsOverlapping());

        System.out.println("Day 5");
        Day05 puzzle05 = new Day05("/Day05_Input.txt");
        System.out.println("Part 1: " + puzzle05.calcGratesOnTop());
        System.out.println("Part 2: " + puzzle05.calcGratesOnTopCrateMover9001());

        System.out.println("Day 6");
        Day06 puzzle06 = new Day06("/Day06_Input.txt");
        System.out.println("Part 1: " + puzzle06.calcStartOfPacketMarker());
        System.out.println("Part 2: " + puzzle06.calcStartOfMessageMarker());

        System.out.println("Day 7");
        Day07 puzzle07 = new Day07("/Day07_Input.txt");
        System.out.println("Part 1: " + puzzle07.sumOfDirectorySizesMax100k());
        System.out.println("Part 2: " + puzzle07.smallestDirSizeForDeletion(70000000, 30000000));

        System.out.println("Day 8");
        Day08 puzzle08 = new Day08("/Day08_Input.txt");
        System.out.println("Part 1: " + puzzle08.treesVisible());
        System.out.println("Part 2: " + puzzle08.maxTreeScenicScore());

        System.out.println("Day 9");
        Day09 puzzle09 = new Day09("/Day09_Input.txt");
        System.out.println("Part 1: " + puzzle09.distinctPositionsVisitedByTail(1));
        System.out.println("Part 2: " + puzzle09.distinctPositionsVisitedByTail(9));

        System.out.println("Day 10");
        Day10 puzzle10 = new Day10("/Day10_Input.txt");
        System.out.println("Part 1: " + puzzle10.sumSignalStrengthsAtCycles(List.of(20, 60, 100, 140, 180, 220)));
        System.out.println("Part 2:");
        puzzle10.drawCRT();
    }
}