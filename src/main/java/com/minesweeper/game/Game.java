package com.minesweeper.game;

import com.minesweeper.exceptions.KaboomException;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

@RedisHash("game")
@Schema(name = "Game", description = "Game model")
public class Game implements Serializable {

    @Id
    @Schema(description = "Unique identifier of the game")
    private String name;

    @Schema(description = "Quantity of board's rows")
    private int rows;

    @Schema(description = "Quantity of board's columns")
    private int columns;

    @Schema(description = "Quantity of board's mines")
    private int mines;

    @Schema(description = "Game's status")
    private String status;

    @Schema(description = "Game's board")
    private Cell[][] grid;

    @Schema(description = "Click counter")
    private int clicks;

    @Schema(description = "Username of the game")
    @NotBlank(message = "username is mandatory")
    private String username;

    @Schema(description = "Date of game creation")
    private Calendar createdAt;

    @Schema(description = "Date the game started")
    private Calendar startedAt;

    @Schema(description = "Time spent in the game")
    private Duration timeSpent;

    public void buildBoard() {
        int cellsNumber = columns * rows;
        Cell[] cells = new Cell[cellsNumber];

        for (int i = 0; i < mines; i++) {
            int randomMineIndex = new Random(cellsNumber).nextInt();
            if (!cells[randomMineIndex].isMine()) {
                cells[randomMineIndex].setMine(true);
            }
        }

        grid = new Cell[rows][];
        for (int row = 0; row < grid.length; row++) {
            grid[row] = Arrays.copyOfRange(cells, (columns * row), columns * (row + 1));
        }
    }

    public void clickCell(int column, int row) throws KaboomException {
        if (alreadyClickedCell(column, row)) {
            throw new UnsupportedOperationException("cell already clicked");
        }

        revealCell(column, row);

    }

    private void revealCell(int column, int row) throws KaboomException {
        grid[column][row].setClicked(true);
        if (grid[column][row].isMine()) {
            status = Status.LOST.getValue();
            throw new KaboomException("Boom!");
        }

        // TODO recursive call to check for adjacent cells
    }

    public void flagOrMarkCell(int column, int row, String clickType) {
        // TODO flag or mark with question mark the clicked cell
    }

    private boolean alreadyClickedCell(int column, int row) {
        return grid[column][row].isClicked();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Calendar startedAt) {
        this.startedAt = startedAt;
    }

    public Duration getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Duration timeSpent) {
        this.timeSpent = timeSpent;
    }


}
