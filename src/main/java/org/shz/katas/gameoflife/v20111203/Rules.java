package org.shz.katas.gameoflife.v20111203;


public class Rules {

	public CellState nextGeneration(CellState state, int nbrNeighbours) {
		if (state.equals(CellState.Dead) && nbrNeighbours < 3) {
			return CellState.Dead;
		}
		if (nbrNeighbours > 3 || nbrNeighbours <2)
			return CellState.Dead;
		return CellState.Alive;
	}

}
