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

}
