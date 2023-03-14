package com.wheel.sys;

import com.wheel.resources.Puzzle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board = new Board();

    @Before
    public void initialize(){
        board.setCurrentPuzzle(new Puzzle("CHITTY CHITTY BANG BANG AEIOU", "Title", 0));
    }

    @Test
    public void showSolution() {
        board.showSolution();
    }
}