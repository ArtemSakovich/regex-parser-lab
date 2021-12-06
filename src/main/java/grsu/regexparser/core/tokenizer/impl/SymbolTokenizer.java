package grsu.regexparser.core.tokenizer.impl;

import grsu.regexparser.core.token.Token;
import grsu.regexparser.core.tokenizer.Tokenizer;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

public class SymbolTokenizer implements Tokenizer {
    @Override
    public Token findNextToken(StrIteratorWrapper strIteratorWrapper) {
        return new Token(String.valueOf(strIteratorWrapper.getCurrentSymbol()));
    }
}
