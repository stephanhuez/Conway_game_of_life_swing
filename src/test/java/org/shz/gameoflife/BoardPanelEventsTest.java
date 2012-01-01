package org.shz.gameoflife;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import org.netbeans.jemmy.operators.*;
import org.shz.gameoflife.BoardPanel;

public class BoardPanelEventsTest {

    private BoardPanel panel;
    private ComponentOperator operator;
    private GameController controller;

    @Before
    public void setUp() {
        Universe universe = new Universe(10, 10);
        controller = mock(GameController.class);
        panel = new BoardPanel(controller);
        panel.setModel(universe);
        operator = new ComponentOperator(panel);
    }

    @Test
    public void should_toggle_cell_0_0() throws Exception {
        // When
        operator.pressMouse(25, 21);
        waitForClick();

        // Then
        verify(controller).toggle(0, 0);
    }

    @Test
    public void should_toggle_cell_6_6() throws Exception {
        // When
        operator.pressMouse(213, 211);
        waitForClick();

        // Then
        verify(controller).toggle(6, 6);
    }

    @Test
    public void should_toggle_cell_3_7() throws Exception {
        // When
        operator.pressMouse(231, 125);
        waitForClick();

        // Then
        verify(controller).toggle(3, 7);
    }

    @Test
    public void should_toggle_cell_2_4_when_clicked_on_top_left() throws Exception {
        // When
        operator.pressMouse(130, 70);
        waitForClick();

        // Then
        verify(controller).toggle(2, 4);
    }

    @Test
    public void should_toggle_cell_2_4_when_clicked_on_top_right() throws Exception {
        // When
        operator.pressMouse(159, 70);
        waitForClick();

        // Then
        verify(controller).toggle(2, 4);
    }

    @Test
    public void should_toggle_cell_2_4_when_clicked_on_bottom_left() throws Exception {
        // When
        operator.pressMouse(130, 99);
        waitForClick();

        // Then
        verify(controller).toggle(2, 4);
    }

    @Test
    public void should_toggle_cell_2_4_when_clicked_on_bottom_right() throws Exception {
        // When
        operator.pressMouse(159, 99);
        waitForClick();

        // Then
        verify(controller).toggle(2, 4);
    }

    @Test
    public void should_toggle_cell_9_9() throws Exception {
        // When
        operator.pressMouse(300, 300);
        waitForClick();

        // Then
        verify(controller).toggle(9, 9);
    }

    @Test
    public void should_toggle_cell_0_9() throws Exception {
        // When
        operator.pressMouse(295, 25);
        waitForClick();

        // Then
        verify(controller).toggle(0, 9);
    }

    @Test
    public void should_toggle_cell_9_0() throws Exception {
        // When
        operator.pressMouse(23, 303);
        waitForClick();

        // Then
        verify(controller).toggle(9, 0);
    }

    private void waitForClick() throws InterruptedException {
        Thread.sleep(20);
    }

}
