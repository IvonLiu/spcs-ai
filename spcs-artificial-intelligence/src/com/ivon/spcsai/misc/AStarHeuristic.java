package com.ivon.spcsai.misc;

public interface AStarHeuristic {
	public int getCost(Board state, Board goalState);
}
