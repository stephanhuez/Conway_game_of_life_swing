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
    private int columns;
    private int rows;

    public BoardPanel(int columns, int rows) {
        this.rows = rows;
        this.columns = columns;
        this.board = new Board(columns, rows);
        this.cellWidth = DEFAULT_CELL_WIDTH;
        this.cellHeight = DEFAULT_CELL_HEIGHT;
        registerEvents();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = DEFAULT_BORDER_SIZE;
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            int x = DEFAULT_BORDER_SIZE;
            for (int currentCol = 0; currentCol < columns; currentCol++) {
                drawCell(g, y, currentRow, x, currentCol);
                x += cellWidth;
            }
            y += cellHeight;
        }
    }

    private void drawCell(Graphics g, int y, int currentRow, int x, int currentCol) {
        g.drawRect(x, y, cellWidth, cellHeight);
        if (board.isOccupied(currentRow, currentCol)) {
            g.fillRect(x, y, cellWidth, cellHeight);
        }
    }

    public int getBoardHeight() {
        return board.getRows();
    }

    public int getBoardWidth() {
        return board.getColumns();
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
                    toggle(p);
                    repaint();
                }
            }

            private void toggle(Point p) {
                int row = findRowClicked(p);
                int col = findColumnClick(p);
                board.toggle(row, col);
            }
        };
        addMouseListener(ml);

    }

    private int findRowClicked(Point p) {
        return (int) ((p.y - DEFAULT_BORDER_SIZE) / cellHeight);
    }

    private int findColumnClick(Point p) {
        return (int) ((p.x - DEFAULT_BORDER_SIZE) / cellWidth);

    }

    private boolean isInGrid(Point p) {
        Rectangle r = getBounds();
        r.grow(-DEFAULT_BORDER_SIZE, -DEFAULT_BORDER_SIZE);
        return r.contains(p);
    }

    protected Board getBoard() {
        return board;
    }

    public void tick() {
        board.tick();
        repaint();
    }

}
