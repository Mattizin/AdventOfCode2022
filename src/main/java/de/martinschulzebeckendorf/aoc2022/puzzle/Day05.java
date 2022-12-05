package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.*;
import java.util.stream.Collectors;

public class Day05 extends Day {

    private final List<String> initalStacksInstruction;
    private final List<Rearrangement> rearrangementProcedure = new ArrayList<>();

    public Day05(String inputFileName) {
        super(inputFileName);

        Map<Boolean, List<String>> inputParts = this.inputText.lines()
                .collect(Collectors.partitioningBy(l -> l.startsWith("move")));

        // Inital Stack Layout
        this.initalStacksInstruction = new java.util.ArrayList<>(inputParts.get(false).stream().toList());
        Collections.reverse(this.initalStacksInstruction);
        // Remove empty previous split line
        this.initalStacksInstruction.remove(0);

        // Moves
        inputParts.get(true)
                .forEach(s -> this.rearrangementProcedure.add(Rearrangement.createFromInstructionText(s)));
    }

    public String calcGratesOnTop() {
        List<Stack<String>> crateStacks = this.loadInitialCrateStacks();
        this.rearrangementProcedure.forEach(r -> {
            for (int i = 0; i < r.quantity(); i++) {
                crateStacks.get(r.to() - 1).push(crateStacks.get(r.from() - 1).pop());
            }
        });

        return crateStacks.stream()
                .map(Stack::peek)
                .collect(Collectors.joining());
    }

    public String calcGratesOnTopCrateMover9001() {
        List<Stack<String>> crateStacks = this.loadInitialCrateStacks();
        // To move multiple in same order first put them on a third Stack and from there to destination
        this.rearrangementProcedure.forEach(r -> {
            Stack<String> tmp = new Stack<String>();
            for (int i = 0; i < r.quantity(); i++) {
                tmp.push(crateStacks.get(r.from() - 1).pop());

            }
            for (int i = 0; i < r.quantity(); i++) {
                crateStacks.get(r.to() - 1).push(tmp.pop());
            }
        });

        return crateStacks.stream()
                .map(Stack::peek)
                .collect(Collectors.joining());
    }

    private List<Stack<String>> loadInitialCrateStacks() {
        List<Stack<String>> crateStacks = new ArrayList<>();
        // Initalize stacks
        for (int i = 0; i < Integer.parseInt(this.initalStacksInstruction.get(0).substring(this.initalStacksInstruction.get(0).length() - 1)); i++) {
            crateStacks.add(new Stack<>());
        }
        this.initalStacksInstruction.stream()
                .skip(1) //Erste Zeile ist die Angabe der Anzahl der Stacks, die Oben zum Initialiseren verwendet wird.
                .forEach(crates -> {
                    for (int i = 0; i <= crateStacks.size(); i++) {
                        int stackIndex = i * 4 + 1;
                        if (crates.length() >= stackIndex && !crates.substring(stackIndex, stackIndex + 1).isBlank()) {
                            crateStacks.get(i).push(crates.substring(stackIndex, stackIndex + 1));
                        }
                    }
                });

        return crateStacks;
    }
}

record Rearrangement(int from, int to, int quantity) {

    public static Rearrangement createFromInstructionText(String instructionText) {
        // Instruction String looks like:
        // move 1 from 2 to 1
        String[] parts = instructionText.split(" ");
        int quantity = Integer.parseInt(parts[1]);
        int from = Integer.parseInt(parts[3]);
        int to = Integer.parseInt(parts[5]);

        return new Rearrangement(from, to, quantity);
    }
}
