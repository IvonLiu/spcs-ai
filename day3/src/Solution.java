import java.util.ArrayList;
import java.util.List;

/**
 * Created by MES on 7/16/2015.
 */
public class Solution {

    private List<State> mStateList;

    private Solution() {
        mStateList = new ArrayList<>();
    }

    @Override
    public String toString() {
        String s = "";
        for (State state : mStateList) {
            s = s + "\n" + state;
        }
        return s;
    }
}
