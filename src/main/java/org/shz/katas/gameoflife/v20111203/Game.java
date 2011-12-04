package org.shz.katas.gameoflife.v20111203;

public class Game {

	private String currentState;
	private Universe universe;


	public Game(String seed) {
		this.currentState = seed;
		this.universe = new Universe(seed);
	}


	public String nextGeneration() {
		return currentState;
	}

	public Universe getUniverse() {
		return universe;
	}

}
