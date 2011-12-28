package org.shz.katas.gameoflife.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.shz.katas.gameoflife.model.CellState;
import org.shz.katas.gameoflife.model.Universe;

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
	
	@Test
	public void shouldInitializeTheUniverseAsEmpty(){
		String seed = "_____$" +
				      "_____$" +
				      "_____$";
		
		Universe universe = new Universe(seed);
		CellState[][] expectedUniverse = new CellState[][]{{CellState.Dead,CellState.Dead,CellState.Dead,CellState.Dead,CellState.Dead},
				                                           {CellState.Dead,CellState.Dead,CellState.Dead,CellState.Dead,CellState.Dead},
				                                           {CellState.Dead,CellState.Dead,CellState.Dead,CellState.Dead,CellState.Dead}};
		assertThat(universe.asArray(),equalTo(expectedUniverse));
	}

	@Test
	public void shouldInitializeTheUniverseAsFull(){
		String seed = "xxxxx$" +
				      "xxxxx$" +
				      "xxxxx$";
		
		Universe universe = new Universe(seed);
		CellState[][] expectedUniverse = new CellState[][]{{CellState.Alive,CellState.Alive,CellState.Alive,CellState.Alive,CellState.Alive},
				                                           {CellState.Alive,CellState.Alive,CellState.Alive,CellState.Alive,CellState.Alive},
				                                           {CellState.Alive,CellState.Alive,CellState.Alive,CellState.Alive,CellState.Alive}};
		assertThat(universe.asArray(),equalTo(expectedUniverse));
	}
	
	@Test
	public void shouldConvertUnivseToString(){
		String seed = "______$" +
			          "__xxx_$" +
			          "_xxx__$" +
			          "______$";
		Universe universe = new Universe(seed);
		String expectedString = "___x__$" +
		          				"_x__x_$" +
		          				"_x__x_$" +
		          				"__x___$";
		universe.nextGeneration();
		assertThat(universe.asString(),equalTo(expectedString));
	}
	
	@Test
	public void shouldGoToNextGeneration(){
		String seed = "______$" +
					  "__xxx_$" +
					  "_xxx__$" +
					  "______$";
		Universe universe = new Universe(seed);

		String expectedUniverseSeed = "___x__$" +
							    	  "_x__x_$" +
							    	  "_x__x_$" +
							    	  "__x___$";
		Universe expectedUniverse = new Universe(expectedUniverseSeed);
		universe.nextGeneration();
		assertThat(universe.asArray(),equalTo(expectedUniverse.asArray()));
	}
	
	@Test
	public void shouldCountNeighboursOfTopLeftCell() {
		String seed = "___$" +
				      "xxx$" +
				      "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(0,0),equalTo(2));
	}
	
	@Test
	public void shouldCountNeighboursOfTopCentralCell() {
		String seed = "___$" +
					  "xxx$" +
			          "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(0,1),equalTo(3));
	}

	@Test
	public void shouldCountNeighboursOfTopRightCell() {
		String seed = "___$" +
					  "xxx$" +
			          "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(0,2),equalTo(2));
	}

	@Test
	public void shouldCountNeighboursOfMiddleRightCell() {
		String seed = "___$" +
					  "xxx$" +
			          "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(1,2),equalTo(1));
	}

	@Test
	public void shouldCountNeighboursOfBottomRightCell() {
		String seed = "___$" +
					  "xxx$" +
			          "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(2,2),equalTo(2));
	}
	
	@Test
	public void shouldCountNeighboursOfBottomCentreCell() {
		String seed = "___$" +
					  "xxx$" +
		              "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(2,1),equalTo(3));
	}

	@Test
	public void shouldCountNeighboursOfBottomLeftCell() {
		String seed = "___$" +
					  "xxx$" +
		              "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(2,0),equalTo(2));
	}

	@Test
	public void shouldCountNeighboursOfMiddleLeftCell() {
		String seed = "___$" +
					  "xxx$" +
			          "___$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(1,0),equalTo(1));
	}

	@Test
	public void shouldCountNeighboursOfCentreCell() {
		String seed = "xxx$" +
					  "xxx$" +
			          "xxx$";
		Universe universe = new Universe(seed);
		assertThat(universe.countNeighbours(1,1),equalTo(8));
	}


}
