package org.shz.katas.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameOfLifeApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game of life");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final BoardPanel boardPanel = new BoardPanel(10, 10);
        frame.add(boardPanel, BorderLayout.CENTER);
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.tick();
            }

        });
        frame.add(nextButton, BorderLayout.PAGE_END);
        frame.setSize(320, 370);
        frame.setVisible(true);
    }

}
