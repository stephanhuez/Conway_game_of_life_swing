package org.shz.gameoflife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameController {
    private static final int TIMER_SPEED = 500;

    private Board universe;
    private GameOfLifeApp view;
    private Timer timer;

    public GameController(Board universe, GameOfLifeApp view) {
        this.universe = universe;
        this.view = view;
        view.setBoard(universe);
    }

    public void toggle(int row, int column) {
        universe.toggle(row, column);
    }

    public void tick() {
        universe.tick();
        view.refresh();
    }

    public void reset() {
        universe.reset();
        view.refresh();
    }

    public void start() {
        ActionListener act = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                universe.tick();
                view.refresh();
            }
        };
        timer = new Timer(TIMER_SPEED, act);
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

}
