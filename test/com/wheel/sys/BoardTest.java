package com.wheel.sys;

import com.wheel.resources.Puzzle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    Board board = new Board();

    @Before
    public void initialize() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Stephen"));
        players.add(new Player("Chad"));
        players.add(new Player("Jay"));
        board.setPlayers(players);
        board.updateRound(2);
        //board.recordCorrectGuess("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        String puzzle = "JINGLE BELLS JINGLE BELLS JINGLE ALL THE WAY";
        //puzzle = "CHITTY CHITTY BANG BANG AEIOU";
        puzzle = "LIFE IS A HIGHWAY I WANT TO RIDE IT ALL NIGHT LONG";
        board.setCurrentPuzzle(new Puzzle(puzzle, "Title", 0));
    }

    @Test
    public void showSolution() {
        for (int i = 0; i < 10; i++) {
            board.setCurrentPuzzle(Puzzle.PuzzleFactory.getRandomPuzzle());
            System.out.println(board.getCurrentPuzzle().getPuzzle());
            board.showSolution();
        }
//        board.showSolution();
    }

    @Test
    public void displayBanner(){
        board.displayBanner();
    }

    @Test
    public void displayRound() {
        board.displayRound(1);
    }

    @Test
    public void showRoundWinner() {
        board.showRoundWinner(new Player("Stephen"));
    }
}