package org.shz.katas.gameoflife.gui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.netbeans.jemmy.operators.*;

public class BoardPanelInteractionTest {

    private BoardPanel panel;
    private ComponentOperator operator;

    @Before
    public void setUp() {
        JFrame frame = new JFrame("GUI Test");
        panel = new BoardPanel(10, 10);
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
    public void should_make_cell_9_9_occupied() throws Exception {
        operator.pressMouse(298, 300);
        Thread.sleep(100);
        assertThat(panel.isOccupied(9, 9), is(true));
    }
    
    @Test
    public void should_make_cell_9_9_unoccupied_again() throws Exception {
        operator.pressMouse(298, 300);
        operator.pressMouse(298, 300);
        Thread.sleep(100);
        assertThat(panel.isOccupied(9, 9), is(false));
    }


}
