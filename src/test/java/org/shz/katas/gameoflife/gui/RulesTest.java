package org.shz.katas.gameoflife.gui;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class RulesTest {

    private Rules rules;

    @Before
    public void setUp() {
        rules = new Rules();
    }

    @Test
    public void occupied_cell_with_no_neighbour_shall_become_unoccupied() {
        assertThat(rules.apply(Board.occupied, 0), is(false));
    }

    @Test
    public void occupied_cell_with_two_neighbours_shall_remain_occupied() {
        assertThat(rules.apply(Board.occupied, 2), is(true));
    }

    @Test
    public void occupied_cell_with_three_wighbours_shall_remain_occupied() {
        assertThat(rules.apply(Board.occupied, 3), is(true));
    }

    @Test
    public void occupied_cell_with_four_neighbours_shall_become_unoccupied() {
        assertThat(rules.apply(Board.occupied, 4), is(false));
    }

    @Test
    public void unoccupied_cell_with_three_neighbours_shall_become_occupied() {
        assertThat(rules.apply(Board.unoccupied, 3), is(true));
    }

    @Test
    public void unoccupied_cell_with_two_neighbours_shall_remain_unoccupied() {
        assertThat(rules.apply(Board.unoccupied, 2), is(false));
    }
}
