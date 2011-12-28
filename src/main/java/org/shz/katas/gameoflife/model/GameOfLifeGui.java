package org.shz.katas.gameoflife.model;

import javax.swing.JFrame;

public class GameOfLifeGui extends JFrame {

	private static final long serialVersionUID = -4849442237519920467L;

	public static void main(String args[]) {
		GameOfLifeGui game = new GameOfLifeGui();
		game.startup();
	}

	private void startup() {
		setTitle("Game of Life");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
