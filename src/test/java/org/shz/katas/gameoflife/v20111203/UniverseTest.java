package org.shz.katas.gameoflife.v20111203;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class UniverseTest {

	@Test
	public void shouldInitializeTheUniverse(){
		String seed = "x___$" +
				      "_x__$" +
				      "__x_$" +
				      "___x$";
		Universe universe = new Universe(seed);
		CellState[][] expectedUniverse = new CellState[][]{{CellState.Alive,CellState.Dead,CellState.Dead,CellState.Dead},
				                                 {CellState.Dead,CellState.Alive,CellState.Dead,CellState.Dead},
				                                 {CellState.Dead,CellState.Dead,CellState.Alive,CellState.Dead},
				                                 {CellState.Dead,CellState.Dead,CellState.Dead,CellState.Alive}};
		assertThat(universe.asArray(),equalTo(expectedUniverse));
	}

}
