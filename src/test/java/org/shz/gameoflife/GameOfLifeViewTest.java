package org.shz.gameoflife;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.awt.Component;

import org.junit.BeforeClass;
import org.junit.Test;
import org.netbeans.jemmy.ComponentChooser;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.shz.gameoflife.BoardPanel;
import org.shz.gameoflife.GameOfLifeView;

public class GameOfLifeViewTest {

    private static GameOfLifeView view;
    private static JFrameOperator frameOperator;
    private static GameController controller;

    @BeforeClass
    public static void setUp() {
        controller = mock(GameController.class);
        view = new GameOfLifeView();
        view.setModel(new Universe(10, 11));
        view.setController(controller);
        view.start();

        frameOperator = new JFrameOperator(GameOfLifeView.TITLE);
    }

    @Test
    public void should_startup_as_visible() {
        // Then
        assertThat(frameOperator, notNullValue());
        assertThat(frameOperator.isVisible(), is(true));
    }

    @Test
    public void window_height_should_be_based_on_board_height() {
        // Given
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();

        // Then
        assertThat(view.getHeight(), equalTo(panel.getComponentHeight() + 70));
    }

    @Test
    public void window_width_should_be_based_on_board_width() {
        // Given
        JComponentOperator boardPanelOperator = new JComponentOperator(frameOperator, new BoardPanelChooser());
        BoardPanel panel = (BoardPanel) boardPanelOperator.getSource();

        // Then
        assertThat(view.getWidth(), equalTo(panel.getComponentWidth()));
    }

    @Test
    public void clicking_on_next_button_should_tick_the_board() throws Exception {
        // Given
        JButtonOperator button = new JButtonOperator(frameOperator, "Next");

        // When
        button.push();
        Thread.sleep(100);

        // Then
        verify(controller).tick();
    }

    @Test
    public void clicking_on_reset_button_should_reset_board() {
        // Given
        JButtonOperator button = new JButtonOperator(frameOperator, "Reset");

        // When
        button.push();

        // Then
        verify(controller).reset();
    }

    @Test
    public void start_button_should_start_timed_tick_of_the_board() throws Exception {
        // Given
        JButtonOperator button = new JButtonOperator(frameOperator, "Start");

        // When
        button.push();

        // Then
        verify(controller).start();
    }

    @Test
    public void stop_button_should_stop_timed_tick_of_the_board() throws Exception {
        // Given
        JButtonOperator stop = new JButtonOperator(frameOperator, "Stop");

        // When
        stop.push();

        // Then
        verify(controller).stop();
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
