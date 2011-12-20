package org.shz.katas.gameoflife.v20111203;

public class Universe {
	private static final int UNDERSCORE = 95;
	private String seed;
	private int height = 0;
	private int width = 0;
	private CellState[][] cells;

	public Universe(String seed) {
		this.seed = seed;
		populate();
	}

	private void populate() {
		String[] rows = seed.split("\\$");
		createUniverse(rows);
		populateUniverse(rows);
	}

	private void createUniverse(String[] rows) {
		height = rows.length;
		width = rows[0].length();
		cells = new CellState[height][width];
	}

	private void populateUniverse(String[] rows) {
		for (int i = 0; i < height; i++) {
			populateRow(rows[i], i);
		}
	}

	private void populateRow(String content, int rowNumber) {
		for (int j = 0; j < width; j++) {
			cells[rowNumber][j] = createCell(content.charAt(j));
		}
	}

	private CellState createCell(char charAt) {
		if (UNDERSCORE == charAt)
			return CellState.Dead;
		return CellState.Alive;
	}

	public CellState[][] asArray() {
		return this.cells;
	}

	public void nextGeneration() {
		Rules rules = new Rules();
		CellState[][] nextState = new CellState[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int nbrNeighbours = countNeighbours(i, j);
				nextState[i][j] = rules.nextGeneration(cells[i][j],
						nbrNeighbours);
			}
		}
		cells = nextState;
	}

	protected int countNeighbours(int i, int j) {
		int count = 0;
		if (notRightCell(j)) {
			count += countAsNeighbour(eastCell(i, j));
		}
		if (notRightCell(j) && notBottomCell(i)) {
			count += countAsNeighbour(southEastCell(i,j));
		}
		if (notBottomCell(i)) {
			count += countAsNeighbour(southCell(i,j));
		}
		if (notLeftCell(j) && notBottomCell(i)) {
			count += countAsNeighbour(southWestCell(i,j));
		}
		if (notLeftCell(j)) {
			count += countAsNeighbour(westCell(i,j));
		}
		if (notTopCell(i) && notLeftCell(j)) {
			count += countAsNeighbour(northWestCell(i,j));
		}
		if (notTopCell(i)) {
			count += countAsNeighbour(northCell(i,j));
		}
		if (notRightCell(j) && notTopCell(i)) {
			count += countAsNeighbour(northEastCell(i,j));
		}
		return count;
	}

	private int countAsNeighbour(CellState cellState) {
		return isAlive(cellState) ? 1 : 0;
	}

	private boolean notLeftCell(int j) {
		return j > 0;
	}

	private boolean notRightCell(int j) {
		return j < width - 1;
	}

	private boolean notTopCell(int i) {
		return notLeftCell(i);
	}

	private boolean notBottomCell(int i) {
		return i < height - 1;
	}

	private boolean isAlive(CellState cellState) {
		return CellState.Alive.equals(cellState);
	}

	private CellState northEastCell(int i, int j) {
		return cells[i - 1][j + 1];
	}

	private CellState northCell(int i, int j) {
		return cells[i - 1][j];
	}

	private CellState northWestCell(int i, int j) {
		return cells[i - 1][j - 1];
	}

	private CellState southWestCell(int i, int j) {
		return cells[i + 1][j - 1];
	}

	private CellState westCell(int i, int j) {
		return cells[i][j - 1];
	}

	private CellState southEastCell(int i, int j) {
		return cells[i + 1][j + 1];
	}

	private CellState eastCell(int i, int j) {
		return cells[i][j + 1];
	}

	private CellState southCell(int i, int j) {
		return cells[i + 1][j];
	}

	public String asString() {
		String result = new String();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				result += cellToString(cells[i][j]);
			}
			result += "$";
		}
		return result;
	}

	private String cellToString(CellState cellState) {
		if (CellState.Alive.equals(cellState)) {
			return "x";
		}
		return "_";
	}

}
