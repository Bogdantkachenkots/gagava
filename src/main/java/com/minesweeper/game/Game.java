package com.minesweeper.game;

import java.time.Duration;
import java.util.Date;

public class Game {
    public String name;
    public int rows;
    public int columns;
    public int mines;
    public String status;
    public Cell[] grid;
    public int clicks;
    public String username;
    public Date createdAt;
    public Date startedAt;
    public Duration timeSpent;
}
