package com.ivon.spcsai.day4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar {
	 
	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;

	public AStar(Board initial, Board goal, AStarHeuristic heur)
	{
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}

	public void search()
	{
      	/* Declare and initialize Frontier and Explored data structures */ 
		/* Put start node in Fringe list Frontier */
		Queue<Board> frontier = new PriorityQueue<>(11, new Comparator<Board>() {
			@Override
			public int compare(Board a, Board b) {
				int aScore = a.getGeneration() + heuristic.getCost(a, goalState);
				int bScore = b.getGeneration() + heuristic.getCost(b, goalState);

				if (aScore > bScore) {
					return 1;
				} else if (aScore < bScore) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		while (!Frontier.isEmpty())
		{
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/

			if (n.equals(goalState))
			{
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
				printSolution(n);
			}

			ArrayList<Board> successors = n.getSuccessors();
			for (int i = 0 ;i<successors.size(); i++)
			{
				Board n1 = successors.get(i);
				/* if n1 is not already in either Frontier or Explored
				      Compute h(n1), g(n1) = g(n)+c(n, n1), f(n1)=g(n1)+h(n1), place n1 in Frontier
				   if n1 is already in either Frontier or Explored
				      if g(n1) is lower for the newly generated n1
				          Replace existing n1 with newly generated g(n1), h(n1), set parent of n1 to n
				          if n1 is in Explored list
				              Move n1 from Explored to Frontier list*/
			}
		}
		System.out.println("No Solution");
	}

	private void printSolution(Board board) {
		if (board.getParent() == null) {
			board.print();
		}

		printSolution(board.getParent());
		board.print();
	}

}
