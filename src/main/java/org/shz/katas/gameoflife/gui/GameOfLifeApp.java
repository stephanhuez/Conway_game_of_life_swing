package org.shz.katas.gameoflife.gui;

import javax.swing.JFrame;

public class GameOfLifeApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game of life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoardPanel boardPanel = new BoardPanel(10, 10);
        frame.add(boardPanel);
        frame.setSize(320, 340);
        frame.setVisible(true);
    }

}
