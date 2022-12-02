package de.martinschulzebeckendorf.aoc2022;

import de.martinschulzebeckendorf.aoc2022.puzzle.Day01;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2022!");

        System.out.println("Day 1");
        Day01 puzzle01 = new Day01("/Day01_Input.txt");
        System.out.println("Part 1: " + puzzle01.getCaloriesOfElfCarryingMost());
        System.out.println("Part 2: " + puzzle01.getCaloriesOf3ElfesCarryingMost());
    }
}