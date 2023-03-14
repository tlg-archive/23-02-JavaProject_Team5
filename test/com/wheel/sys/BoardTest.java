package com.wheel.sys;

import com.wheel.resources.Puzzle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board = new Board();

    @Before
    public void initialize(){

        String puzzle = "JINGLE BELLS JINGLE BELLS JINGLE ALL THE WAY";
        //puzzle = "CHITTY CHITTY BANG BANG AEIOU";
        board.setCurrentPuzzle(new Puzzle(puzzle, "Title", 0));
    }

    @Test
    public void showSolution() {
        for(int i = 0; i < 1000; i++){
            board.setCurrentPuzzle(Puzzle.PuzzleFactory.getRandomPuzzle());
            board.showSolution();
        }
//        board.showSolution();
    }
}