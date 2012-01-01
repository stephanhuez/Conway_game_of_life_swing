package org.shz.gameoflife;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.awt.Graphics;
import java.awt.Graphics2D;

import org.junit.Before;
import org.junit.Test;
import org.shz.gameoflife.Universe;
import org.shz.gameoflife.BoardPanel;

public class BoardPanelTest {

    private GameController controller;
    private Universe universe;

    @Before
    public void setUp() {
        controller = mock(GameController.class);
    }

    @Test
    public void should_know_size_from_model() {
        // Given
        BoardPanel panel = newPanel(20, 10);

        // Then
        assertThat(panel.getColumns(), equalTo(10));
        assertThat(panel.getRows(), equalTo(20));
    }

    @Test
    public void should_draw_rectangles_corresponding_to_initial_universe() {
        // Given
        BoardPanel panel = newPanel(20, 10);
        Graphics graphics = newMockGraphics();

        // When
        panel.paintComponent(graphics);

        // Then
        verify(graphics, times(200)).drawRect(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void should_draw_rectangles_of_proper_size() {
        // Given
        BoardPanel panel = newPanel(10, 10);
        Graphics graphics = newMockGraphics();

        // When
        panel.paintComponent(graphics);

        // Then
        verify(graphics, times(100)).drawRect(anyInt(), anyInt(), eq(panel.getCellwidth()), eq(panel.getCellHeight()));
    }

    @Test
    public void should_draw_universe_inside_borders() {
        // Given
        BoardPanel panel = newPanel(3, 3);
        Graphics2D graphics = newMockGraphics();

        // When
        panel.paintComponent(graphics);

        // Then
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
        // Given
        BoardPanel panel = newPanel(3, 3);
        Graphics graphics = newMockGraphics();
        universe.toggle(2, 2);

        // When
        panel.paintComponent(graphics);

        // Then
        verify(graphics).fillRect(eq(70), eq(70), anyInt(), anyInt());
    }

    @Test
    public void should_draw_unoccupied_cell_as_empty() {
        // Given
        BoardPanel panel = newPanel(3, 3);
        Graphics graphics = newMockGraphics();
        universe.toggle(2, 2);
        universe.toggle(2, 2);

        // When
        panel.paintComponent(graphics);

        // Then
        verify(graphics, never()).fillRect(eq(70), eq(70), anyInt(), anyInt());
    }

    @Test
    public void should_calculate_panel_height() {
        // Given
        BoardPanel panel = newPanel(3, 4);

        // Then
        assertThat(panel.getComponentHeight(), equalTo(110));
    }

    @Test
    public void should_calculate_panel_width() {
        // Given
        BoardPanel panel = newPanel(3, 4);

        // Then
        assertThat(panel.getComponentWidth(), equalTo(140));
    }

    private BoardPanel newPanel(int rows, int columns) {
        universe = new Universe(rows, columns);
        BoardPanel panel = new BoardPanel(controller);
        panel.setModel(universe);
        return panel;
    }

    private Graphics2D newMockGraphics() {
        Graphics2D graphics = mock(Graphics2D.class);
        when(graphics.create()).thenReturn(graphics);
        return graphics;
    }

}
