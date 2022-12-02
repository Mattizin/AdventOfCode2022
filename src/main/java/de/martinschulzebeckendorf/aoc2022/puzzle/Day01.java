package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 extends Day {

    private final List<Elf> elfes = new ArrayList<>();

    public Day01(String inputFileName) {
        super(inputFileName);

        List<String> calories = new ArrayList<>();
        this.inputText.lines().forEachOrdered(s -> {
            if(s.isEmpty() || s.isBlank()) {
                elfes.add(new Elf(calories.toArray(String[]::new)));
                calories.clear();
            } else {
                calories.add(s);
            }
        });
        //last one
        elfes.add(new Elf(calories.toArray(String[]::new)));
    }

    public int getCaloriesOfElfCarryingMost() {
        return this.getSortedCaloriesCarried(1).get(0);
    }

    public int getCaloriesOf3ElfesCarryingMost() {
        return this.getSortedCaloriesCarried(3).stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private List<Integer> getSortedCaloriesCarried(long limit) {
        return this.elfes.stream()
                .map(Elf::totalCalories)
                .sorted(Collections.reverseOrder())
                .limit(limit)
                .collect(Collectors.toList());
    }
}

record Elf(String[] calories) {
    int totalCalories() {
        return Arrays.stream(calories).mapToInt(Integer::valueOf).sum();
    }
}
