package grsu.regexparser.core.state;

import grsu.regexparser.core.path.Path;

import java.util.ArrayList;
import java.util.List;

public class State {
    private boolean finalState;
    private List<Path> incomesPaths = new ArrayList<>();
    private List<Path> outcomesPaths = new ArrayList<>();

    public State(boolean finalState) {
        this.finalState=finalState;
    }

    public State(List<Path> incomesPaths, List<Path> outcomesPaths) {
        this.incomesPaths = incomesPaths;
        this.outcomesPaths = outcomesPaths;
    }


    public void addIncomePath(Path path){
        incomesPaths.add(path);
    }

    public void addOutcomePath(Path path){
        outcomesPaths.add(path);
    }



    //--------------------------------------------------

    public List<Path> getIncomesPaths() {
        return incomesPaths;
    }

    public void setIncomesPaths(List<Path> incomesPaths) {
        this.incomesPaths = incomesPaths;
    }

    public List<Path> getOutcomesPaths() {
        return outcomesPaths;
    }

    public void setOutcomesPaths(List<Path> outcomesPaths) {
        this.outcomesPaths = outcomesPaths;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }
}
