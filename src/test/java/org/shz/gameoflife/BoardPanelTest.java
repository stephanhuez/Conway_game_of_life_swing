package org.shz.gameoflife;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.junit.Test;
import org.shz.gameoflife.Board;
import org.shz.gameoflife.BoardPanel;

public class BoardPanelTest {

    @Test
    public void should_know_size_from_model() {
        Board universe = new Board(20, 10);
        BoardPanel panel = new BoardPanel(universe);

        assertThat(panel.getColumns(), equalTo(10));
        assertThat(panel.getRows(), equalTo(20));
    }

    @Test
    public void should_draw_rectangles_corresponding_to_initial_universe() {
        Board universe = new Board(20, 10);
        BoardPanel panel = new BoardPanel(universe);
        Graphics graphics = newMockGraphics();

        panel.paintComponent(graphics);

        verify(graphics, times(200)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void should_draw_rectangles_of_proper_size() {
        Board universe = new Board(10, 10);
        BoardPanel panel = new BoardPanel(universe);
        Graphics graphics = newMockGraphics();

        panel.paintComponent(graphics);

        verify(graphics, times(100)).drawRect(anyInt(), anyInt(), eq(panel.getCellwidth()), eq(panel.getCellHeight()));

    }

    @Test
    public void should_draw_universe_inside_borders() {
        Board universe = new Board(3, 3);
        BoardPanel panel = new BoardPanel(universe);
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
        Board universe = new Board(3, 3);
        BoardPanel panel = new BoardPanel(universe);
        Graphics graphics = newMockGraphics();

        panel.toggle(2, 2);
        panel.paintComponent(graphics);

        verify(graphics).fillRect(eq(70), eq(70), anyInt(), anyInt());
    }

    @Test
    public void should_draw_unoccupied_cell_as_empty() {
        Board universe = new Board(3, 3);
        BoardPanel panel = new BoardPanel(universe);
        Graphics graphics = newMockGraphics();

        panel.toggle(2, 2);
        panel.toggle(2, 2);
        panel.paintComponent(graphics);

        verify(graphics, never()).fillRect(eq(70), eq(70), anyInt(), anyInt());
    }

    @Test
    public void should_calculate_panel_height() {
        Board universe = new Board(3, 4);
        BoardPanel panel = new BoardPanel(universe);

        assertThat(panel.getComponentHeight(), equalTo(110));
    }

    @Test
    public void should_calculate_panel_width() {
        Board universe = new Board(3, 4);
        BoardPanel panel = new BoardPanel(universe);

        assertThat(panel.getComponentWidth(), equalTo(140));
    }

    private Graphics2D newMockGraphics() {
        Graphics2D graphics = mock(Graphics2D.class);
        when(graphics.create()).thenReturn(graphics);
        return graphics;
    }

}
