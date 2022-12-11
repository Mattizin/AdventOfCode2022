package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Day10 extends Day {

    private final List<String> instructions;

    public Day10(String inputFileName) {
        super(inputFileName);

        this.instructions = this.inputText.lines().toList();
    }

    public int sumSignalStrengthsAtCycles(List<Integer> cycles) {
        List<CPUCycle> cpuCycleLog = this.executeCPUInstructions(this.instructions);

        return cpuCycleLog.stream()
                .filter(cpuCycle -> cycles.contains(cpuCycle.cycle()))
                .mapToInt(cpuCycle -> cpuCycle.cycle() * cpuCycle.startValue())
                .sum();
    }

    public void drawCRT() {
        List<CPUCycle> cpuCycleLog = this.executeCPUInstructions(this.instructions);

        // Drawing starts with cycle 1
        cpuCycleLog.remove(0);
        for (int i = 0; i < cpuCycleLog.size(); i++) {
            CPUCycle cycle = cpuCycleLog.get(i);
            int vertPos = i % 40;
            if (vertPos >= cycle.startValue() - 1 && vertPos <= cycle.startValue() + 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            if (vertPos == 39) {
                //Line Break
                System.out.println();
            }
        }
    }

    private List<CPUCycle> executeCPUInstructions(List<String> instructions) {
        // Cycle, Value of variable x
        List<CPUCycle> cpuCycleLog = new ArrayList<>(List.of(new CPUCycle(0, 0, 1)));
        // Apply all instructions
        for (String instruction : instructions) {
            CPUCycle currentCycle = cpuCycleLog.get(cpuCycleLog.size() - 1);
            String[] parts = instruction.split(" ");
            // Both instructions need one cycle changing nothing
            cpuCycleLog.add(new CPUCycle(currentCycle.cycle() + 1, currentCycle.endValue(), currentCycle.endValue()));
            // addx Instruction additionally changes register value after cycle 2
            if (parts[0].equals("addx")) {
                cpuCycleLog.add(new CPUCycle(
                        currentCycle.cycle() + 2,
                        currentCycle.endValue(),
                        currentCycle.endValue() + Integer.parseInt(parts[1])));
            }
        }

        return cpuCycleLog;
    }
}

record CPUCycle(int cycle, int startValue, int endValue) {
}