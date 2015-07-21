package com.ivon.spcsai.day4;

public class EmptyHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		return 0;
	}

}
