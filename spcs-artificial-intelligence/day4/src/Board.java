import java.util.ArrayList;

public class Board {
	public static int rows=5;
	public static int columns=3;
	private Board parent = null; /* only initial board's parent is null */
	public int[][] tiles;
	private int distance;

	public Board(int distance, int[][] cells)                 //Populate states
	{
		this.distance = distance;

	  tiles = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
			{
				tiles[i][j] = cells[i][j];
			}
	}
	public boolean equals(Object x)         //Can be used for repeated state checking
	{
		Board another = (Board)x;
		if (columns != another.columns || rows != another.rows) return false;
		for (int i = 0; i<rows; i++)
			for (int j = 0; j<columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}
	public ArrayList<Board> getSuccessors()     //Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	{
		ArrayList<Board> successors = new ArrayList<>();

		Board board;
		for (int i=LEFT; i<=DOWN; i++) {
			board = swap(i);
			if (board != null) {
				board.setParent(this);
				successors.add(board);
			} else {
			}
		}
		return successors;
	}

	private static final int LEFT = 0;
	private static final int RIGHT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;

	private Board swap(int tile) {

		// Locate position of 0 tile
		int row0 = 0;
		int col0 = 0;
		for (int row=0; row<tiles.length; row++) {
			for (int col=0; col<tiles[row].length; col++) {
				if (tiles[row][col] == 0) {
					row0 = row;
					col0 = col;
				}
			}
		}

		int[][] newTiles = clone2dArray(tiles);

		switch (tile) {

			case LEFT:
			{
				if (col0 == 0) {
					return null;
				}
				newTiles[row0][col0] = newTiles[row0][col0-1];
				newTiles[row0][col0-1] = 0;
				break;
			}

			case RIGHT:
			{
				if (col0 == columns-1) {
					return null;
				}
				newTiles[row0][col0] = newTiles[row0][col0+1];
				newTiles[row0][col0+1] = 0;
				break;
			}

			case UP:
			{
				if (row0 == 0) {
					return null;
				}
				newTiles[row0][col0] = newTiles[row0-1][col0];
				newTiles[row0-1][col0] = 0;
				break;
			}

			case DOWN:
			{
				if (row0 == rows-1) {
					return null;
				}
				newTiles[row0][col0] = newTiles[row0+1][col0];
				newTiles[row0+1][col0] = 0;
				break;
			}
		}

		return new Board(distance+1, newTiles);
	}

	public void print()
	{
		for (int i = 0; i<rows; i++)
		{
			for (int j = 0 ;j<columns; j++)
			{
				if (j > 0) System.out.print("\t");
				System.out.print(tiles[i][j]);
			}
			System.out.println();
		}
	}
	
	public void setParent(Board parentBoard)
	{
		parent = parentBoard;
	}
	
	public Board getParent()
	{
		return parent;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return distance;
	}

	private static int[][] clone2dArray(int[][] array) {
		int [][] newArray = new int[array.length][];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i].clone();
		}
		return newArray;
	}
}
