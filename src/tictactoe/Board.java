package tictactoe;

import java.util.Arrays;

public class Board {
	private final int width = 3, height = 3;
	private final char player1 = 'x', player2 = 'o';

	private char[][] cells = new char[width][height];


	/**
	 * @param x position on the board
	 * @param y position on the board
	 * @param player the character to be placed
	 * @return boolean if the move is valid an has been done
	 */
	private boolean move(int x, int y, char player){
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
	 * @return if a player has how the character of that player else 0
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

	private int evalPosition(char[][] position,int depth, boolean isMax){
		if (Board.checkWin(position) == 'x')
			return 1 - depth;
		else if(Board.checkWin(position) == 'o')
			return -1 - depth;
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

	public int[] minMax(){
		int max;
		int best = -2;
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

	private String prettyPrint(){
		String temp = "";
		temp+="_______\n";
		for (int i = 0; i < cells.length; i++) {
			temp+="|";
			for (int j = 0; j < cells[0].length; j++) {
				if(cells[j][i] == 0)
					temp += "0|";
				else
					temp+=cells[j][i] + "|";
			}
			temp+="\n";
		}
		temp+="¯¯¯¯¯¯¯";
		return temp;
	}

	@Override
	public String toString() {
		return prettyPrint();
	}

	public static void main(String[] args) {
		Board test = new Board();
		test.move(1, 0, 'x');
		test.move(1, 1, 'x');
		test.move(1, 2, 'o');
		test.move(2, 2, 'o');
		test.move(0, 2, 'x');
		test.move(0, 0, 'o');
		System.out.println(test);
		System.out.println(Arrays.toString(test.minMax()));
	}
}
