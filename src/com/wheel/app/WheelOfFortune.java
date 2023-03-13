package com.wheel.app;

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
        int rounds = host.getRounds();
        int round = 0;
        boolean gameOver = false;

        while (round < rounds) {
            host.generatePuzzle();
            boolean roundOver = false;
            board.revealBoard();

            int index = 0;
            while (!roundOver) {

                roundOver = host.winOnTurn(players.get(index));

                index++;
                if (index == players.size()) index = 0;
            }

        }
    }
}