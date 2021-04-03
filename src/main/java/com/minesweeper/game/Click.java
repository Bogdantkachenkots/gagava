package com.minesweeper.game;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(name = "Click", description = "Click model")
public class Click {

    @Schema(description = "Column position")
    private int column;

    @Schema(description = "Row position")
    private int row;

    @Schema(description = "Click type", allowableValues = { "click", "flag", "mark" })
    @NotBlank
    private String clickType;

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getClickType() {
        return clickType;
    }

    public void setClickType(String clickType) {
        this.clickType = clickType;
    }
}
