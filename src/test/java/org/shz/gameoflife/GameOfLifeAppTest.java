package org.shz.gameoflife;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.awt.Component;

import org.junit.Before;
import org.junit.Test;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.shz.gameoflife.BoardPanel;
import org.shz.gameoflife.GameOfLifeApp;

public class GameOfLifeAppTest {

    private GameOfLifeApp app;
    private JFrameOperator frameOperator;

    @Before
    public void setUp() {
        app = new GameOfLifeApp(10, 11);

        app.start();

        frameOperator = new JFrameOperator(GameOfLifeApp.TITLE);
    }

    @Test
    public void should_startup_as_visible() {
        assertThat(frameOperator, notNullValue());
        assertThat(frameOperator.isVisible(), is(true));
    }

    @Test
    public void should_initialise_board() {
        JComponentOperator boardPanel = new JComponentOperator(frameOperator, new BoardPanelChooser());

        assertThat(boardPanel, notNullValue());
    }

    @Test
    public void should_initialise_board_panel_with_provided_size() {
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());

        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();
        assertThat(panel.getRows(), equalTo(10));
        assertThat(panel.getColumns(), equalTo(11));
    }

    @Test
    public void window_height_should_be_based_on_board_height() {
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();

        assertThat(app.getHeight(), equalTo(panel.getComponentHeight() + 70));
    }

    @Test
    public void window_width_should_be_based_on_board_width() {
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();

        assertThat(app.getWidth(), equalTo(panel.getComponentWidth()));
    }

    @Test
    public void should_have_next_button() {
        JButtonOperator button = new JButtonOperator(frameOperator, "Next");

        assertThat(button, notNullValue());
    }

    @Test
    public void clicking_on_next_button_should_tick_the_board() {
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();
        JButtonOperator button = new JButtonOperator(frameOperator, "Next");

        panel.toggle(1, 1);
        button.push();

        assertThat(panel.isOccupied(1, 1), is(false));
    }

    @Test
    public void should_have_reset_button() {
        JButtonOperator button = new JButtonOperator(frameOperator, "Reset");

        assertThat(button, notNullValue());
    }

    @Test
    public void clicking_on_reset_button_should_reset_board() {
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();
        JButtonOperator button = new JButtonOperator(frameOperator, "Reset");

        panel.toggle(1, 1);
        button.push();

        assertThat(panel.isOccupied(1, 1), is(false));
    }

    @Test
    public void should_have_start_button() {
        JButtonOperator button = new JButtonOperator(frameOperator, "Start");

        assertThat(button, notNullValue());
    }

    @Test
    public void start_button_should_start_timed_tick_of_the_board() throws Exception {
        JButtonOperator button = new JButtonOperator(frameOperator, "Start");
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();

        panel.toggle(4, 4);
        panel.toggle(4, 5);
        panel.toggle(4, 6);

        button.push();
        Thread.sleep(1500);

        assertThat(panel.isOccupied(3, 5), is(true));
        assertThat(panel.isOccupied(4, 5), is(true));
        assertThat(panel.isOccupied(5, 5), is(true));
    }

    @Test
    public void stop_button_should_stop_timed_tick_of_the_board() throws Exception {
        JButtonOperator start = new JButtonOperator(frameOperator, "Start");
        JButtonOperator stop = new JButtonOperator(frameOperator, "Stop");
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();

        panel.toggle(8, 4);
        panel.toggle(8, 5);
        panel.toggle(8, 6);

        start.push();
        Thread.sleep(1500);
        stop.push();
        Thread.sleep(500);

        assertThat(panel.isOccupied(7, 5), is(true));
        assertThat(panel.isOccupied(8, 5), is(true));
        assertThat(panel.isOccupied(9, 5), is(true));
    }

    @Test
    public void should_have_stop_button() {
        JButtonOperator button = new JButtonOperator(frameOperator, "Stop");

        assertThat(button, notNullValue());
    }

    private static class BoardPanelChooser implements ComponentChooser {
        @Override
        public boolean checkComponent(Component comp) {
            if (null != comp.getName())
                return comp.getName().equals("BoardPanel");
            return false;
        }

        @Override
        public String getDescription() {
            return null;
        }
    }
}
