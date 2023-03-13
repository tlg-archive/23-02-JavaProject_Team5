package com.wheel.resources.wedge;

public enum WedgeBad implements WedgeSpecial{
    BANKRUPT(0),LOSE_TURN(0);

    private final int value;

    private WedgeBad(int value){
        this.value = value;
    }

    public int value(){
        return value;
    }
}