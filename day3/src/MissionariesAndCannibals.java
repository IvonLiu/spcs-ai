import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by MES on 7/16/2015.
 */
public class MissionariesAndCannibals {

    public static void main(String[] args) {

        State solution = searchForSolution();

        if (solution == null) {
            System.out.println("No solution found");
        } else {
            solution.printPath();
        }
    }

    private static State searchForSolution() {
        State initState = new State();
        Queue<State> queue = new LinkedList<>();    // frontier
        queue.add(initState);
        Set<State> explored = new HashSet<>();

        while (!queue.isEmpty()) {

            State state = queue.poll();
            explored.add(state);

            for (State childState : state.generateChildStates()) {
                if (!queue.contains(childState) && !explored.contains(childState)) {
                    if (childState.isGoal()) {
                        return childState;
                    } else {
                        queue.add(childState);
                    }
                }
            }
        }

        return null;
    }
}
