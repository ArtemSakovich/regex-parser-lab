package grsu.regexparser.core.machine;

import grsu.regexparser.core.operations.OperationStateGenerator;
import grsu.regexparser.core.operations.impl.MultiplyOperationStateGenerator;
import grsu.regexparser.core.operations.impl.PlusOperationStateGenerator;
import grsu.regexparser.core.operations.impl.QuestionOperationStateGenerator;
import grsu.regexparser.core.operations.impl.SymbolOperationStateGenerator;
import grsu.regexparser.core.state.State;
import grsu.regexparser.core.token.Token;
import grsu.regexparser.core.tokenizer.Tokenizer;
import grsu.regexparser.core.tokenizer.impl.ParenthesesTokenizer;
import grsu.regexparser.core.tokenizer.impl.SquareBracketsTokenizer;
import grsu.regexparser.core.tokenizer.impl.SymbolTokenizer;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

import java.util.ArrayList;
import java.util.List;

public class MachineGenerator {
    private List<Tokenizer> tokenizers = new ArrayList<>();
    private Tokenizer defaultTokenizer;

    private List<OperationStateGenerator> operationStateGenerators= new ArrayList<>();
    private OperationStateGenerator defaultOperationStateGenerators;

    public MachineGenerator() {
        tokenizers.add(new ParenthesesTokenizer());
        tokenizers.add(new SquareBracketsTokenizer());
        defaultTokenizer = new SymbolTokenizer();

        operationStateGenerators.add(new MultiplyOperationStateGenerator());
        operationStateGenerators.add(new PlusOperationStateGenerator());
        operationStateGenerators.add(new QuestionOperationStateGenerator());
        defaultOperationStateGenerators=new SymbolOperationStateGenerator();
    }


    public State generateStateMachineForRegexStrAndGetRootState(String regex){
        char[] regexCharArr=regex.toCharArray();
        State rootState = new State(false);

        List<Token> tokens = generateTokens(regexCharArr);
        generateStatesAndConnectThemToRootState(rootState,tokens);

        return rootState;
    }

    private void generateStatesAndConnectThemToRootState(State rootState,List<Token> allTokens){
        List<State> finalStates= new ArrayList<>();
        finalStates.add(rootState);


        allTokens.forEach(token ->
                operationStateGenerators.stream()
                        .map(operationStateGenerator ->
                                operationStateGenerator.generateAndAddPathsWithStatesToFinalStates(finalStates,token)
                        ).filter(Boolean.TRUE::equals)
                        .findFirst()
                        .orElseGet(()->defaultOperationStateGenerators.generateAndAddPathsWithStatesToFinalStates(finalStates,token))
        );
    }

    private List<Token> generateTokens(char[] sourceStr){
        List<Token> tokens = new ArrayList<>(sourceStr.length);
        for(StrIteratorWrapper wrapper = new StrIteratorWrapper(sourceStr);
            wrapper.indexLessThanRegexStrLength();
            wrapper.incrementIndex()){

            tokens.add(tokenizers.stream()
                    .map(tokenizer -> tokenizer.findNextToken(wrapper))
                    .takeWhile( e -> (e != null) && (!e.getTokenString().equals("")) )
                    .findFirst()
                    .orElseGet( () -> defaultTokenizer.findNextToken(wrapper))
            );

        }
        return tokens;
    }

}
