package org.shz.gameoflife;

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

    private Universe universe;
    private int cellHeight = DEFAULT_CELL_HEIGHT;
    private int cellWidth = DEFAULT_CELL_WIDTH;
    private GameController controller;


    public BoardPanel(GameController controller) {
        this.controller = controller;
        registerEvents();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = DEFAULT_BORDER_SIZE;
        for (int currentRow = 0; currentRow < universe.getRows(); currentRow++) {
            int x = DEFAULT_BORDER_SIZE;
            for (int currentCol = 0; currentCol < universe.getColumns(); currentCol++) {
                drawCell(g, y, currentRow, x, currentCol);
                x += cellWidth;
            }
            y += cellHeight;
        }
    }

    private void drawCell(Graphics g, int y, int currentRow, int x, int currentCol) {
        g.drawRect(x, y, cellWidth, cellHeight);
        if (universe.isOccupied(currentRow, currentCol)) {
            g.fillRect(x, y, cellWidth, cellHeight);
        }
    }

    public int getRows() {
        return universe.getRows();
    }

    public int getColumns() {
        return universe.getColumns();
    }

    public int getCellwidth() {
        return this.cellWidth;
    }

    public int getCellHeight() {
        return this.cellHeight;
    }

    private void registerEvents() {

        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                if (isInGrid(p)) {
                    toggle(p);
                }
            }
        };
        addMouseListener(ml);
    }

    private void toggle(Point p) {
        int row = findRowClicked(p);
        int col = findColumnClick(p);
        controller.toggle(row, col);
    }

    private int findRowClicked(Point p) {
        return (int) ((p.y - DEFAULT_BORDER_SIZE) / cellHeight);
    }

    private int findColumnClick(Point p) {
        return (int) ((p.x - DEFAULT_BORDER_SIZE) / cellWidth);

    }

    private boolean isInGrid(Point p) {
        Rectangle r = new Rectangle(DEFAULT_BORDER_SIZE, DEFAULT_BORDER_SIZE, getGridHeight(), getGridWidth());
        return r.contains(p);
    }

    public int getComponentHeight() {
        return getGridHeight() + 2 * DEFAULT_BORDER_SIZE;
    }

    public int getComponentWidth() {
        return getGridWidth() + 2 * DEFAULT_BORDER_SIZE;
    }

    private int getGridHeight() {
        return universe.getRows() * DEFAULT_CELL_HEIGHT;
    }

    private int getGridWidth() {
        return universe.getColumns() * DEFAULT_CELL_WIDTH;
    }

    public void setModel(Universe universe) {
        this.universe = universe;
    }

}
