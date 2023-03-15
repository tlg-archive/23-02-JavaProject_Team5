package com.wheel.resources;

public enum Consonant implements Letter{
    B('B'),
    C('C'),
    D('D'),
    F('F'),
    G('G'),
    H('H'),
    J('J'),
    K('K'),
    L('L'),
    M('M'),
    N('N'),
    P('P'),
    Q('Q'),
    R('R'),
    S('S'),
    T('T'),
    V('V'),
    W('W'),
    X('X'),
    Y('Y'),
    Z('Z');

    private final char c;
    Consonant (char c) {
        this.c = c;
    }

    @Override
    public char getChar() {
        return 0;
    }
}