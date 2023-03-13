package com.wheel.app;

import com.wheel.sys.Board;
import com.wheel.sys.Host;
import com.wheel.sys.Player;
import com.wheel.sys.Wheel;

import java.util.ArrayList;
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
        boolean gameOver = false;

        while(!gameOver) {
            board.revealBoard();
            for (int i = 0; i < players.size(); i++){
                host.doTurn(players.get(i));

                if(i == players.size()) i = 0;
            }

        }
    }
}