package com.wheel.sys;

import com.apps.util.Prompter;
import com.wheel.resources.Consonant;
import com.wheel.resources.Puzzle;
import com.wheel.resources.Vowel;
import com.wheel.resources.wedge.Wedge;
import com.wheel.resources.wedge.WedgeGood;
import com.wheel.resources.wedge.WedgeMoney;
import static com.wheel.resources.wedge.WedgeBad.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Host {
    private Puzzle puzzle;
    private Wheel wheel;

    //PUT THIS SOMEWHERE ELSE
    private Prompter prompter = new Prompter(new Scanner(System.in));

    public void assignPrompter(Prompter prompter){
        this.prompter = prompter;
    }


    public void announceWinner(){

    }


    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        //prompt number of players
        int nPlayers = Integer.parseInt(prompter.prompt("Please enter the number of players: \n","[2-5]", "Must be between 2 and 5."));
        for(int i = 0; i < nPlayers; i++){
            //prompt for input
            String name = prompter.prompt("Please enter name of player " + (i + 1) +" \n");
            players.add(new Player(name));
        }
        return Collections.unmodifiableList(players);
    }

    public int getRounds() {
        //prompt for rounds
        return Integer.parseInt(prompter.prompt("How many rounds would you like to play?\n",
                "[3-6]", "Enter a number between 3 and 6\n"));
    }

    public boolean winOnTurn(Player player, Wheel wheel) {
        boolean result = false;
        this.wheel = wheel;
        Wedge wedge = player.spin(wheel);
        System.out.println("You landed on " + wedge);
        if(wedge instanceof WedgeMoney || wedge instanceof WedgeGood){
            result = processGuess(wedge, player);
        } else {
            if(wedge == BANKRUPT){
                player.goBankrupt();
                System.out.println("Oh no! You lost all of your money so far for this round!");
            }
        }
        return result;
    }

    boolean processGuess(Wedge wedge, Player player){
        //prompt for guess
        String guess = null;
        System.out.println(wedge);
        if(wedge == null){
            guess = prompter.prompt("Guess a vowel: \n", "[aeiouAEIOU]", "You paid for a vowel, so gimme one.\n");
            System.out.println(guess);
        } else {
            guess = prompter.prompt("Guess a consonant: ", "^(?!.*[aeiouAEIOU])[a-zA-Z]$", "You can only provide a consonant.");
        }
        boolean solvedPuzzle = false;
        //Check if guess is correct
        boolean correct = (wedge == null)? puzzle.checkLetter(Vowel.valueOf(guess.toUpperCase())) :
                puzzle.checkLetter(Consonant.valueOf(guess.toUpperCase()));

        if(correct){
            if(wedge == null){
                //this is the case where they buy a vowel
            } else if (wedge instanceof WedgeGood){
                player.gainToken(wedge);
            } else {
                int num = 1;// Get number of times it appears in the puzzle
                player.gainMoney(num * wedge.value());
            }
            System.out.println("You have $" + player.getRoundBalance());
            //The player can spin, buy a vowel, or try to solve
            String choice = prompter.prompt("What would you like to do?\n" +
                    "Spin the [W]heel\n" +
                    "Buy a [V]owel\n" +
                    "Solve the [P]uzzle\n", "[wvpWVP]", "Please select W, V, or P.\n");
            System.out.println(choice);

            if("W".equalsIgnoreCase(choice)){
                winOnTurn(player, wheel);
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
//        puzzle = new Puzzle("CHITTY CHITTY BANG BANG", "Title", 0);


        puzzle = Puzzle.PuzzleFactory.getRandomPuzzle();

    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
}