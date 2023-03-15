package com.wheel.resources.wedge;

public enum WedgeGood implements WedgeSpecial {
    FREE_SPIN(0);

    private final int value;

    private WedgeGood(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}