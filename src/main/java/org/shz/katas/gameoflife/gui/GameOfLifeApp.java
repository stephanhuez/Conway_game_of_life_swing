package org.shz.katas.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLifeApp extends JFrame {

    private static final long serialVersionUID = 5357372067190037769L;
    public static final String TITLE = "Conway's Game of Life";
    private static final int FIXED_WINDOW_SPACE_HEIGHT = 70;
    private BoardPanel boardPanel;
    private int columns;
    private int rows;

    public GameOfLifeApp(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        layoutComponents();
    }

    public void start() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(calculateWidth(), calculateHeight());
        setVisible(true);
        setResizable(true);
    }

    private int calculateHeight() {
        return boardPanel.getComponentHeight() + FIXED_WINDOW_SPACE_HEIGHT;
    }

    private int calculateWidth() {
        return boardPanel.getComponentWidth();
    }

    private void layoutComponents() {
        setTopLayoutManager();
        addBoardPanel();
        addButtonBar();
    }

    private void setTopLayoutManager() {
        Container contentPane = getContentPane();
        BorderLayout layout = new BorderLayout();
        contentPane.setLayout(layout);
    }

    private void addBoardPanel() {
        boardPanel = new BoardPanel(rows, columns);
        boardPanel.setName("BoardPanel");
        add(boardPanel, BorderLayout.CENTER);
    }

    private void addButtonBar() {
        JPanel bar = newButtonBar();

        bar.add(newNextButton());
        bar.add(newStartButton());
        bar.add(newStopButton());
        bar.add(newResetButton());

        add(bar, BorderLayout.PAGE_END);
    }

    private JPanel newButtonBar() {
        JPanel bar = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(2);
        bar.setLayout(layout);
        return bar;
    }

    private JButton newStartButton() {
        JButton button = new JButton("Start");
        return button;
    }

    private JButton newStopButton() {
        JButton button = new JButton("Stop");
        return button;
    }

    private JButton newNextButton() {
        JButton button = new JButton("Next");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.tick();
            }
        });
        return button;
    }

    private JButton newResetButton() {
        JButton button = new JButton("Reset");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.reset();
            }
        });
        return button;
    }
}
