import javax.swing.SwingUtilities;

import org.shz.gameoflife.GameOfLifeApp;

public class GameOfLifeAppLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameOfLifeApp(20, 20);
            }
        });
    }

}
