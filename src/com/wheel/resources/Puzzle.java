package com.wheel.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Puzzle {
    private final String puzzle;
    private final String category;
    private int timesUsed;

    public static class PuzzleFactory {
        private static final List<Puzzle> puzzleList = readPuzzlesFromFile();
        private static List<String> lines = null;

        private static List<Puzzle> readPuzzlesFromFile() {
            List<Puzzle> puzzles = new ArrayList<>();
            try {
                lines = Files.readAllLines(Path.of("puzzles/puzzles.csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert lines != null;
            for (var line : lines) {
                String[] tokens = line.split(",");
                Puzzle puzzle = new Puzzle(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
                puzzles.add(puzzle);
            }
            return puzzles;
        }

        public static Puzzle getRandomPuzzle() {
            Random random = new Random();
            int index = random.nextInt(puzzleList.size());
            return puzzleList.get(index);
        }

        /**
         * To be implemented later.
         *
         * @param puzzle
         */
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


    public int checkLetter(String letter) {
        int count = 0;
        int index = 0;
        while ((index = puzzle.indexOf(letter, index)) != -1) {
            count++;
            index += letter.length();
        }
        return count;
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