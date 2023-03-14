package com.wheel.sys;

import com.wheel.resources.Letter;
import com.wheel.resources.Puzzle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Board {
    List<String> banner = new ArrayList<>(); //Read banner from file
    List<Letter> wrongGuesses = new ArrayList<>();
    List<String> boardLines = loadBoardLinesFromFile();


    Puzzle currentPuzzle;

    private static List<String> loadBoardLinesFromFile() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of("textFiles/boardBase.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Board getInstance(){
        return new Board();
    }

    public void revealLetter(Letter letter){
        //update the board to show the letter
    }

    public void displayBanner(){
        //Initial startup banner
    }

    /*
     * Possible extra
     */
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

    public boolean isCorrect(Letter guess){
        boolean correct = false;
        //logic here
        if(correct){
            revealLetter(guess);
        }
        return correct;
    }

    public void displayPlayers(){
        // player information on bottom of board. Name, number, and amount of money

    }

    public void updateBoard(String guess){

    }

    public void updateBoard(){

    }


    public void displayBoard(Puzzle puzzle) {
        setCurrentPuzzle(puzzle);
        for(var line : boardLines){
            System.out.println(line);
        }
    }


    public Puzzle getCurrentPuzzle() {
        return currentPuzzle;
    }

    public void setCurrentPuzzle(Puzzle currentPuzzle) {
        this.currentPuzzle = currentPuzzle;
    }
}