package org.shz.gameoflife;

public interface GameBoard {

    public abstract boolean isOccupied(int row, int column);

    public abstract int getColumns();

    public abstract int getRows();

}