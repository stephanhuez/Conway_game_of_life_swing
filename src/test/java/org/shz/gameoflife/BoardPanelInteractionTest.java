package org.shz.gameoflife;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.netbeans.jemmy.operators.*;
import org.shz.gameoflife.BoardPanel;

public class BoardPanelInteractionTest {

    private BoardPanel panel;
    private ComponentOperator operator;

    @Before
    public void setUp() {
        JFrame frame = new JFrame("GUI Test");
        Board board = new Board(10, 10);
        panel = new BoardPanel(board);
        frame.add(panel);
        frame.setSize(320, 340);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        operator = new ComponentOperator(panel);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void should_make_cell_0_0_occupied() throws Exception {
        operator.pressMouse(25, 21);
        Thread.sleep(100);
        assertThat(panel.isOccupied(0, 0), is(true));
    }

    @Test
    public void should_make_cell_6_6_occupied() throws Exception {
        operator.pressMouse(213, 211);
        Thread.sleep(100);
        assertThat(panel.isOccupied(6, 6), is(true));
    }

    @Test
    public void should_make_cell_3_7_occupied() throws Exception {
        operator.pressMouse(231, 125);
        Thread.sleep(100);
        assertThat(panel.isOccupied(3, 7), is(true));
    }

    @Test
    public void should_make_cell_2_4_occupied_when_clicked_on_top_left() throws Exception {
        operator.pressMouse(130, 70);
        Thread.sleep(100);
        assertThat(panel.isOccupied(2, 4), is(true));
    }

    @Test
    public void should_make_cell_2_4_occupied_when_clicked_on_top_right() throws Exception {
        operator.pressMouse(159, 70);
        Thread.sleep(100);
        assertThat(panel.isOccupied(2, 4), is(true));
    }

    @Test
    public void should_make_cell_2_4_occupied_when_clicked_on_bottom_left() throws Exception {
        operator.pressMouse(130, 99);
        Thread.sleep(100);
        assertThat(panel.isOccupied(2, 4), is(true));
    }

    @Test
    public void should_make_cell_2_4_occupied_when_clicked_on_bottom_right() throws Exception {
        operator.pressMouse(159, 99);
        Thread.sleep(100);
        assertThat(panel.isOccupied(2, 4), is(true));
    }

    @Test
    public void should_make_cell_9_9_occupied() throws Exception {
        operator.pressMouse(300, 300);
        Thread.sleep(100);
        assertThat(panel.isOccupied(9, 9), is(true));
    }

    @Test
    public void should_make_cell_0_9_occupied() throws Exception {
        operator.pressMouse(295, 25);
        Thread.sleep(100);
        assertThat(panel.isOccupied(0, 9), is(true));
    }

    @Test
    public void should_make_cell_9_0_occupied() throws Exception {
        operator.pressMouse(23, 303);
        Thread.sleep(100);
        assertThat(panel.isOccupied(9, 0), is(true));
    }

    @Test
    public void should_make_cell_9_9_unoccupied_again() throws Exception {
        operator.pressMouse(298, 300);
        operator.pressMouse(298, 300);
        Thread.sleep(100);
        assertThat(panel.isOccupied(9, 9), is(false));
    }

}
