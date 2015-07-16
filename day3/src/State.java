import java.util.ArrayList;
import java.util.List;

/**
 * Created by MES on 7/16/2015.
 */
public class State {

    public static final int TOTAL_MISSIONARIES = 3;
    public static final int TOTAL_CANNIBALS = 3;

    private State parent;
    
    private int mMissionariesAcross;
    private int mCannibalsAcross;
    private boolean mBoatAcross;

    public State() {
        mMissionariesAcross = 0;
        mCannibalsAcross = 0;
        mBoatAcross = false;
    }

    public int getMissionariesAcross() {
        return mMissionariesAcross;
    }

    public int getCannibalsAcross() {
        return mCannibalsAcross;
    }

    public int getMissionariesNotAcross() {
        return TOTAL_MISSIONARIES - mMissionariesAcross;
    }

    public int getCannibalsNotAcross() {
        return TOTAL_CANNIBALS - mCannibalsAcross;
    }

    /**
     * Move a missionary across the river
     * @return  true if there are missionaries to move, false otherwise
     */
    public boolean moveMissionaryAcross() {
        if (mMissionariesAcross < TOTAL_MISSIONARIES) {
            mMissionariesAcross++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move a cannibal across the river
     * @return  true if there are cannibals to move, false otherwise
     */
    public boolean moveCannibalAcross() {
        if (mCannibalsAcross < TOTAL_CANNIBALS) {
            mCannibalsAcross++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move a missionary back
     * @return  true if there are missionaries to move, false otherwise
     */
    public boolean moveMissionaryBack() {
        if (mMissionariesAcross > 0) {
            mMissionariesAcross--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move a cannibal back
     * @return  true if there are cannibals to move, false otherwise
     */
    public boolean moveCannibalBack() {
        if (mCannibalsAcross > 0) {
            mCannibalsAcross--;
            return true;
        } else {
            return false;
        }
    }

    public boolean getBoatAcross() {
        return mBoatAcross;
    }

    public void setBoatAcross(boolean across) {
        mBoatAcross = across;
    }

    public List<State> generateChildStates() {

        List<State> states = new ArrayList<>();
        State state;

        if (!mBoatAcross) {

            // Try move 1 missionary across
            if ((getMissionariesNotAcross() >= 1)
                    && ((getMissionariesAcross()+1) >= (getCannibalsAcross()))
                    && ((getMissionariesNotAcross()-1) >= (getCannibalsNotAcross()))
                    ) {
                state = copy();
                state.moveMissionaryAcross();
                state.setBoatAcross(true);
                states.add(state);
            }

            // Try move 1 cannibal across
            if ((getCannibalsNotAcross() >= 1)
                    && ((getMissionariesAcross()) >= (getCannibalsAcross()+1))
                    && ((getMissionariesNotAcross()) >= (getCannibalsNotAcross()-1))
                    ) {
                state = copy();
                state.moveCannibalAcross();
                state.setBoatAcross(true);
                states.add(state);
            }

            // Try move 2 missionaries across
            if ((getMissionariesNotAcross() >= 2)
                    && ((getMissionariesAcross()+2) >= (getCannibalsAcross()))
                    && ((getMissionariesNotAcross()-2) >= (getCannibalsNotAcross()))
                    ) {
                state = copy();
                state.moveMissionaryAcross();
                state.moveMissionaryAcross();
                state.setBoatAcross(true);
                states.add(state);
            }

            // Try move 2 cannibals across
            if ((getCannibalsNotAcross() >= 2)
                    && ((getMissionariesAcross()) >= (getCannibalsAcross()+2))
                    && ((getMissionariesNotAcross()) >= (getCannibalsNotAcross()-2))
                    ) {
                state = copy();
                state.moveCannibalAcross();
                state.moveCannibalAcross();
                state.setBoatAcross(true);
                states.add(state);
            }

            // Try move 1 missionary and 1 cannibal across
            if ((getMissionariesNotAcross() >= 1 && getCannibalsNotAcross() >= 1)
                    && ((getMissionariesAcross()+1) >= (getCannibalsAcross()+1))
                    && ((getMissionariesNotAcross()-1) >= (getCannibalsNotAcross()-1))
                    ) {
                state = copy();
                state.moveMissionaryAcross();
                state.moveCannibalAcross();
                state.setBoatAcross(true);
                states.add(state);
            }

        } else {

            // Try move 1 missionary back
            if ((getMissionariesAcross() >= 1)
                    && ((getMissionariesAcross()-1) >= (getCannibalsAcross()))
                    && ((getMissionariesNotAcross()+1) >= (getCannibalsNotAcross()))
                    ) {
                state = copy();
                state.moveMissionaryBack();
                state.setBoatAcross(false);
                states.add(state);
            }

            // Try move 1 cannibal back
            if ((getCannibalsAcross() >= 1)
                    && ((getMissionariesAcross()) >= (getCannibalsAcross()-1))
                    && ((getMissionariesNotAcross()) >= (getCannibalsNotAcross()+1))
                    ) {
                state = copy();
                state.moveCannibalBack();
                state.setBoatAcross(false);
                states.add(state);
            }

            // Try move 2 missionaries back
            if ((getMissionariesAcross() >= 2)
                    && ((getMissionariesAcross()-2) >= (getCannibalsAcross()))
                    && ((getMissionariesNotAcross()+2) >= (getCannibalsNotAcross()))
                    ) {
                state = copy();
                state.moveMissionaryBack();
                state.moveMissionaryBack();
                state.setBoatAcross(false);
                states.add(state);
            }

            // Try move 2 cannibals back
            if ((getCannibalsAcross() >= 2)
                    && ((getMissionariesAcross()) >= (getCannibalsAcross()-2))
                    && ((getMissionariesNotAcross()) >= (getCannibalsNotAcross()+2))
                    ) {
                state = copy();
                state.moveCannibalBack();
                state.moveCannibalBack();
                state.setBoatAcross(false);
                states.add(state);
            }

            // Try move 1 missionary and 1 cannibal back
            if ((getMissionariesAcross() >= 1 && getCannibalsAcross() >= 1)
                    && ((getMissionariesAcross()-1) >= (getCannibalsAcross()-1))
                    && ((getMissionariesNotAcross()+1) >= (getCannibalsNotAcross()+1))
                    ) {
                state = copy();
                state.moveMissionaryBack();
                state.moveCannibalBack();
                state.setBoatAcross(false);
                states.add(state);
            }
            
        }

        return states;
    }

    public boolean isGoal() {
        return (getMissionariesAcross() == TOTAL_MISSIONARIES)
                && (getCannibalsAcross() == TOTAL_CANNIBALS);
    }

    public State copy() {
        State state = new State();
        state.parent = this;
        state.mMissionariesAcross = mMissionariesAcross;
        state.mCannibalsAcross = mCannibalsAcross;
        state.mBoatAcross = mBoatAcross;
        return state;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0; i<getMissionariesAcross(); i++) {
            s += "M";
        }
        for (int i=0; i<getCannibalsAcross(); i++) {
            s += "C";
        }
        if (getBoatAcross()) {
            s += " B~~~~ ";
        } else {
            s += " ~~~~B";
        }
        for (int i=0; i<getMissionariesNotAcross(); i++) {
            s += "M";
        }
        for (int i=0; i<getCannibalsNotAcross(); i++) {
            s += "C";
        }
        return s;
    }
}
