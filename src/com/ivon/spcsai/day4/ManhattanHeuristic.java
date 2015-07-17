package com.ivon.spcsai.day4;

public class ManhattanHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		int h = 0;

		for (int row=0; row<state.tiles.length; row++) {
			for (int col=0; col<state.tiles[row].length; col++) {
				if (state.tiles[row][col] != 0) {

					boolean found = false;

					for (int goalRow=0; goalRow<goalState.tiles.length; goalRow++) {
						for (int goalCol=0; goalCol<goalState.tiles[row].length; goalCol++) {
							if (state.tiles[row][col] == goalState.tiles[goalRow][goalCol]) {
								h = h + Math.abs(goalRow - row) + Math.abs(goalCol - col);
								found = true;
								break;
							}
						}
						if (found) {
							break;
						}
					}

				}
			}
		}

		return h;
	}
}

