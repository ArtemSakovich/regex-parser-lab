package grsu.regexparser.core.operations.impl;

import grsu.regexparser.core.operations.OperationStateGenerator;
import grsu.regexparser.core.path.Path;
import grsu.regexparser.core.state.State;
import grsu.regexparser.core.token.Token;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

import java.util.*;

public class SymbolOperationStateGenerator implements OperationStateGenerator {


    @Override
    public boolean generateAndAddPathsWithStatesToFinalStates(List<State> finalStates, Token tokenToAnalyzeAndGenerateStates) {
        Set<State> newFinalStates=new HashSet<>();

        finalStates.forEach(finalState -> {
            finalState.getOutcomesPaths()
                    .stream()
                    .filter(e->
                            e.canUseThisPath(
                                    new StrIteratorWrapper(
                                            tokenToAnalyzeAndGenerateStates.getTokenString()
                                                    .toCharArray()
                                    )
                            )
                    ).findFirst()
                    .ifPresentOrElse(
                            path -> {
                                newFinalStates.addAll(finalStates);
                                newFinalStates.remove(finalState);
                                finalState.setFinalState(false);

                                State stateTo=path.getToState();

                                stateTo.setFinalState(true);
                                newFinalStates.add(stateTo);
                            },
                            ()->{
                                State newFinalState = new State(true);
                                Path pathFromFinalStateToNewState = new Path(finalState,newFinalState) {
                                    @Override
                                    public boolean canUseThisPath(StrIteratorWrapper sourceStrIter) {
                                        return tokenToAnalyzeAndGenerateStates.getTokenString().equals(String.valueOf(sourceStrIter.getCurrentSymbol()));
                                    }
                                };
                                finalState.addOutcomePath(pathFromFinalStateToNewState);
                                newFinalState.addIncomePath(pathFromFinalStateToNewState);

                                finalState.setFinalState(false);

                                newFinalStates.add(newFinalState);
                            }
                    );
        });

        finalStates.clear();
        finalStates.addAll(newFinalStates);

        return true;
    }
}
