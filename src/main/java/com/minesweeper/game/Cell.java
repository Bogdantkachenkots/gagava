package com.minesweeper.game;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Cell", description = "Cell model")
public class Cell {
    @Schema(description = "Flag to determinate if this cell has a mine on it")
    private boolean mine;

    @Schema(description = "Flag to determinate if this cell has been clicked")
    private boolean clicked;

    @Schema(description = "Flag to determinate if this cell has been flagged")
    private boolean flagged;

    @Schema(description = "Flag to determinate if this cell has been mark with question mark")
    private boolean marked;

    @Schema(description = "A string representation of the cell coordinates")
    private String coordinates;

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
