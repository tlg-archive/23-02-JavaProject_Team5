package com.wheel.resources.wedge;

public enum WedgeMoney implements Wedge{
    US_500(500),
    US_550(550),
    US_600(600),
    US_650(650),
    US_700(700),
    US_750(750),
    US_800(800),
    US_850(850),
    US_900(900),
    TOP_VALUE(2500);
    private final int value;
    private WedgeMoney(int value){
        this.value = value;
    }

    public int value() {
        return value;
    }
}