package com.wheel.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private final String puzzle;
    private final String category;
    private int timesUsed;


    public static class PuzzleFactory {
        private static List<Puzzle> puzzleList = new ArrayList<>();
        private static List<String> lines = null;

        public static void readPuzzlesFromFile()
        {
            try {
                lines = Files.readAllLines(Path.of("puzzles/puzzles.csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(var line : lines) {
                String[] tokens = line.split(",");
                Puzzle puzzle = new Puzzle(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                puzzleList.add(puzzle);

            }
        }
        public static void markPuzzleAsUsed(Puzzle puzzle) {
            int index = puzzleList.indexOf(puzzle);
            if (index != -1) {
                Puzzle updatedPuzzle = puzzleList.get(index);
                updatedPuzzle.timesUsed++;
            }
        }

    }

    public Puzzle(String puzzle, String category, int timesUsed) {
        this.puzzle = puzzle;
        this.category = category;
        this.timesUsed = timesUsed;
    }

    public boolean checkLetter(Letter letter) {
        boolean result = false;
        if (puzzle.contains(letter.toString())) {
            result = true;
        }
        return result;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public String getCategory() {
        return category;
    }

    public int getUsed() {
        return timesUsed;
    }

    public boolean checkSolution(String solutionGuess) {
        return getPuzzle().equalsIgnoreCase(solutionGuess);
    }
}