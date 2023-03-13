package com.wheel.resources;

public class Puzzle {
    String puzzle;
    String category;
    int used;

    public Puzzle(String puzzle, String category){
        this.puzzle = puzzle;
        this.category = category;
    }

    public boolean checkLetter(Letter letter){
        boolean result = false;
        if(puzzle.contains(letter.toString())){
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
        return used;
    }

    public boolean checkSolution(String solutionGuess) {
        return getPuzzle().equalsIgnoreCase(solutionGuess);
    }
}