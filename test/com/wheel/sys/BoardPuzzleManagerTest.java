package com.wheel.sys;

import com.wheel.resources.Puzzle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardPuzzleManagerTest {
    BoardPuzzleManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new BoardPuzzleManager(Puzzle.PuzzleFactory.getRandomPuzzle());
    }

    @Test
    public void determineLines_shouldReturnArrayList_withAppropriateElements(){
//        for(var item : manager.determineLines()){
//            System.out.println(Arrays.toString(item));
//        }

        for(int i = 0; i < 2000; i++){
            System.out.println(i);
            manager = new BoardPuzzleManager(Puzzle.PuzzleFactory.getRandomPuzzle());
            ArrayList<String[]> lines = manager.determineLines();
            for(var item : lines){
                System.out.println(Arrays.toString(item));
            }
            assertTrue(lines.size() < 5 && lines.size() > 0);
        }
    }
}