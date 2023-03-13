package com.wheel.sys;

import com.apps.util.Prompter;
import com.wheel.resources.Consonant;
import com.wheel.resources.Letter;
import com.wheel.resources.Puzzle;
import com.wheel.resources.wedge.Wedge;
import com.wheel.resources.wedge.WedgeGood;
import com.wheel.resources.wedge.WedgeMoney;
import static com.wheel.resources.wedge.WedgeBad.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Host {
    private Puzzle puzzle;

    //PUT THIS SOMEWHERE ELSE
    private Prompter prompter = new Prompter(new Scanner(System.in));


    public void promptPlayer(){
        // Tells player what to do. Look at Prompter API
    }
    public void announceWinner(){

    }
    public String getInput(){
        String input = null;

        return input;
    }

    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        //prompt number of players
        int nPlayers = Integer.parseInt(prompter.prompt("Please enter the number of players: ","[2-5]", "Must be between 2 and 5."));
        for(int i = 0; i < nPlayers; i++){
            //prompt for input
            String name = prompter.prompt("Please enter name of player " + i + 1);
            players.add(new Player(name));
        }
        return Collections.unmodifiableList(players);
    }

    public int getRounds() {
        //prompt for rounds
        int rounds = 0; //input result here
        return rounds;
    }

    public boolean winOnTurn(Player player) {
        boolean result = false;
        Wedge wedge = player.spin();
        if(wedge instanceof WedgeMoney || wedge instanceof WedgeGood){
            result = processGuess(wedge, player);
        } else {
            if(wedge == BANKRUPT){
                player.goBankrupt();
            }
        }
        return result;
    }

    private boolean processGuess(Wedge wedge, Player player){
        //prompt for guess
        String guess = null;
        if(wedge == null){
            guess = prompter.prompt("Guess a vowel: ", "[aeiouAEIOU]", "You paid for a vowel, so gimme one.");
        } else {
            guess = prompter.prompt("Guess a consonant: ", "[^aeiouAEIOU]", "You can only provide a consonant.");
        }
        boolean solvedPuzzle = false;
        //Check if guess is correct
        //boolean correct = puzzle.checkLetter(Consonant.valueOf(guess)); //put real code here

        if(puzzle.checkLetter(Consonant.valueOf(guess))){
            if(wedge == null){
                //this is the case where they buy a vowel
            } else if (wedge instanceof WedgeGood){
                player.gainToken(wedge);
            } else {
                int num = 1;// Get number of times it appears in the puzzle
                player.gainMoney(num * wedge.value());
            }
            //The player can spin, buy a vowel, or try to solve
            String choice = prompter.prompt("What would you like to do?\n" +
                    "Spin the [W]heel\n" +
                    "Buy a [V]owel\n" +
                    "Solve the [P]uzzle", "/[wvp]/i", "Please select W, V, or P.");

            if("W".equalsIgnoreCase(choice)){
                winOnTurn(player);
            } else if ("V".equalsIgnoreCase(choice)){
                processGuess(null, player);
            } else if ("P".equalsIgnoreCase(choice)){
                String solutionGuess = prompter.prompt("Input your guess:");
                solvedPuzzle = puzzle.checkSolution(solutionGuess);
            }
        }
        return solvedPuzzle;
    }

    public void generatePuzzle() {
        //Temporary - replace with puzzleFactory that reads puzzles from a file.
        puzzle = new Puzzle("CHITTY CHITTY BANG BANG", "Title");
    }
}