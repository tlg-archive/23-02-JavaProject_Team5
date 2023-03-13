package com.wheel.sys;

import com.wheel.resources.Wedge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class Player {
    public static int numberPlayers = 0;

    private String name;
    private int playerNumber;
    private Double roundBalance;
    private Double gameBalance;

    Collection<Wedge> tokens = new ArrayList<>();

    public Player(String name) {
        this.name = name;

    }
    public Player(){
        numberPlayers++;
        playerNumber = numberPlayers;
    }

    public void spin(){

    }
    public void guess(){

    }
    public void buyVowel(){


    }
    public void solve(){

    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRoundBalance() {
        return roundBalance;
    }

    public Double getGameBalance() {
        return gameBalance;
    }

    public Collection<Wedge> getTokens() {
        return Collections.unmodifiableCollection(tokens);
    }

    public String toString() {
        return String.format("%s: PlayerNumber:%s Name:%s Wins:%s , RoundBalance:%s , GameBalance=%s ",
                getClass(), getPlayerNumber(), getName(), getRoundBalance(), getRoundBalance());
    }
}