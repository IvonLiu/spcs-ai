package com.ivon.spcsai.misc;

public class ManhattanHeuristic implements AStarHeuristic{
	private int ManhattanDistance(int initrow, int initcol, int frow, int fcol) {
		return Math.abs(initrow - frow) + Math.abs(initcol - fcol);
	}
	
	public int getCost(Board state, Board goalState)
	{
		int cost = 0;
		for (int i = 0; i < state.rows; i++)
			for (int j = 0; j < state.columns; j++)
				for (int k = 0; k < goalState.rows; k++)
					for (int l = 0; l < goalState.columns; l++)
						if (state.tiles[i][j] == goalState.tiles[k][l])
							cost += ManhattanDistance(i, j, k, l);
		
		return cost;
	}
}

