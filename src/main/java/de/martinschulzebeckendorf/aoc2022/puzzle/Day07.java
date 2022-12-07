package de.martinschulzebeckendorf.aoc2022.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Day07 extends Day {

    private final Dir fileSystem;

    public Day07(String inputFileName) {
        super(inputFileName);

        List<String> instructions = this.inputText.lines()
                .skip(1) // move into root dir "/"
                .filter(instruction -> !instruction.contains("$ ls")) // unnötig
                .toList();

        this.fileSystem = new Dir(null, "/");
        // Parse into AoCFile and Dir, create FileSystem Tree
        Dir currentDir = this.fileSystem;
        for (String instruction : instructions) {
            if (instruction.contains("$ cd")) {
                String dir = instruction.substring(instruction.lastIndexOf(" ") + 1);
                if (dir.equals("..")) {
                    currentDir = currentDir.getParentDir();
                } else {
                    currentDir = currentDir.getSubDirByPathName(dir);
                }
            } else if (instruction.startsWith("dir")) {
                currentDir.addSubDir(new Dir(currentDir, instruction.substring(instruction.lastIndexOf(" ") + 1)));
            } else {
                String[] fileParts = instruction.split(" ");
                currentDir.addFile(new AoCFile(fileParts[1], Integer.parseInt(fileParts[0])));
            }
        }
    }

    public int sumOfDirectorySizesMax100k() {
        return this.fileSystem.getDirsRecursive().stream()
                .mapToInt(Dir::getDirSizeRecursive)
                .filter(dirSize -> dirSize <= 100000)
                .sum();
    }

    public int smallestDirSizeForDeletion(int totalSize, int requiredSize) {
        int additionalSpaceNeeded = requiredSize - (totalSize - this.fileSystem.getDirSizeRecursive());
        return this.fileSystem.getDirsRecursive().stream()
                .mapToInt(Dir::getDirSizeRecursive)
                .filter(dirSize -> dirSize >= additionalSpaceNeeded)
                .sorted()
                .findFirst() //Smallest
                .orElse(0);
    }
}

class Dir {

    private final Dir parentDir;
    private final List<Dir> subDirs;
    private final List<AoCFile> files;
    private final String name;

    public Dir(Dir parentDir, String name) {
        this.parentDir = parentDir;
        this.name = name;
        this.subDirs = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public Dir getSubDirByPathName(String name) {
        for (Dir dir : this.subDirs) {
            if (dir.getName().equals(name)) {
                return dir;
            }
        }
        return null;
    }

    public List<Dir> getDirsRecursive() {
        List<Dir> dirs = new ArrayList<>(this.subDirs);
        if (this.parentDir == null) {
            dirs.add(this);
        }
        for (Dir subDir : this.subDirs) {
            dirs.addAll(subDir.getDirsRecursive());
        }
        return dirs;
    }

    public int getDirSizeRecursive() {
        return this.subDirs.stream().mapToInt(Dir::getDirSizeRecursive).sum() + this.files.stream().mapToInt(AoCFile::size).sum();
    }

    public void addSubDir(Dir subDir) {
        this.subDirs.add(subDir);
    }

    public void addFile(AoCFile file) {
        this.files.add(file);
    }

    public Dir getParentDir() {
        return parentDir;
    }

    public String getName() {
        return name;
    }
}

record AoCFile(String name, int size) {

}