package org.shz.katas.gameoflife.gui;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class BoardTest {

    @Test
    public void new_board_should_initialise_with_provided_height() {
        Board board = new Board(10, 20);

        assertThat(board.getRows(), equalTo(20));
    }

    @Test
    public void new_board_should_initialise_with_provided_width() {
        Board board = new Board(10, 20);

        assertThat(board.getColumns(), equalTo(10));
    }

    @Test
    public void new_board_should_initialise_as_many_cells_as_requested() {
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
    public void toggle_should_set_cell_as_occupied() {
        Board board = new Board(3, 3);

        board.toggle(2, 2);

        assertThat(board.isOccupied(2, 2), equalTo(Board.occupied));
    }

    @Test
    public void toggle_should_set_cell_as_unoccupied() {
        Board board = new Board(3, 3);

        board.toggle(2, 1);
        board.toggle(2, 1);

        assertThat(board.isOccupied(2, 1), equalTo(Board.unoccupied));
    }

    @Test
    public void tick_on_block_shall_remain_a_block() {
        Board board = new Board(2, 2);

        board.toggle(0, 0);
        board.toggle(0, 1);
        board.toggle(1, 0);
        board.toggle(1, 1);

        board.tick();

        assertThat(board.isOccupied(0, 0), equalTo(Board.occupied));
        assertThat(board.isOccupied(0, 1), equalTo(Board.occupied));
        assertThat(board.isOccupied(1, 0), equalTo(Board.occupied));
        assertThat(board.isOccupied(1, 1), equalTo(Board.occupied));
    }

    @Test
    public void tick_on_lonely_cell_shall_yield_empty_board() {
        Board board = new Board(2, 2);
        board.toggle(0, 0);

        board.tick();

        assertThat(board.isOccupied(0, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(0, 1), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(1, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(1, 1), equalTo(Board.unoccupied));
    }
    
    @Test
    public void single_tick_on_glider_shall_yield_expected_occupancy(){
        Board board = new Board (4,4);
        
        board.toggle(0,1);
        board.toggle(1,2);
        board.toggle(2,0);
        board.toggle(2,1);
        board.toggle(2,2);
        
        board.tick();

        assertThat(board.isOccupied(0, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(0, 1), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(0, 2), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(0, 3), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(1, 0), equalTo(Board.occupied));
        assertThat(board.isOccupied(1, 1), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(1, 2), equalTo(Board.occupied));
        assertThat(board.isOccupied(1, 3), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(2, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(2, 1), equalTo(Board.occupied));
        assertThat(board.isOccupied(2, 2), equalTo(Board.occupied));
        assertThat(board.isOccupied(2, 3), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(3, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(3, 1), equalTo(Board.occupied));
        assertThat(board.isOccupied(3, 2), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(3, 3), equalTo(Board.unoccupied));
    }
    
    @Test
    public void double_tick_on_glider_shall_yield_expected_occupancy(){
        Board board = new Board (4,4);
        
        board.toggle(0,1);
        board.toggle(1,2);
        board.toggle(2,0);
        board.toggle(2,1);
        board.toggle(2,2);
        
        board.tick();
        board.tick();

        assertThat(board.isOccupied(0, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(0, 1), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(0, 2), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(0, 3), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(1, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(1, 1), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(1, 2), equalTo(Board.occupied));
        assertThat(board.isOccupied(1, 3), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(2, 0), equalTo(Board.occupied));
        assertThat(board.isOccupied(2, 1), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(2, 2), equalTo(Board.occupied));
        assertThat(board.isOccupied(2, 3), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(3, 0), equalTo(Board.unoccupied));
        assertThat(board.isOccupied(3, 1), equalTo(Board.occupied));
        assertThat(board.isOccupied(3, 2), equalTo(Board.occupied));
        assertThat(board.isOccupied(3, 3), equalTo(Board.unoccupied));
    }
}
