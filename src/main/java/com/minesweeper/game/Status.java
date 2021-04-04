package com.minesweeper.game;

public enum Status {
    NEW("new"),
    WON("won"),
    IN_PROGRESS("in_progress"),
    LOST("lost");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
