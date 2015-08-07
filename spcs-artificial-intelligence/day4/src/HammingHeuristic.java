public class HammingHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		int h = 0;

		for (int row=0; row<state.tiles.length; row++) {
			for (int col=0; col<state.tiles[row].length; col++) {
				if (state.tiles[row][col] != 0) {
					if (state.tiles[row][col] != goalState.tiles[row][col]) {
						h++;
					}
				}
			}
		}

		return h;
	}
}

