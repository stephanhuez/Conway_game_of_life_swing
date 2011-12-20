package org.shz.katas.gameoflife.v20111203;

public class Game {

	private Universe universe;

	public Game(String seed) {
		this.universe = new Universe(seed);
	}

	public String nextGeneration() {
		universe.nextGeneration();
		return universe.asString();
	}

}
