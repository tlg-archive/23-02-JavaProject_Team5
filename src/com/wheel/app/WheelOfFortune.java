package com.wheel.app;

import com.apps.util.Console;
import com.wheel.sys.Board;
import com.wheel.sys.Player;
import com.wheel.sys.Wheel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class WheelOfFortune {
    private final Board board = Board.getInstance();
    private final Host host = new Host();
    private List<Player> players;

    public void play() {
        board.displayBanner();
        host.showWelcomeMessage();
        players = host.getPlayers();
        board.setPlayers(players);
        host.setBoard(board);
        int rounds = host.getRounds();
        int round = 1;
        boolean gameOver = false;
        Console.clear();

        while (round <= rounds) {

            board.displayRound(round);
            Console.clear();
            host.generatePuzzle();
            board.setCurrentPuzzle(host.getPuzzle());
            boolean roundOver = false;
            board.updateRound(round);
            host.clearCorrectGuesses();
            board.revealBoard();
            Wheel wheel = new Wheel();

            int playerIndex = 0;

            playerIndex = IntStream.range(0, players.size())
                    .boxed()
                    .max(Comparator.comparing(i -> players.get(i).getGameBalance()))
                    .orElse(-1);

            while (!roundOver) {
                //System.out.println("We are in round: " + (round + 1));
                board.markCurrentPlayer(playerIndex);
                Console.clear();
                board.update();
                roundOver = host.winOnTurn(players.get(playerIndex), wheel);
                if(roundOver){
                    board.showRoundWinner(players.get(playerIndex));
                    Console.pause(2000);
                }


                playerIndex++;
                if (playerIndex == players.size()) playerIndex = 0;
            }
            for (var player : players) {
                player.goBankrupt();
            }
            round++;


        }
        board.showGameWinner(players);
    }
}