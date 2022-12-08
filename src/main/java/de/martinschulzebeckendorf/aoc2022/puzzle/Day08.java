package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.List;

public class Day08 extends Day {

    private final int[][] forest;

    public Day08(String inputFileName) {
        super(inputFileName);

        List<String> rows = this.inputText.lines().toList();
        this.forest = new int[rows.get(0).length()][rows.size()];
        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                this.forest[i][j] = Integer.parseInt(rows.get(i).substring(j, j + 1));
            }
        }
    }

    public int treesVisible() {
        // Äußerer Ring ist immer sichtbar, direkt zum Ergebnis hinzufügen.
        int visible = (this.forest.length - 1) * 2 + (this.forest[0].length - 1) * 2;
        // Alle aus dem inneren Bereich nacheinander prüfen in alle 4 Richtungen nach außen,
        // ob nur kleinere Bäume davor stehen, wenn in einer Richtung ja, dann ist er sichtbar, also müssen andere Richtungen nicht mehr geprüft werden
        for (int y = 1; y < this.forest.length - 1; y++) {
            treeCheckLoop:
            for (int x = 1; x < this.forest[0].length - 1; x++) {
                int checkTree = this.forest[y][x];
                // System.out.println("Check Tree at position x:" + x + ", y:" + y + " h:" + checkTree);
                // check left
                for (int k = x - 1; k >= 0; k--) {
                    if (this.forest[y][k] < checkTree) {
                        if (k == 0) {
                            // System.out.println("Visibly left");
                            visible++;
                            continue treeCheckLoop;
                        }
                    } else {
                        break;
                    }
                }
                // check top
                for (int k = y - 1; k >= 0; k--) {
                    if (this.forest[k][x] < checkTree) {
                        if (k == 0) {
                            // System.out.println("Visibly top");
                            visible++;
                            continue treeCheckLoop;
                        }
                    } else {
                        break;
                    }
                }
                // check right
                for (int k = x + 1; k < this.forest.length; k++) {
                    if (this.forest[y][k] < checkTree) {
                        if (k == this.forest.length - 1) {
                            // System.out.println("Visibly right");
                            visible++;
                            continue treeCheckLoop;
                        }
                    } else {
                        break;
                    }
                }
                // check bottom
                for (int k = y + 1; k < this.forest[0].length; k++) {
                    if (this.forest[k][x] < checkTree) {
                        if (k == this.forest[0].length - 1) {
                            // System.out.println("Visibly bottom");
                            visible++;
                            continue treeCheckLoop;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        return visible;
    }

    public int maxTreeScenicScore() {
        int maxScenicScore = 0;
        // Jeden Baum in alle Richtungen ablaufen, ähnlich zu Part 1 innerer Teil und Abstand bis zum Blocken merken
        // Äußrer Ring ist mind. 1 Richtung 0 Bäume sichtbar, also scenicScore immer 0, kann daher ignoriert werden
        for (int y = 1; y < this.forest.length - 1; y++) {
            for (int x = 1; x < this.forest[0].length - 1; x++) {
                int scenicScore = 1; // Da der äußere Ring ignoriert wurde 1 als Basis für Scenic Multiplikationen
                int checkTree = this.forest[y][x];
                //System.out.println("Check Tree at position x:" + x + ", y:" + y + " h:" + checkTree);
                // Go left
                for (int k = x - 1; k >= 0; k--) {
                    if (this.forest[y][k] >= checkTree || k == 0) {
                        scenicScore *= x - k;
                        break;
                    }
                }
                // Go top
                for (int k = y - 1; k >= 0; k--) {
                    if (this.forest[k][x] >= checkTree || k == 0) {
                        scenicScore *= y - k;
                        break;
                    }
                }
                // Go right
                for (int k = x + 1; k < this.forest.length; k++) {
                    if (this.forest[y][k] >= checkTree || k == this.forest.length - 1) {
                        scenicScore *= k - x;
                        break;
                    }
                }
                // Go bottom
                for (int k = y + 1; k < this.forest[0].length; k++) {
                    if (this.forest[k][x] >= checkTree || k == this.forest[0].length - 1) {
                        scenicScore *= k - y;
                        break;
                    }
                }

                //System.out.println(scenicScore);
                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
        }

        return maxScenicScore;
    }
}
