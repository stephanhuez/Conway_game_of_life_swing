package org.shz.katas.gameoflife.model;

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
