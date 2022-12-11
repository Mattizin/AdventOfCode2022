package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day11 extends Day {

    private final List<Monkey> monkeys = new ArrayList<>();

    public Day11(String inputFileName) {
        super(inputFileName);

        // Input parsing
        Monkey monkey = null;
        for (String line : this.inputText.lines().map(String::trim).toList()) {
            //Ignore
            if (line.isBlank() || line.startsWith("Monkey")) {
                continue;
            }
            if (line.startsWith("Starting")) {
                String[] items = line.substring(line.lastIndexOf(":") + 2).split(", ");
                monkey = new Monkey(Arrays.stream(items).map(s -> new MonkeyItem(Long.parseLong(s))).toList());
                continue;
            }
            assert monkey != null;
            if (line.startsWith("Operation")) {
                monkey.setOperation(new WorryLevelOperation(line.substring(line.lastIndexOf("= old") + 6)));
                continue;
            }
            if (line.startsWith("Test")) {
                monkey.setDivisibleBy(Long.parseLong(line.substring(line.lastIndexOf(" ") + 1)));
                continue;
            }
            int monkeyIndex = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
            if (line.startsWith("If true")) {
                monkey.setMonkeyTrueIndex(monkeyIndex);
                continue;
            }
            if (line.startsWith("If false")) {
                monkey.setMonkeyFalseIndex(monkeyIndex);
                // Full monkey parsed
                this.monkeys.add(monkey);
                monkey = null;
            }
        }
    }

    public long businessLevelMostActiveMonkey(int rounds, boolean worryRelief) {
        long testDivisorProduct = 0L;
        if (!worryRelief) {
            // Math idea by https://todd.ginsberg.com/post/advent-of-code/2022/day11/
            // instead of storing the real worrying level, store the modulo with the leas common divisor of all test "divide by" cases
            testDivisorProduct = this.monkeys.stream()
                    .mapToLong(Monkey::getDivisibleBy)
                    .reduce((a, b) -> a * b)
                    .orElse(0L);
        }

        for (int round = 1; round <= rounds; round++) {
            for (Monkey monkey : this.monkeys) {
                for (MonkeyItem item : monkey.getItems()) {
                    MonkeyItem itemInspected = monkey.inspectItem(item, worryRelief, testDivisorProduct);
                    this.monkeys.get(monkey.getAfterInspectMonkeyReceiverIndex(itemInspected.worryLevel())).addItem(itemInspected);
                }
                monkey.getItems().clear();
            }
        }

        List<Long> sortedInspectCounts = new ArrayList<>(this.monkeys.stream()
                .map(Monkey::getInspectCount)
                .sorted()
                .toList());
        Collections.reverse(sortedInspectCounts);
        return sortedInspectCounts.stream()
                .mapToLong(Long::longValue)
                .limit(2)
                .reduce((a, b) -> a * b)
                .orElse(0L);
    }
}

class Monkey {
    private final List<MonkeyItem> items;
    private WorryLevelOperation operation;
    private long divisibleBy;
    private int monkeyTrueIndex, monkeyFalseIndex;
    private long inspectCount;

    public Monkey(List<MonkeyItem> items) {
        this.items = new ArrayList<>(items);
        this.inspectCount = 0L;
    }

    public MonkeyItem inspectItem(MonkeyItem item, boolean worryRelief, long testDivisorProduct) {
        this.inspectCount++;
        long worryLevel = this.operation.apply(item.worryLevel());
        if (worryRelief) {
            worryLevel /= 3;
        } else {
            // Math idea by https://todd.ginsberg.com/post/advent-of-code/2022/day11/
            worryLevel %= testDivisorProduct;
        }
        return new MonkeyItem(worryLevel);
    }

    public int getAfterInspectMonkeyReceiverIndex(long worryLevel) {
        return worryLevel % this.divisibleBy == 0L ? this.monkeyTrueIndex : this.monkeyFalseIndex;
    }

    public void addItem(MonkeyItem item) {
        this.items.add(item);
    }

    public void setOperation(WorryLevelOperation operation) {
        this.operation = operation;
    }

    public void setDivisibleBy(long divisibleBy) {
        this.divisibleBy = divisibleBy;
    }

    public void setMonkeyTrueIndex(int monkeyTrueIndex) {
        this.monkeyTrueIndex = monkeyTrueIndex;
    }

    public void setMonkeyFalseIndex(int monkeyFalseIndex) {
        this.monkeyFalseIndex = monkeyFalseIndex;
    }

    public List<MonkeyItem> getItems() {
        return items;
    }

    public long getDivisibleBy() {
        return divisibleBy;
    }

    public long getInspectCount() {
        return inspectCount;
    }
}

record WorryLevelOperation(String instruction) {

    public long apply(long level) {
        String[] parts = instruction.split(" ");
        long value = level;
        if (!parts[1].equals("old")) {
            level = Long.parseLong(parts[1]);
        }
        switch (parts[0]) {
            case "+" -> {
                return level + value;
            }
            case "-" -> {
                return level - value;
            }
            case "*" -> {
                return level * value;
            }
            case "/" -> {
                return level / value;
            }
        }
        return 0;
    }
}

record MonkeyItem(long worryLevel) {
}