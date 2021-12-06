package grsu.regexparser.core.operations.impl;

import grsu.regexparser.core.operations.OperationStateGenerator;
import grsu.regexparser.core.path.Path;
import grsu.regexparser.core.state.State;
import grsu.regexparser.core.token.Token;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

import java.util.ArrayList;
import java.util.List;

public class MultiplyOperationStateGenerator implements OperationStateGenerator {
    @Override
    public boolean generateAndAddPathsWithStatesToFinalStates(List<State> finalStates, Token tokenToAnalyzeAndGenerateStates) {
        if(tokenToAnalyzeAndGenerateStates.getTokenString().equals("*")){
            List<State> newFinalStatesToAdd = new ArrayList<>();

            finalStates.forEach(finalState ->{
                finalState.getIncomesPaths()
                        .stream()
                        .map(Path::getFromState)
                        .forEach(stateFrom -> {
                            stateFrom.setFinalState(true);
                            newFinalStatesToAdd.add(stateFrom);
                        });

                Path path= new Path(finalState,finalState) {
                    @Override
                    public boolean canUseThisPath(StrIteratorWrapper sourceStrIter) {
                        return finalState.getIncomesPaths().get(0).canUseThisPath(sourceStrIter);
                    }
                };
                finalState.addOutcomePath(path);
                finalState.addIncomePath(path);


            });

            finalStates.addAll(newFinalStatesToAdd);
            return true;
        }
        return false;
    }
}
