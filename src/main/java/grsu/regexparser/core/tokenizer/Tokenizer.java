package grsu.regexparser.core.tokenizer;

import grsu.regexparser.core.token.Token;
import grsu.regexparser.core.wrapper.StrIteratorWrapper;

public interface Tokenizer {

    Token findNextToken(StrIteratorWrapper strIteratorWrapper);

}
