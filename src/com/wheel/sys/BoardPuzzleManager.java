package com.wheel.sys;

import com.wheel.resources.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class manages the puzzle on the board
 * 1234567890123456789012345678901
 *   ╔═════════════════════════╗   1
 *   ║+-+-+-+-+-+-+-+-+-+-+-+-+║   2
 * ╔═╝|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|╚═╗ 3
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+-+-+║ 4
 * ║|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|║ 5
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+-+-+║ 6
 * ║|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|║ 7
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+-+-+║ 8
 * ╚═╗|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|╔═╝ 9
 *   ║+-+-+-+-+-+-+-+-+-+-+-+-+║   10
 *   ╚═════════════════════════╝   11
 * Category
 */
class BoardPuzzleManager {
    private Puzzle puzzle;
    private String[] words;

    BoardPuzzleManager(Puzzle puzzle) {
        this.puzzle = puzzle;
        words = puzzle.getPuzzle().split(" ");
    }

    public ArrayList<String[]> determineLines() {
        int index = 0;
        ArrayList<String[]> lines = new ArrayList<>();
        ArrayList<String> current = new ArrayList<>();

        int currentLength = 0;
        for (String word : words) {

            current.add(word);
            currentLength += word.length();
            if (currentLength + word.length() > 13) {
                lines.add(current.toArray(new String[current.size()]));
                current.clear();
                currentLength = 0;
            }
        }
        if(lines.isEmpty()){
            lines.add(current.toArray(new String[current.size()]));
        }

        return lines;
    }

}