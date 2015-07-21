package com.ivon.spcsai.misc;

public class HammingHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		int cost = 0;
		
		for (int i = 0; i < state.rows; i++)
			for (int j = 0; j < state.columns; j++)
				cost += ((state.tiles[i][j] != goalState.tiles[i][j]) ? 1 : 0);
		
		return cost;
	}
}

