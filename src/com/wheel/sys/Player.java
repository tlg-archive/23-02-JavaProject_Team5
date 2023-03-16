package com.wheel.sys;

import com.wheel.resources.wedge.Wedge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Player {
    public static int numberPlayers = 0;

    private String name;
    private int roundBalance;
    private int gameBalance;

    Collection<Wedge> tokens = new ArrayList<>();

    public Player(String name) {
        this.name = name;

    }


    public void gainToken(Wedge wedge) {
        tokens.add(wedge);
    }

    public void gainMoney(int money) {
        roundBalance += money;
    }


    public String getName() {
        return name;
    }


    public int getRoundBalance() {
        return roundBalance;
    }

    public int getGameBalance() {
        return gameBalance;
    }

    public void addToGameBalance(int gameBalance) {
        this.gameBalance += gameBalance;
    }

    public Collection<Wedge> getTokens() {
        return Collections.unmodifiableCollection(tokens);
    }

    public String toString() {
        return String.format("%s: Name:%s, RoundBalance:%s , GameBalance=%s ",
                getClass(), getName(), getRoundBalance(), getGameBalance());
    }

    public void goBankrupt() {
        roundBalance = 0;
    }

    public void deductMoney(int vowelCost) {
        this.roundBalance -= vowelCost;
    }
}