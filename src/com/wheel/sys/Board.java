package com.wheel.sys;

import java.util.ArrayList;
import java.util.List;

class Board {
    List<Puzzle> puzzles = new List<>();
    List<String> banner = new ArrayList<>(); //Read banner from file
    List<Letter> wrongGuesses = new ArrayList<>();

    public void revealLetter(Letter letter){
        //update the board to show the letter
    }

    public void displayBanner(){
        //Initial startup banner
    }

    public void revealBoard(){
        //create transition from banner to board
    }

    public boolean isSolution(String solution){
        boolean solved = false;
        //delegate the solving to the puzzle.
        if(solved){
            revealSolution();
        }
        return solved;
    }

    void revealSolution(){
        //This should show all of the letters in the solution.
    }

    public boolean isLetterCorrect(Letter guess){
        boolean correct = false;
        //logic here
        if(correct){
            revealLetter(guess);
        }
        return correct;
    }
}