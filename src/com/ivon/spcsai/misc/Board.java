package com.ivon.spcsai.misc;

import java.util.ArrayList;
public class Board {
	public static int rows=5;
	public static int columns=3;
	private Board parent = null; /* only initial board's parent is null */
	private int distance;
	public int[][] tiles;
	
	private final static int[] drow = new int[] {1, -1, 0, 0};
	private final static int[] dcol = new int[] {0, 0, 1, -1};
	
	public Board(int[][] cells)                 //Populate states
	{
		distance = Integer.MAX_VALUE-1; //for the momentdasdasadsdsaasd
		
	  tiles = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
			{
				tiles[i][j] = cells[i][j];
			}
	}
	
	public boolean equals(Object x)     //Can be used for repeated state checking
	{
		Board another = (Board)x;
		if (columns != another.columns || rows != another.rows) return false;
		for (int i = 0; i<rows; i++)
			for (int j = 0; j<columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}
	
	private Board swap (int initrow, int initcol, int frow, int fcol) {
		Board newboard = new Board(clone2dArray(tiles));
		
		int aux = newboard.tiles[initrow][initcol];
		newboard.tiles[initrow][initcol] = newboard.tiles[frow][fcol];
		newboard.tiles[frow][fcol] = aux;
		
		return newboard;
	}
	
	private static int[][] clone2dArray(int[][] array) {
		int [][] newArray = new int[array.length][];
		for(int i = 0; i < array.length; i++) {
			newArray[i] = array[i].clone();
		}
		return newArray;
	}
	
	public ArrayList<Board> getSuccessors()     //Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	{
		int emptyrow = 0;
		int emptycol = 0;
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++)
				if (tiles[i][j] == 0) {
					emptyrow = i;
					emptycol = j;
					break;
				}
		
		ArrayList<Board> al = new ArrayList<>();
		
		int newrow, newcol;
		for (int i = 0; i < 4; i++) {
			newrow = emptyrow + drow[i];
			newcol = emptycol + dcol[i];
			
			if (newrow >= 0 && newrow < rows && newcol >= 0 && newcol < columns)
				al.add(swap(emptyrow, emptycol, newrow, newcol));
		}
		
		return al;
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
	
	public void setDistance(int dist) {
		this.distance = dist;
	}

	public int getDistance() {
		return distance;
	}
}
