package org.shz.katas.gameoflife.gui;

public class Board {

    private int height;
    private int width;
    private boolean[][] cells;

    public Board(int width, int height) {
        this.height = height;
        this.width = width;
        this.cells = new boolean[width][height];
    }

    public void toggle(int column, int row) {
        cells[column][row] = !cells[column][row];
    }

    public boolean isOccupied(int column, int row) {
        return cells[column][row];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return height;
    }

}