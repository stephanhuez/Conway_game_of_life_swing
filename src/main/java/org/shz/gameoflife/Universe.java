package org.shz.gameoflife;

public class Universe {

    public static final boolean occupied = true;
    public static final boolean unoccupied = false;
    private int rows;
    private int columns;
    private boolean[][] cells;
    private Rules rules;

    public Universe(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new boolean[rows][columns];
        this.rules = new Rules();
    }

    public void toggle(int row, int column) {
        cells[row][column] = !cells[row][column];
    }

    public boolean isOccupied(int row, int column) {
        return cells[row][column];
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public void tick() {
        boolean[][] nextState = new boolean[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int nbrNeighbours = countNeighbours(row, col);
                nextState[row][col] = rules.apply(cells[row][col], nbrNeighbours);
            }
        }
        cells = nextState;
    }

    protected int countNeighbours(int row, int column) {
        int count = 0;
        if (notRightCell(column)) {
            count += countAsNeighbour(eastCell(row, column));
        }
        if (notRightCell(column) && notBottomCell(row)) {
            count += countAsNeighbour(southEastCell(row, column));
        }
        if (notBottomCell(row)) {
            count += countAsNeighbour(southCell(row, column));
        }
        if (notLeftCell(column) && notBottomCell(row)) {
            count += countAsNeighbour(southWestCell(row, column));
        }
        if (notLeftCell(column)) {
            count += countAsNeighbour(westCell(row, column));
        }
        if (notTopCell(row) && notLeftCell(column)) {
            count += countAsNeighbour(northWestCell(row, column));
        }
        if (notTopCell(row)) {
            count += countAsNeighbour(northCell(row, column));
        }
        if (notRightCell(column) && notTopCell(row)) {
            count += countAsNeighbour(northEastCell(row, column));
        }
        return count;
    }

    private int countAsNeighbour(boolean occupied) {
        return occupied ? 1 : 0;
    }

    private boolean notLeftCell(int column) {
        return column > 0;
    }

    private boolean notRightCell(int column) {
        return column < columns - 1;
    }

    private boolean notTopCell(int row) {
        return row > 0;
    }

    private boolean notBottomCell(int row) {
        return row < rows - 1;
    }

    private boolean northEastCell(int row, int column) {
        return cells[row - 1][column + 1];
    }

    private boolean northCell(int row, int column) {
        return cells[row - 1][column];
    }

    private boolean northWestCell(int row, int column) {
        return cells[row - 1][column - 1];
    }

    private boolean southWestCell(int row, int column) {
        return cells[row + 1][column - 1];
    }

    private boolean westCell(int row, int column) {
        return cells[row][column - 1];
    }

    private boolean southEastCell(int row, int column) {
        return cells[row + 1][column + 1];
    }

    private boolean eastCell(int row, int column) {
        return cells[row][column + 1];
    }

    private boolean southCell(int row, int column) {
        return cells[row + 1][column];
    }

    public String asString() {
        String result = new String();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                result += occupancyToString(cells[row][column]);
            }
            result += "\n";
        }
        return result;
    }

    private String occupancyToString(boolean occupied) {
        if (occupied) {
            return "x";
        }
        return "_";
    }

    public void reset() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                cells[row][col] = unoccupied;
            }
        }
    }

}