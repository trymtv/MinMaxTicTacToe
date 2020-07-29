package com.github.trymtv;

import java.util.Arrays;

public class Board {
	private final int width = 3, height = 3;
	private final char player1 = 'x', player2 = 'o';

	private char[][] cells = new char[width][height];


	/**
	 * @param x position on the board
	 * @param y position on the board
	 * @param player the character to be placed
	 * @return a boolean if the move is valid an has been done
	 */
	public boolean move(int x, int y, char player){
		if (x >= width || y >= height)
			throw new IllegalArgumentException("Index not in range.");
		if (cells[x][y] == 0){
			cells[x][y] = player;
			return true;
		}
		return false;
	}

	/**
	 * Checks if one of the player has won
	 * @return a boolean of if a player has how the character of that player else 0
	 */
	public static char checkWin(char[][] cells){
		for (int i = 0; i < 3; i++) {
			if (compareEqualCells(cells[i]))
				return cells[i][0];
			else if(compareEqualCells(cells[0][i], cells[1][i], cells[2][i]))
				return cells[0][i];
		}
		if (compareEqualCells(cells[0][0], cells[1][1], cells[2][2]))
			return cells[0][0];
		else if(compareEqualCells(cells[0][2], cells[1][1], cells[2][0]))
			return cells[0][2];
		return 0;
	}

	public static boolean isEmptyCell(char[][] cells){
		for (char[] cellRow : cells) {
			for (char cell :cellRow) {
				if (cell == 0)
					return true;
			}
		}
		return false;
	}


	//helper function for comparing that given characters are equal
	private static boolean compareEqualCells(char... cells){
		if(cells[0] == 0)
			return false;
		for (int i = 1; i < cells.length; i++) {
			if(cells[0] != cells[i])
				return false;
		}
		return true;
	}

	//helper function for the minmax function
	//	the win is weighted so the fastest win will always be chosen
	private int evalPosition(char[][] position,int depth, boolean isMax){
		if (Board.checkWin(position) == 'x')
			return 10 -depth;
		else if(Board.checkWin(position) == 'o')
			return -10 -depth;
		else if(!Board.isEmptyCell(position))
			return 0;

		int[] move;
		int max;
		if(isMax){
			max = -100;
			for (int i = 0; i < position.length; i++) {
				for (int j = 0; j < position[0].length; j++) {
					if (position[i][j] == 0){
						position[i][j] = 'x';
						max = Math.max(max, evalPosition(position,depth+1, !isMax));
						position[i][j] = 0;
					}
				}
			}
		}else {
			max = 100;
			for (int i = 0; i < position.length; i++) {
				for (int j = 0; j < position[0].length; j++) {
					if (position[i][j] == 0){
						position[i][j] = 'o';
						max = Math.min(max, evalPosition(position,depth+1, !isMax));
						position[i][j] = 0;
					}
				}
			}
		}
		return max;
	}

	/**
	 * Assesses the board and chooses the optimal move for the fastest win
	 * @return a int[] with the {x, y} coordinates of the optimal move
	 */
	public int[] minMax(){
		int max;
		int best = -100;
		int[] move  = null;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] == 0){
					cells[i][j] = 'x';
					max = evalPosition(cells,0, false);
					cells[i][j] = 0;
					if(max > best) {
						best = max;
						move = new int[] {i, j};
					}
				}
			}
		}
		return move;
	}


	/**
	 * Concatenates the tictactoe board to a more readable format
	 * @return a string of the readable format of the board
	 */
	private String prettyPrint(){
		StringBuilder temp = new StringBuilder();
		temp.append("_______\n");
		for (int i = 0; i < cells.length; i++) {
			temp.append("|");
			for (int j = 0; j < cells[0].length; j++) {
				if(cells[j][i] == 0)
					temp.append("0|");
				else
					temp.append(cells[j][i] + "|");
			}
			temp.append("\n");
		}
		temp.append("¯¯¯¯¯¯¯");
		return temp.toString();
	}

	@Override
	public String toString() {
		return prettyPrint();
	}

	public static void main(String[] args) {
		Board test = new Board();
		test.move(0,0, 'o');
		test.move(1, 1, 'x');
		test.move(2,0, 'o');
		test.move(1, 0, 'x');


		System.out.println(test);
		System.out.println(test.prettyPrint());
		System.out.println(Arrays.toString(test.minMax()));
	}
}
