package grsu.regexparser.core.tokenizer.impl;

import grsu.regexparser.core.token.Token;
import grsu.regexparser.core.tokenizer.Tokenizer;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

import java.util.Stack;

public class ParenthesesTokenizer implements Tokenizer {
    private final Stack<Character> bracketsCounter = new Stack<>();

    @Override
    public Token findNextToken(StrIteratorWrapper strIteratorWrapper) {
        if(strIteratorWrapper.getCurrentSymbol() == '('){
            bracketsCounter.push('(');
            StringBuilder token = new StringBuilder("(");
            int i;
            for(i = strIteratorWrapper.getIndex()+1; i < strIteratorWrapper.getRegexString().length; i++){
                token.append(strIteratorWrapper.getRegexString()[i]);
                switch (strIteratorWrapper.getRegexString()[i]){
                    case '(':
                        bracketsCounter.push('(');
                        break;
                    case ')':
                        bracketsCounter.pop();
                        if(bracketsCounter.isEmpty()){
                            strIteratorWrapper.setIndex(i);
                            return new Token(token.toString());
                        }
                        break;
                }
            }
        }
        return new Token("");
    }
}
