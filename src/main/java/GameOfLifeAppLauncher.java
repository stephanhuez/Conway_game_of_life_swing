import org.shz.gameoflife.GameController;
import org.shz.gameoflife.GameOfLifeView;
import org.shz.gameoflife.Universe;

public class GameOfLifeAppLauncher {

    public static void main(String[] args) {
        Universe model = new Universe(20, 20);
        GameOfLifeView view = new GameOfLifeView();
        GameController controller = new GameController(model, view);
        view.setController(controller);
        view.start();
    }

}
