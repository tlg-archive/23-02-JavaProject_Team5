package com.wheel.sys;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.wheel.resources.Puzzle;
import com.wheel.resources.wedge.Wedge;
import com.wheel.resources.wedge.WedgeGood;
import com.wheel.resources.wedge.WedgeMoney;

import static com.wheel.resources.wedge.WedgeBad.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Host {
    private Puzzle puzzle;
    private Wheel wheel;
    private List<Player> players;
    private int currentPlayerIndex;
    private StringBuilder consonantRegex = new StringBuilder();

    //PUT THIS SOMEWHERE ELSE
    private Prompter prompter = new Prompter(new Scanner(System.in));
    private Board board;


    public void assignPrompter(Prompter prompter) {
        this.prompter = prompter;
    }


    public void announceWinner() {

    }


    public List<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        //prompt number of players
        int nPlayers = Integer.parseInt(prompter.prompt("Please enter the number of players [2-3]: ", "[2-3]", " \nMust be between 2 and 3."));
        for (int i = 0; i < nPlayers; i++) {
            //prompt for input
            String name = prompter.prompt("Please enter name of player " + (i + 1) + ": ", "^.{0,9}$", "\nNames are limited to 9 characters.");
            if (name.length() == 0) {
                name = "Player" + (i + 1);
            }
            players.add(new Player(name));

        }

        return Collections.unmodifiableList(players);
    }

    public int getRounds() {
        //prompt for rounds
        return Integer.parseInt(prompter.prompt("How many rounds [3-6] would you like to play? ",
                "[3-6]", "\nEnter a number between 3 and 6. "));
    }

    public boolean winOnTurn(Player player, Wheel wheel) {
        boolean result = false;
        this.wheel = wheel;

//        System.out.println("It's " + players. + "'s turn!");
        prompter.prompt(player.getName() + ", press enter to spin the wheel.");
        Wedge wedge = wheel.getRandomWedge();
        Console.clear();
        board.showSolution();
        System.out.println(player.getName() + ", you landed on " + wedge);
        if (wedge instanceof WedgeMoney || wedge instanceof WedgeGood) {
            result = processGuess(wedge, player);
        } else {
            if (wedge == BANKRUPT) {
                player.goBankrupt();
                Console.clear();
                board.showSolution();
                System.out.println("Oh no!" + player.getName() + " lost all of their money so far for this round!");
            }
            Console.pause(2000L);
        }
        return result;
    }

    private String validConsonantRegex(){
        return "^(?!.*[aeiouAEIOU" + consonantRegex + "])[a-zA-Z]$";
    }

    private void updateCorrectGuesses(String correctGuess){
        consonantRegex.append(correctGuess.toUpperCase());
        consonantRegex.append(correctGuess.toLowerCase());
    }

    public void clearCorrectGuesses(){
        consonantRegex = new StringBuilder();
    }

    boolean processGuess(Wedge wedge, Player player) {
        //prompt for guess
        String guess;
        int vowelCost = 250;
        if (wedge == null) {
            guess = prompter.prompt(player.getName() + ", guess a vowel: ", "[aeiouAEIOU]",
                    "\nYou paid for a vowel, so gimme one! ").toUpperCase();
//            System.out.println(guess);

        } else {
            guess = prompter.prompt(player.getName() + ", guess a consonant: ", validConsonantRegex(),
                    "Provide a consonant that isn't on the board.\n").toUpperCase();
        }

        Console.clear();
        board.showSolution();
        boolean solvedPuzzle = false;
        //Check if guess is correct
        int numTimesInPuzzle = puzzle.checkLetter(guess.toUpperCase());
        if (numTimesInPuzzle > 0) {
            board.recordCorrectGuess(guess);
            updateCorrectGuesses(guess);
            Console.clear();
            board.showSolution();
            if (wedge == null) {
                //this is the case where they buy a vowel
            } else if (wedge instanceof WedgeGood) {
                player.gainToken(wedge);
            } else {
                player.gainMoney(numTimesInPuzzle * wedge.value());
            }
            Console.clear();
            board.showSolution();
            //The player can spin, buy a vowel, or try to solve

            StringBuilder prompt = new StringBuilder();
            StringBuilder regex = new StringBuilder();
            StringBuilder errorMessage = new StringBuilder();
            prompt.append(player.getName() + ", what would you like to do?\nSpin the [W]heel\n");
            regex.append("[wpWP");
            errorMessage.append("\nPlease select W");
            if (player.getRoundBalance() > vowelCost) {
                prompt.append("Buy a [V]owel\n");
                regex.append("vV");
                errorMessage.append(", V,");
            }
            prompt.append("Solve the [P]uzzle: ");
            regex.append("]");

            errorMessage.append(" or P: ");
            errorMessage.append(" or p");//************************Check on this!


            String choice = prompter.prompt(prompt.toString(), regex.toString(), errorMessage.toString());

            Console.clear();
            board.showSolution();

            if ("W".equalsIgnoreCase(choice)) {
                solvedPuzzle = winOnTurn(player, wheel);
            } else if ("V".equalsIgnoreCase(choice)) {
                player.deductMoney(vowelCost);
                solvedPuzzle = processGuess(null, player);
            } else if ("P".equalsIgnoreCase(choice)) {
                String solutionGuess = prompter.prompt(player.getName() + ", Input your guess: ").toUpperCase();
                solvedPuzzle = puzzle.checkSolution(solutionGuess);
                if (solvedPuzzle) {
                    Console.clear();
                    board.recordCorrectGuess(solutionGuess);
                    board.showSolution();
                    player.addToGameBalance(player.getRoundBalance());
                    System.out.println(player.getName() + " solved the puzzle!");
                    Console.pause(2000L);
                    Console.clear();

                }
            }
        }


        return solvedPuzzle;
    }

    public void generatePuzzle() {
        //Temporary - replace with puzzleFactory that reads puzzles from a file.
        puzzle = new Puzzle("CHITTY CHITTY BANG BANG AEIOU", "Title", 0);


//        puzzle = Puzzle.PuzzleFactory.getRandomPuzzle();

    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void showWelcomeMessage() {
        List<String> welcomeMessage = new ArrayList<>();
        try {
            welcomeMessage = Files.readAllLines(Path.of("textFiles/welcomeMessage.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(var line : welcomeMessage){
            System.out.println(line);
        }
    }
}