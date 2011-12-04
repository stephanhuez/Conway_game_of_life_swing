package org.shz.katas.gameoflife.v20111203;

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
	public void liveCellWithTwoNeighboursShallLiveOn() {
		CellState result = rules.nextGeneration(CellState.Alive, 2);
		assertThat(result, equalTo(CellState.Alive));
	}

	@Test
	public void liveCellWithFourNeighboursShallDie() {
		CellState result = rules.nextGeneration(CellState.Alive, 4);
		assertThat(result, equalTo(CellState.Dead));
	}

	@Test
	public void deadCellWithTwoNeighboursRemainsDead() {
		CellState result = rules.nextGeneration(CellState.Dead, 2);
		assertThat(result, equalTo(CellState.Dead));
	}

	@Test
	public void deadCellWithThreeNeighboursShallBecomeAlive() {
		CellState result = rules.nextGeneration(CellState.Dead, 3);
		assertThat(result, equalTo(CellState.Alive));
	}

	@Test
	public void deadCellWithNoNeighbourShallRemainDead() {
		CellState result = rules.nextGeneration(CellState.Dead, 0);
		assertThat(result, equalTo(CellState.Dead));
	}

	@Test
	public void liveCellWithOneNeighbourDies() {
		CellState result = rules.nextGeneration(CellState.Alive, 1);
		assertThat(result, equalTo(CellState.Dead));
	}


}
