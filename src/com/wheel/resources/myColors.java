package com.wheel.resources;

public enum myColors {
    FGOLD("\u001B[38;5;220m"),
    FGREN("\u001B[32m"), //38;5;22m"
    FBLUE("\u001B[96m"),
    BGREN("\u001B[48;5;22m"),
    ESCAP("\u001B[0m");

    private final String value;

    private myColors(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}