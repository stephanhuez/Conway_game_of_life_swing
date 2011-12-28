package org.shz.katas.gameoflife.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private static final long serialVersionUID = 3454143495977773575L;
    private static final int DEFAULT_CELL_HEIGHT = 30;
    private static final int DEFAULT_CELL_WIDTH = 30;
    private static final int DEFAULT_BORDER_SIZE = 10;

    private Board board;
    private int cellHeight;
    private int cellWidth;
    private int boardWidth;
    private int boardHeight;
    private int rows;
    private int columns;

    public BoardPanel(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.board = new Board(columns, rows);
        this.cellWidth = DEFAULT_CELL_WIDTH;
        this.cellHeight = DEFAULT_CELL_HEIGHT;
        this.boardWidth = this.cellWidth * columns;
        this.boardHeight = this.cellHeight * rows;
        registerEvents();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int currentRow = 0, currentCol = 0;
        for (int x = DEFAULT_BORDER_SIZE; x <= boardWidth; x += cellWidth) {
            for (int y = DEFAULT_BORDER_SIZE; y <= boardHeight; y += cellHeight) {
                g.drawRect(x, y, cellWidth, cellHeight);
                if (board.isOccupied(currentCol, currentRow)) {
                    g.fillRect(x, y, cellWidth, cellHeight);
                }
                currentRow++;
            }
            currentRow = 0;
            currentCol++;
        }
    }

    public int getBoardHeight() {
        return board.getHeight();
    }

    public int getBoardWidth() {
        return board.getWidth();
    }

    public int getCellwidth() {
        return this.cellWidth;
    }

    public int getCellHeight() {
        return this.cellHeight;
    }

    public boolean isOccupied(int column, int row) {
        return board.isOccupied(column, row);
    }

    private void registerEvents() {
        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                if (isInGrid(p)) {

                    int row = findRowClicked(p);
                    int col = findColumnClick(p);
                    board.toggle(col, row);
                    repaint();
                }
            }
        };
        addMouseListener(ml);

    }

    private int findRowClicked(Point p) {
        double yInc = (double) (getHeight() - 2 * DEFAULT_BORDER_SIZE) / rows;
        return (int) ((p.y - DEFAULT_BORDER_SIZE) / yInc);
    }

    private int findColumnClick(Point p) {
        double xInc = (double) (getWidth() - 2 * DEFAULT_BORDER_SIZE) / columns;
        return (int) ((p.x - DEFAULT_BORDER_SIZE) / xInc);

    }

    private boolean isInGrid(Point p) {
        Rectangle r = getBounds();
        r.grow(-DEFAULT_BORDER_SIZE, -DEFAULT_BORDER_SIZE);
        return r.contains(p);
    }

    protected Board getBoard() {
        return board;
    }

}
