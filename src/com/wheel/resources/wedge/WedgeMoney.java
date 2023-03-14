package com.wheel.resources.wedge;

public enum WedgeMoney implements Wedge{
    $500(500),
    $550(550),
    $600(600),
    $650(650),
    $700(700),
    $750(750),
    $800(800),
    $850(850),
    $900(900),
    TOP_VALUE(2500);
    private final int value;
    private WedgeMoney(int value){
        this.value = value;
    }

    public int value() {
        return value;
    }
}