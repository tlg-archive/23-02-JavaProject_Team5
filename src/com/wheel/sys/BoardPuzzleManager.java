package com.wheel.sys;

import com.wheel.resources.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class manages the puzzle on the board
 * 1234567890123456789012345678901
 * ╔═════════════════════════╗   1
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+║   2
 * ╔═╝|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|╚═╗ 3
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+-+-+║ 4
 * ║|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|║ 5
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+-+-+║ 6
 * ║|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|║ 7
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+-+-+║ 8
 * ╚═╗|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|▒|╔═╝ 9
 * ║+-+-+-+-+-+-+-+-+-+-+-+-+║   10
 * ╚═════════════════════════╝   11
 * Category
 */
class BoardPuzzleManager {
    private Puzzle puzzle;
    private String[] words;

    BoardPuzzleManager(Puzzle puzzle) {
        this.puzzle = puzzle;
        words = puzzle.getPuzzle().split(" ");
    }

    public List<String[]> determineLines() {
        List<String[]> lines = new ArrayList<>();
        List<String> current = new ArrayList<>();

        int currentLength = 0;
        int[] limits = {11, 13, 13, 11};
        for (String word : words) {

            if (currentLength + word.length() > limits[lines.size()]) {
                lines.add(current.toArray(new String[current.size()]));
                current.clear();
                currentLength = 0;
            }
            current.add(word);
            currentLength += word.length();
        }
        if (lines.isEmpty() || !current.isEmpty()) {
            lines.add(current.toArray(new String[current.size()]));
        }

        return lines;
    }


}