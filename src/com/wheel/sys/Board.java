package com.wheel.sys;

import com.apps.util.Console;
import com.wheel.resources.Letter;
import com.wheel.resources.Puzzle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    List<String> banner = new ArrayList<>(); //Read banner from file
    List<Letter> wrongGuesses = new ArrayList<>();
    List<String> boardLines = loadBoardLinesFromFile();
    BoardPuzzleManager manager;


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

    public void showSolution(){
        topFiveLines();
        categoryLineFive();
        for(int i = 5; i < 7; i++){
            System.out.println(boardLines.get(i));
        }
        ArrayList<String[]> puzzleWords = manager.determineLines();
        int index = 0;
        if(puzzleWords.size() > 2){
            //║ R ╔═╝|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|╚═╗ R ║
            String line1 = Arrays.toString(puzzleWords.get(0));
            System.out.println(line1);
        } else {
            System.out.println(boardLines.get(7));
            System.out.println(boardLines.get(8));
            String mask = boardLines.get(9);
            String line = solutionLine(puzzleWords.get(index++));
            System.out.println(overlaySolution(mask, line));
            System.out.println(boardLines.get(10));

            mask = boardLines.get(11);
            line = solutionLine(puzzleWords.get(index));
            System.out.println(overlaySolution(mask, line));
            System.out.println(boardLines.get(12));



        }
    }

    private String overlaySolution(String base, String overlay){
        int baseLength = base.length();
        int overlayLength = overlay.length();
        int paddingLength = (baseLength - overlayLength) / 2;

        String leftPadding;
        String rightPadding;
        if (paddingLength % 2 == 0) { //paddingLength--;
             leftPadding = base.substring(0, paddingLength - 1);
             rightPadding = base.substring(baseLength - paddingLength - 1);
        } else {
            leftPadding = base.substring(0, paddingLength);
            rightPadding = base.substring(baseLength - paddingLength);
        }
        String paddedOverlay = leftPadding + overlay + rightPadding;

            return paddedOverlay;
    }

    private String solutionLine(String[] puzzleWords) {
        String line1 = Arrays.toString(puzzleWords).replace("[","").
                replace("]","").replace(",","").replace(" ", "▒");
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < line1.length(); i++) {
            sb.append(line1.charAt(i));
            if (i != line1.length()) {
                sb.append("|");
            }
        }
        return sb.toString();
    }

    private void topFiveLines() {
        for(int i = 0; i < 4; i++){
            System.out.println(boardLines.get(i));
        }
    }

    private void categoryLineFive() {
        String category = currentPuzzle.getCategory();
        System.out.printf("║");
        int leading = (36 - category.length())/2;
        printSpaces(leading);
        System.out.printf(category);
        printSpaces(37 - category.length() - leading);
        System.out.printf("║\n");
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
        manager = new BoardPuzzleManager(currentPuzzle);
    }

    private void printSpaces(int num){
        for(int i = 0; i < num; i++){
            System.out.printf(" ");
        }
    }
}