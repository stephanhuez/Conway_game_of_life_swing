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
		Cell liveCell = new LiveCell();
		Cell result = rules.nextGeneration(liveCell, 2);
		assertThat(result, instanceOf(LiveCell.class));
	}

	@Test
	public void liveCellWithFourNeighboursShallDie() {
		Cell liveCell = new LiveCell();
		Cell result = rules.nextGeneration(liveCell, 4);
		assertThat(result, instanceOf(DeadCell.class));
	}

	@Test
	public void deadCellWithTwoNeighboursRemainsDead() {
		Cell deadCell = new DeadCell();
		Cell result = rules.nextGeneration(deadCell, 2);
		assertThat(result, instanceOf(DeadCell.class));
	}

	@Test
	public void deadCellWithThreeNeighboursShallBecomeAlive() {
		Cell deadCell = new DeadCell();
		Cell result = rules.nextGeneration(deadCell, 3);
		assertThat(result, instanceOf(LiveCell.class));
	}

	@Test
	public void deadCellWithNoNeighbourShallRemainDead() {
		Cell deadCell = new DeadCell();
		Cell result = rules.nextGeneration(deadCell, 0);
		assertThat(result, instanceOf(DeadCell.class));
	}

	@Test
	public void liveCellWithOneNeighbourDies() {
		Cell liveCell = new LiveCell();
		Cell result = rules.nextGeneration(liveCell, 1);
		assertThat(result, instanceOf(DeadCell.class));
	}


}
