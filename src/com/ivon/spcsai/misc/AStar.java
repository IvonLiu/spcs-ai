package com.ivon.spcsai.misc;

import java.util.*;
public class AStar {

	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;

	Set<Board> visited = new HashSet<Board>();

	public static class PriorityQueueState {
		Board state;
		int priority;

		public PriorityQueueState(Board state, int priority) {
			this.state = state;
			this.priority = priority;
		}
	}

	private PriorityQueueState makePriorityQueueState(Board state, int priority) {
		PriorityQueueState newPriorityQueueState = new PriorityQueueState(state, priority);
		return newPriorityQueueState;
	}

	public static class PriorityQueueComparator implements Comparator<PriorityQueueState>{
		@Override
		public int compare(PriorityQueueState arg0, PriorityQueueState arg1) {
			if (arg0.priority > arg1.priority)
				return 1;
			else if (arg0.priority < arg1.priority)
				return -1;
			else
				return 0;

			//return arg0.priority - arg1.priority;
		}
	}

	PriorityQueue<PriorityQueueState> frontier = new PriorityQueue(11, new PriorityQueueComparator());

	public AStar(Board initial, Board goal, AStarHeuristic heur)
	{
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}
	private void printSolution(Board board) {
		if (board.getParent() == null) {
			System.out.println("");
			board.print();
			return;
		}

		printSolution(board.getParent());

		System.out.println("");
		board.print();
	}
	/*public void printSolution(Board state) {
		if (state.equals(initialState)) {
			state.print();
			return;
		}

			printSolution(state.getParent());
			state.print();
	}*/
	
	public void search()
	{
      	/* Declare and initialize Frontier and Explored data structures */ 
		/* Put start node in Fringe list Frontier */
		initialState.setDistance(0);
		frontier.add(makePriorityQueueState(initialState, heuristic.getCost(initialState, goalState)));
		
		while (!frontier.isEmpty())
		{
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/
			Board n = frontier.poll().state;

			if (visited.contains(n))
				continue;
			visited.add(n);
			if (n.equals(goalState))
			{
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
				System.out.println("\nExplored " + visited.size() + " states\n");
				System.out.println("Printing solution");
				printSolution(goalState);
				return ;
			}
			ArrayList<Board> successors = n.getSuccessors();

			for (int i = 0; i<successors.size(); i++)
			{
				Board n1 = successors.get(i);

				/* if n1 is not already in either Frontier or Explored
				      Compute h(n1), g(n1) = g(n)+c(n, n1), f(n1)=g(n1)+h(n1), place n1 in Frontier
				   if n1 is already in either Frontier or Explored
				      if g(n1) is lower for the newly generated n1
				          Replace existing n1 with newly generated g(n1), h(n1), set parent of n1 to n
				          if n1 is in Explored list
				              Move n1 from Explored to Frontier list*/
				if (n.getDistance() + 1 < n1.getDistance()) {
					n1.setDistance(n.getDistance() + 1);
					n1.setParent(n);
					frontier.add(makePriorityQueueState(n1, n1.getDistance() + heuristic.getCost(n1,  goalState)));
				}
			}
		}
		System.out.println("No Solution");
	}
}
