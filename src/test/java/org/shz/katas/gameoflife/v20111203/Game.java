package org.shz.katas.gameoflife.v20111203;

public class Game {

	private String currentState;

	public Game(String seed) {
		this.currentState = seed;
	}

	public String nextGeneration() {
		return currentState;
	}

}
