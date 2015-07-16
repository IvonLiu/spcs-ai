import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by MES on 7/16/2015.
 */
public class MissionariesAndCannibals {

    public static void main(String[] args) {

        State solution = searchForSolution();

        if (solution == null) {
            System.out.println("No solution found");
        } else {
            System.out.println(solution);
        }
    }

    private static State searchForSolution() {
        Queue<State> queue = new LinkedList<>();
        State initState = new State();
        queue.add(initState);

        while (true) {

            if (queue.isEmpty()) {
                return null;
            }

            State state = queue.poll();
            System.out.println(state);
            if (state.isGoal()) {
                return state;
            }

            for (State childState : state.generateChildStates()) {
                queue.add(childState);
            }
        }
    }
}
