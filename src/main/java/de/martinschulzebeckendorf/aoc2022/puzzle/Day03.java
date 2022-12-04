package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Day03 extends Day {

    private final List<String> bags = new ArrayList<>();

    public Day03(String inputFileName) {
        super(inputFileName);

        this.inputText.lines().forEach(bags::add);
    }

    public int calcPrioritySumOfSharedItems() {
        return bags.stream()
                .map(s -> { //Split int bag halfs
                    final int mid = s.length() / 2;
                    return new String[]{s.substring(0, mid), s.substring(mid)};
                })
                .map(bagCompartments -> { //Check of identical chars (each once!)
                    List<Character> itemsSharedInBag = new ArrayList<>();
                    for (int i = 0; i < bagCompartments[0].length(); i++) {
                        char checkItem = bagCompartments[0].charAt(i);
                        if (!itemsSharedInBag.contains(checkItem) && bagCompartments[1].indexOf(checkItem) != -1) {
                            itemsSharedInBag.add(checkItem);
                        }
                    }
                    return itemsSharedInBag;
                })
                .map(itemsSharedInBag -> { // Convert to priorities per bag with adjusted ASCII values
                    AtomicInteger bagPriorities = new AtomicInteger();
                    itemsSharedInBag.forEach(item -> {
                        bagPriorities.addAndGet(Character.isLowerCase(item) ? ((int) item - 96) : ((int) item - 38));
                    });
                    return bagPriorities.get();
                })//sum up
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public int calcPioritySumOfBadgeItems() {
        List<List<String>> groupsBags = new ArrayList<>();
        List<String> groupBag = new ArrayList<>();
        for (int i = 1; i <= this.bags.size(); i++) {
            groupBag.add(this.bags.get(i - 1));
            if (i % 3 == 0) {
                groupsBags.add(groupBag);
                groupBag = new ArrayList<>();
            }
        }

        return groupsBags.stream()
                .map(groupBags -> { //Check in Group (3 Strings) for first shared character and convert to priorities per bag with adjusted ASCII values
                    for (int i = 0; i < groupBags.get(0).length(); i++) {
                        char checkItem = groupBags.get(0).charAt(i);
                        if (groupBags.get(1).indexOf(checkItem) != -1 && groupBags.get(2).indexOf(checkItem) != -1) {
                            return Character.isLowerCase(checkItem) ? ((int) checkItem - 96) : ((int) checkItem - 38);
                        }
                    }
                    return null;
                }).filter(Objects::nonNull)
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
