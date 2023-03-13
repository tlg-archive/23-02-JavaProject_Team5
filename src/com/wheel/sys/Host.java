package com.wheel.sys;

import com.wheel.resources.wedge.Wedge;
import com.wheel.resources.wedge.WedgeGood;
import com.wheel.resources.wedge.WedgeMoney;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Host {


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
        int nPlayers = 3;
        for(int i = 0; i < nPlayers; i++){
            //prompt for input
            String name = "name";//input goes in here
            players.add(new Player(name));
        }
        return Collections.unmodifiableList(players);
    }

    public int getRounds() {
        //prompt for rounds
        int rounds = 0; //input result here
        return rounds;
    }

    public void doTurn(Player player) {
        Wedge wedge = player.spin();
        if(wedge instanceof WedgeMoney || wedge instanceof WedgeGood){
            processGuess(wedge, player);
        }
    }

    private void processGuess(Wedge wedge, Player player){
        //prompt for guess

        //Check if guess is correct
        boolean correct = true; //put real code here

        if(correct){
            if(wedge instanceof WedgeGood){
                player.gainToken(wedge);
            } else {
                int num = 1;// Get number of times it appears in the puzzle
                player.gainMoney(num * wedge.value());
            }
        }
    }
}