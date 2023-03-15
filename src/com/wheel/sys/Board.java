package com.wheel.sys;

import com.wheel.resources.Puzzle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Places where there are words will be filled with "▓".
 * Blanks will be filled with "░".
 */

public class Board {
    List<String> banner = new ArrayList<>(); //Read banner from file
    List<String> boardLines = loadBoardLinesFromFile();
    BoardPuzzleManager manager;
    List<Player> players;
    private StringBuilder correctGuesses = new StringBuilder();
    StringBuilder wrongGuesses = new StringBuilder();


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



    public void displayBanner(){
        //Initial startup banner
    }

    /*
     * Possible extra
     */
    public void revealBoard(){
        //create transition from banner to board
    }




    public void displayPlayers(){
        // player information on bottom of board. Name, number, and amount of money

    }


    public void showSolution(){
        topFiveLines();
        categoryLineFive();
        for(int i = 5; i < 7; i++){
            System.out.println(boardLines.get(i));
        }
        ArrayList<String[]> puzzleWords = manager.determineLines();
        int index = 0;
        //This portion works right now but could be refactored later to make it more compact.
        if(puzzleWords.size() > 2){
            String line1 = solutionLine(puzzleWords.get(index++));
            String mask = boardLines.get(7);
            System.out.println(overlaySolution(mask, line1));
            System.out.println(boardLines.get(8));
            line1 = solutionLine(puzzleWords.get(index++));
            mask = boardLines.get(9);
            System.out.println(overlaySolution(mask, line1));

            System.out.println(boardLines.get(10));
            line1 = solutionLine(puzzleWords.get(index++));
            mask = boardLines.get(11);
            System.out.println(overlaySolution(mask, line1));

            System.out.println(boardLines.get(12));

            if(index != puzzleWords.size()){
                line1 = solutionLine(puzzleWords.get(puzzleWords.size() - 1));
                mask = boardLines.get(13);
                System.out.println(overlaySolution(mask, line1));
            } else {
                    System.out.println(boardLines.get(13));
            }
        } else {
            System.out.println(boardLines.get(7));
            System.out.println(boardLines.get(8));
            String mask = boardLines.get(9);
            String line = solutionLine(puzzleWords.get(index++));
            System.out.println(overlaySolution(mask, line));
            System.out.println(boardLines.get(10));
            if(puzzleWords.size() == 2){
                mask = boardLines.get(11);
                line = solutionLine(puzzleWords.get(index));
                System.out.println(overlaySolution(mask, line));

            } else {
                System.out.println(boardLines.get(11));
            }
            System.out.println(boardLines.get(12));
            System.out.println(boardLines.get(13));
        }
        updateRoundMoney();
        for(int i = 14; i <= 20; i++){
            System.out.println(boardLines.get(i));
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

        return leftPadding + overlay + rightPadding;
    }

    private String solutionLine(String[] puzzleWords) {
        String line1 = Arrays.toString(puzzleWords).replace("[","").
                replace("]","").replace(",","").replace(" ", "░");


        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < line1.length(); i++) {
            sb.append(line1.charAt(i));
            if (i != line1.length()) {
                sb.append("|");
            }
        }
        return maskSolutionLine(sb.toString());
    }

    private String maskSolutionLine(String line){
        return line.replaceAll(getAnswerMask(),"▓");
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

    public void setPlayers(List<Player> players) {
        this.players = players;
        StringBuilder sb = new StringBuilder();
        sb.append("║    ");
        int count = 0;
        for(var player : players){
            sb.append(padBottomBracket(player.getName()));
            count++;
            if(count < 2) sb.append("|");
        }
        if(players.size() == 2) sb.append("|         ");
        sb.append("    ║");
        boardLines.set(17, sb.toString());
    }

    public void updateRound(Integer round){
        boardLines.set(13,boardLines.get(13).replaceAll("\\d", round.toString()));
        correctGuesses = new StringBuilder();
    }

    private void updateRoundMoney(){
        StringBuilder sb = new StringBuilder();
        sb.append("║    ");
        int count = 0;
        for(var player : players){
            sb.append(padBottomBracket("$" + player.getRoundBalance()));
            count++;
            if(count < 2) sb.append("|");
        }
        if(players.size() == 2) sb.append("|         ");
        sb.append("    ║");
        boardLines.set(18, sb.toString());
    }

    public void markCurrentPlayer(int playerIndex) {
        String old = "║                                     ║";
        int index = (playerIndex + 1) * 10 - 1;
        String updated = old.substring(0, index) + "*" + old.substring(index + 1);
        boardLines.set(16,updated);
    }

    private String padBottomBracket(String input){
        int paddingLength = 9 - input.length();
        int paddingBefore = paddingLength / 2;
        int paddingAfter = paddingLength - paddingBefore;
        String padding = " ".repeat(paddingBefore) + " ".repeat(paddingAfter);
        return padding.substring(0, paddingBefore) + input + padding.substring(paddingBefore);

    }

    public void recordCorrectGuess(String guess){
        correctGuesses.append(guess);
        System.out.println(correctGuesses);
    }

    private String getAnswerMask(){
        return "[^-?!░|" + correctGuesses.toString() + "]";
    }
}