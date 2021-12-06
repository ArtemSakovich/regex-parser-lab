package grsu.regexparser.core.path;

import grsu.regexparser.core.state.State;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

public abstract class Path {
    private State fromState;
    private State toState;



    public Path(State fromState, State toState) {
        this.fromState = fromState;
        this.toState = toState;
    }

    public abstract boolean canUseThisPath(StrIteratorWrapper sourceStrIter);



    //---------------------------------------------
    public State getFromState() {
        return fromState;
    }

    public void setFromState(State fromState) {
        this.fromState = fromState;
    }

    public State getToState() {
        return toState;
    }

    public void setToState(State toState) {
        this.toState = toState;
    }
}
