package org.shz.gameoflife;

public class GameOfLifeApp {

    public GameOfLifeApp(int rows, int columns) {
        Universe model = new Universe(rows, columns);
        GameOfLifeView view = new GameOfLifeView();
        GameController controller = new GameController(model, view);
        view.setController(controller);
        view.start();
    }

}
