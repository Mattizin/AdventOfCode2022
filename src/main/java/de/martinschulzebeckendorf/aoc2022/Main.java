package de.martinschulzebeckendorf.aoc2022;

import de.martinschulzebeckendorf.aoc2022.puzzle.Day01;
import de.martinschulzebeckendorf.aoc2022.puzzle.Day02;
import de.martinschulzebeckendorf.aoc2022.puzzle.Day03;
import de.martinschulzebeckendorf.aoc2022.puzzle.Day04;

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
    }
}