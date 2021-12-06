package grsu.regexparser.core.operations.impl;

import grsu.regexparser.core.operations.OperationStateGenerator;
import grsu.regexparser.core.path.Path;
import grsu.regexparser.core.state.State;
import grsu.regexparser.core.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionOperationStateGenerator implements OperationStateGenerator {
    @Override
    public boolean generateAndAddPathsWithStatesToFinalStates(List<State> finalStates, Token tokenToAnalyzeAndGenerateStates) {
        if(tokenToAnalyzeAndGenerateStates.getTokenString().equals("?")){
            List<State> newFinalStatesToAdd = new ArrayList<>();
            finalStates.forEach(finalState ->
                finalState.getIncomesPaths()
                        .stream()
                        .map(Path::getFromState)
                        .forEach(state -> {
                            state.setFinalState(true);
                            newFinalStatesToAdd.add(state);
                        })
            );
            finalStates.addAll(newFinalStatesToAdd);
            return true;
        }
        return false;
    }
}
