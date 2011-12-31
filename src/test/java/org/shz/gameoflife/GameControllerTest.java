package org.shz.gameoflife;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GameControllerTest {

    private GameController controller;
    private GameOfLifeApp view;
    private Board universe;

    @Before
    public void setUp() {
        universe = new Board(10, 10);
        view = mock(GameOfLifeApp.class);
        controller = new GameController(universe, view);
    }

    @Test
    public void should_initialise_model_in_app() {
        // Then
        verify(view).setBoard(universe);
    }

    @Test
    public void should_toggle_universe_cell() {
        // When
        controller.toggle(1, 1);

        // Then
        assertThat(universe.isOccupied(1, 1), is(true));
    }

    @Test
    public void should_tick_universe() {
        // Given
        universe.toggle(1, 1);

        // When
        controller.tick();

        // Then
        assertThat(universe.isOccupied(1, 1), is(false));
    }

    @Test
    public void should_reset_universe() {
        // Given
        universe.toggle(1, 1);

        // When
        controller.reset();

        // Then
        assertThat(universe.isOccupied(1, 1), is(false));
    }

    @Test
    public void should_automatically_tick__oscillator_once() throws Exception {
        // Given
        universe.toggle(3, 3);
        universe.toggle(3, 4);
        universe.toggle(3, 5);

        // When
        controller.start();
        Thread.sleep(600);

        // Then
        assertThat(universe.isOccupied(2, 4), is(true));
        assertThat(universe.isOccupied(3, 4), is(true));
        assertThat(universe.isOccupied(4, 4), is(true));
    }

    @Test
    public void should_automatically_tick_oscillator_twice() throws Exception {
        // Given
        universe.toggle(3, 3);
        universe.toggle(3, 4);
        universe.toggle(3, 5);

        // When
        controller.start();
        Thread.sleep(1200);

        // Then
        assertThat(universe.isOccupied(3, 3), is(true));
        assertThat(universe.isOccupied(3, 4), is(true));
        assertThat(universe.isOccupied(3, 5), is(true));
    }

    @Test
    public void should_stop_automatic_ticking_of_oscillator() throws Exception {
        // Given
        universe.toggle(3, 3);
        universe.toggle(3, 4);
        universe.toggle(3, 5);

        // When
        controller.start();
        Thread.sleep(600);
        controller.stop();
        Thread.sleep(600);

        // Then
        assertThat(universe.isOccupied(2, 4), is(true));
        assertThat(universe.isOccupied(3, 4), is(true));
        assertThat(universe.isOccupied(4, 4), is(true));
    }

    @Test
    public void tick_should_tell_view_to_refresh() {
        // When
        controller.tick();

        // Then
        verify(view).refresh();
    }

    @Test
    public void reset_should_tell_view_to_refresh() {
        // When
        controller.reset();

        // Then
        verify(view).refresh();
    }

    @Test
    public void automatic_ticking_should_refresh_view_after_ticking() throws Exception {
        // When
        controller.start();
        Thread.sleep(1200);
        controller.stop();

        // Then
        verify(view, times(2)).refresh();
    }
}
