package org.shz.katas.gameoflife.gui;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.junit.Test;

public class BoardPanelTest {

    @Test
    public void should_be_a_jpanel() {
        BoardPanel panel = new BoardPanel(10, 10);
        assertThat(panel, instanceOf(JPanel.class));
    }

    @Test
    public void should_initialise_model() {
        BoardPanel panel = new BoardPanel(10, 20);

        assertThat(panel.getBoardWidth(), equalTo(10));
        assertThat(panel.getBoardHeight(), equalTo(20));
    }

    @Test
    public void should_initialise_cell_width_and_height() {
        BoardPanel panel = new BoardPanel(10, 10);

        assertThat(panel.getCellHeight(), equalTo(30));
        assertThat(panel.getCellwidth(), equalTo(30));

    }

    @Test
    public void should_draw_rectangles_corresponding_to_initial_board() {
        BoardPanel panel = new BoardPanel(10, 20);
        Graphics graphics = newMockGraphics();

        panel.paintComponent(graphics);

        verify(graphics, times(200)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void should_draw_rectangles_of_default_size() {
        BoardPanel panel = new BoardPanel(10, 10);
        Graphics graphics = newMockGraphics();

        panel.paintComponent(graphics);

        verify(graphics, times(100)).drawRect(anyInt(), anyInt(), eq(panel.getCellwidth()), eq(panel.getCellHeight()));

    }

    @Test
    public void should_draw_rectangles_corresponding_to_board_layout_border_inclusive() {
        BoardPanel panel = new BoardPanel(3, 3);
        Graphics2D graphics = newMockGraphics();

        panel.paintComponent(graphics);

        verify(graphics).drawRect(eq(10), eq(10), anyInt(), anyInt());
        verify(graphics).drawRect(eq(10), eq(40), anyInt(), anyInt());
        verify(graphics).drawRect(eq(10), eq(70), anyInt(), anyInt());
        verify(graphics).drawRect(eq(40), eq(10), anyInt(), anyInt());
        verify(graphics).drawRect(eq(40), eq(40), anyInt(), anyInt());
        verify(graphics).drawRect(eq(40), eq(70), anyInt(), anyInt());
        verify(graphics).drawRect(eq(70), eq(10), anyInt(), anyInt());
        verify(graphics).drawRect(eq(70), eq(40), anyInt(), anyInt());
        verify(graphics).drawRect(eq(70), eq(70), anyInt(), anyInt());
    }

    @Test
    public void should_draw_occupied_cell_as_filled() {
        TestBoardPanel panel = new TestBoardPanel(3, 3);
        Graphics graphics = newMockGraphics();

        panel.toggle(2, 2);
        panel.paintComponent(graphics);

        verify(graphics).fillRect(eq(70), eq(70), anyInt(), anyInt());
    }

    @Test
    public void should_draw_unoccupied_cell_as_empty() {
        TestBoardPanel panel = new TestBoardPanel(3, 3);
        Graphics graphics = newMockGraphics();

        panel.toggle(2, 2);
        panel.toggle(2, 2);
        panel.paintComponent(graphics);

        verify(graphics, never()).fillRect(eq(70), eq(70), anyInt(), anyInt());
    }

    private Graphics2D newMockGraphics() {
        Graphics2D graphics = mock(Graphics2D.class);
        when(graphics.create()).thenReturn(graphics);
        return graphics;
    }

    private class TestBoardPanel extends BoardPanel {

        private static final long serialVersionUID = -6671916506461607344L;

        public TestBoardPanel(int columns, int rows) {
            super(columns, rows);
        }

        public void toggle(int column, int row) {
            getBoard().toggle(column, row);
        }
    }

}
