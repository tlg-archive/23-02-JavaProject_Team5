package com.wheel.app;

import com.apps.util.Console;
import com.wheel.sys.Board;
import com.wheel.sys.Host;
import com.wheel.sys.Player;
import com.wheel.sys.Wheel;

import java.util.List;

public class WheelOfFortune {
    Board board = Board.getInstance();
    Host host = new Host();
    Wheel wheel = new Wheel();
    List<Player> players;

    public void play() {
        board.displayBanner();
        players = host.getPlayers();
        board.setPlayers(players);
        host.setBoard(board);
        int rounds = host.getRounds();
        int round = 1;
        boolean gameOver = false;
        Console.clear();

        while (round <= rounds) {
            host.generatePuzzle();
            board.displayBoard(host.getPuzzle());
            boolean roundOver = false;
            board.updateRound(round);
            board.revealBoard();
            Wheel wheel = new Wheel();

            int playerIndex = 0;
            while (!roundOver) {
                //System.out.println("We are in round: " + (round + 1));
                board.markCurrentPlayer(playerIndex);
                Console.clear();
                board.showSolution();
                roundOver = host.winOnTurn(players.get(playerIndex), wheel);


                playerIndex++;
                if (playerIndex == players.size()) playerIndex = 0;
            }
            for (var player : players) {
                player.goBankrupt();
            }
            round++;

        }
    }
}