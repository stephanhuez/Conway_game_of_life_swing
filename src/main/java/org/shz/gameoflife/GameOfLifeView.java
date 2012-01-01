package org.shz.gameoflife;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLifeView extends JFrame {

    private static final long serialVersionUID = 5357372067190037769L;
    public static final String TITLE = "Conway's Game of Life";
    private static final int FIXED_WINDOW_SPACE_HEIGHT = 70;
    private BoardPanel boardPanel;
    private GameBoard board;
    private GameController controller;

    public void start() {
        layoutComponents();
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
        boardPanel = new BoardPanel(controller);
        boardPanel.setModel(board);
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.start();
            }
        });
        return button;
    }

    private JButton newStopButton() {
        JButton button = new JButton("Stop");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.stop();
            }
        });
        return button;
    }

    private JButton newNextButton() {
        JButton button = new JButton("Next");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.tick();
            }
        });
        return button;
    }

    private JButton newResetButton() {
        JButton button = new JButton("Reset");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.reset();
            }
        });
        return button;
    }

    public void setModel(GameBoard board) {
        this.board = board;
    }

    public void refresh() {
        repaint();
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }
}
