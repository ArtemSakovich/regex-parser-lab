package grsu.regexparser.core.operations;

import grsu.regexparser.core.state.State;
import grsu.regexparser.core.token.Token;

import java.util.List;

public interface OperationStateGenerator {

    // return true if  token is convinient for generator
    boolean generateAndAddPathsWithStatesToFinalStates(List<State> finalStates, Token tokenToAnalyzeAndGenerateStates);

}
