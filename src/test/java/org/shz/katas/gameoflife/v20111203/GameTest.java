package org.shz.katas.gameoflife.v20111203;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

public class GameTest {

	@Test
	public void blockShallRemainBlock() {
		String seed = "____$" +
				      "_xx_$" +
				      "_xx_$" +
				      "____$";
		Game game = new Game(seed);
		assertThat(game.nextGeneration(), equalTo(seed));
	}
	
	@Test
	public void lonelyCentreCellIn3By3UniverseShallDieAfterFirstGeneration(){
		String seed = "___$" +
				      "_x_$" +
				      "___$";
		String expectedUniverse = "___$" +
								  "___$" +
								  "___$";
		Game game = new Game(seed);
		assertThat(expectedUniverse,equalTo(game.nextGeneration()));
	}
	
}
