package org.shz.katas.gameoflife.gui;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void should_initialise_with_provided_height() {
        Board board = new Board(10, 20);
        assertThat(board.getHeight(), equalTo(20));
    }

    @Test
    public void should_initialise_with_provided_width() {
        Board board = new Board(10, 20);
        assertThat(board.getWidth(), equalTo(10));
    }

    @Test
    public void should_initialise_as_many_cells_as_requested() {
        Board board = new Board(3, 3);
        assertThat(board.isOccupied(0, 0), is(false));
        assertThat(board.isOccupied(0, 1), is(false));
        assertThat(board.isOccupied(0, 2), is(false));
        assertThat(board.isOccupied(1, 0), is(false));
        assertThat(board.isOccupied(1, 1), is(false));
        assertThat(board.isOccupied(1, 2), is(false));
        assertThat(board.isOccupied(2, 0), is(false));
        assertThat(board.isOccupied(2, 1), is(false));
        assertThat(board.isOccupied(2, 2), is(false));
    }

    @Test
    public void should_set_cell_as_occupied() {
        Board board = new Board(3, 3);

        board.toggle(2, 2);

        assertThat(board.isOccupied(2, 2), is(true));
    }

    @Test
    public void should_set_cell_as_unoccupied() {
        Board board = new Board(3, 3);
        board.toggle(2, 1);
        board.toggle(2, 1);
        assertThat(board.isOccupied(2, 1), is(false));
    }



}
