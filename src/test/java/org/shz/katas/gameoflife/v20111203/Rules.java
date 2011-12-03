package org.shz.katas.gameoflife.v20111203;

public class Rules {
	public Cell nextGeneration(Cell cell, int nbrNeighbours) {
		if (cell instanceof DeadCell && nbrNeighbours < 3) {
			return new DeadCell();
		}
		if (nbrNeighbours > 3 || nbrNeighbours <2)
			return new DeadCell();
		return new LiveCell();
	}

}
