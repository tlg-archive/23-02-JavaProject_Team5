package com.wheel.sys;

import com.wheel.resources.Puzzle;

import java.util.ArrayList;
import java.util.List;

public class WrongGuesses {

    private List<Character> wrongGuesses;

    public WrongGuesses() {
       this.wrongGuesses = new ArrayList<>();
    }

    public static void addGuess(char guess) {
        this.wrongGuesses.add(guess);

    }

//    public boolean hasGuessed(char guess) {
//        return this.wrongGuesses.contains(guess);
//    }

    public String getWrongGuessesAsString() {
        StringBuilder guesses = new StringBuilder();
        for (char guess : this.wrongGuesses) {
            guesses.append(guess).append(" ");
        }
        return guesses.toString();
    }
}