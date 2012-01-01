package org.shz.gameoflife;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.shz.gameoflife.Universe;

public class UniverseTest {

    @Test
    public void new_universe_should_initialise_with_provided_height() {
        // Given
        GameBoard universe = newUniverse(20, 10);

        // Then
        assertThat(universe.getRows(), equalTo(20));
    }

    @Test
    public void new_universe_should_initialise_with_provided_width() {
        // Given
        GameBoard universe = newUniverse(20, 10);

        // Then
        assertThat(universe.getColumns(), equalTo(10));
    }

    @Test
    public void new_universe_should_initialise_as_many_cells_as_requested() {
        // Given
        GameBoard universe = newUniverse(3, 3);

        // Then
        assertThat(universe.isOccupied(0, 0), is(false));
        assertThat(universe.isOccupied(0, 1), is(false));
        assertThat(universe.isOccupied(0, 2), is(false));
        assertThat(universe.isOccupied(1, 0), is(false));
        assertThat(universe.isOccupied(1, 1), is(false));
        assertThat(universe.isOccupied(1, 2), is(false));
        assertThat(universe.isOccupied(2, 0), is(false));
        assertThat(universe.isOccupied(2, 1), is(false));
        assertThat(universe.isOccupied(2, 2), is(false));
    }

    @Test
    public void toggle_should_set_cell_as_occupied() {
        // Given
        Universe universe = newUniverse(3, 3);

        // When
        universe.toggle(2, 2);

        // Then
        assertThat(universe.isOccupied(2, 2), equalTo(Universe.occupied));
    }

    @Test
    public void toggle_should_set_cell_as_unoccupied() {
        // Given
        Universe universe = newUniverse(3, 3);

        // When
        universe.toggle(2, 1);
        universe.toggle(2, 1);

        // Then
        assertThat(universe.isOccupied(2, 1), equalTo(Universe.unoccupied));
    }

    @Test
    public void tick_on_block_shall_remain_a_block() {
        // Given
        Universe universe = newUniverse(2, 2);
        universe.toggle(0, 0);
        universe.toggle(0, 1);
        universe.toggle(1, 0);
        universe.toggle(1, 1);

        // When
        universe.tick();

        // Then
        assertThat(universe.isOccupied(0, 0), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(0, 1), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(1, 0), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(1, 1), equalTo(Universe.occupied));
    }

    @Test
    public void tick_on_lonely_cell_shall_yield_empty_universe() {
        // Given
        Universe universe = newUniverse(2, 2);
        universe.toggle(0, 0);

        // When
        universe.tick();

        // Then
        assertThat(universe.isOccupied(0, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(0, 1), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(1, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(1, 1), equalTo(Universe.unoccupied));
    }

    @Test
    public void single_tick_on_glider_shall_yield_expected_occupancy() {
        // Given
        Universe universe = newUniverse(4, 4);
        universe.toggle(0, 1);
        universe.toggle(1, 2);
        universe.toggle(2, 0);
        universe.toggle(2, 1);
        universe.toggle(2, 2);

        // When
        universe.tick();

        // Then
        assertThat(universe.isOccupied(0, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(0, 1), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(0, 2), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(0, 3), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(1, 0), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(1, 1), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(1, 2), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(1, 3), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(2, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(2, 1), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(2, 2), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(2, 3), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(3, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(3, 1), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(3, 2), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(3, 3), equalTo(Universe.unoccupied));
    }

    @Test
    public void double_tick_on_glider_shall_yield_expected_occupancy() {
        // Given
        Universe universe = newUniverse(4, 4);
        universe.toggle(0, 1);
        universe.toggle(1, 2);
        universe.toggle(2, 0);
        universe.toggle(2, 1);
        universe.toggle(2, 2);

        // When
        universe.tick();
        universe.tick();

        // Then
        assertThat(universe.isOccupied(0, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(0, 1), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(0, 2), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(0, 3), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(1, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(1, 1), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(1, 2), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(1, 3), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(2, 0), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(2, 1), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(2, 2), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(2, 3), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(3, 0), equalTo(Universe.unoccupied));
        assertThat(universe.isOccupied(3, 1), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(3, 2), equalTo(Universe.occupied));
        assertThat(universe.isOccupied(3, 3), equalTo(Universe.unoccupied));
    }

    @Test
    public void as_string_should_convert_fully_occupied_universe() {
        // Given
        Universe universe = newUniverse(2, 2);
        universe.toggle(0, 0);
        universe.toggle(1, 0);
        universe.toggle(0, 1);
        universe.toggle(1, 1);

        // When
        String universeAsString = universe.asString();

        // Then
        assertThat(universeAsString, equalTo("xx\nxx\n"));
    }

    @Test
    public void as_string_should_convert_empty_universe() {
        // Given
        Universe universe = newUniverse(3, 2);

        // When
        String universeAsString = universe.asString();

        // Then
        assertThat(universeAsString, equalTo("__\n__\n__\n"));
    }

    @Test
    public void as_string_should_convert_to_block() {
        // Given
        Universe universe = newUniverse(4, 4);
        universe.toggle(1, 1);
        universe.toggle(1, 2);
        universe.toggle(2, 1);
        universe.toggle(2, 2);

        // When
        String universeAsString = universe.asString();

        // Then
        assertThat(universeAsString, equalTo("____\n_xx_\n_xx_\n____\n"));
    }

    @Test
    public void should_reset_cells() {
        // Given
        Universe universe = newUniverse(2, 2);
        universe.toggle(1, 1);

        // When
        universe.reset();

        // Then
        assertThat(universe.isOccupied(1, 1), is(false));
    }

    private Universe newUniverse(int rows, int columns) {
        return new Universe(rows, columns);
    }
}
