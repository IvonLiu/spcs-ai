package com.ivon.spcsai.misc;

public class EmptyHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		return 0;
	}
}
