package grsu.regexparser.core.operations.impl;

import grsu.regexparser.core.operations.OperationStateGenerator;
import grsu.regexparser.core.path.Path;
import grsu.regexparser.core.state.State;
import grsu.regexparser.core.token.Token;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

import java.util.List;

public class PlusOperationStateGenerator implements OperationStateGenerator {

    @Override
    public boolean generateAndAddPathsWithStatesToFinalStates(List<State> finalStates, Token tokenToAnalyzeAndGenerateStates) {
        if(tokenToAnalyzeAndGenerateStates.getTokenString().equals("+")){
            finalStates.forEach(finalState ->{
                Path path= new Path(finalState,finalState) {
                    @Override
                    public boolean canUseThisPath(StrIteratorWrapper sourceStrIter) {
                        return finalState.getIncomesPaths().get(0).canUseThisPath(sourceStrIter);
                    }
                };
                finalState.addOutcomePath(path);
                finalState.addIncomePath(path);
            });
            return true;
        }
        return false;
    }

}
