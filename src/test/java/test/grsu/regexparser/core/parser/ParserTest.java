package test.grsu.regexparser.core.parser;

import grsu.regexparser.core.parser.Parser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class ParserTest {

    @Test
    void parse1(){
        Parser parser =new Parser();
        List<String> subStrs=parser.getSubStringsByRegex("aabbbaaacsk","ab*a+");
        assertIterableEquals(List.of("aa","aaa"),subStrs);
    }

    @Test
    void parse2(){
        Parser parser =new Parser();
        List<String> subStrs=parser.getSubStringsByRegex("aabbbaaacsk","a*a");
        assertIterableEquals(List.of("aa","aaa"),subStrs);
    }

    @Test
    void parse3(){
        Parser parser =new Parser();
        List<String> subStrs=parser.getSubStringsByRegex("aabbbaaacsk","ab+");
        assertIterableEquals(List.of("abbb"),subStrs);
    }

    @Test
    void parse4(){
        Parser parser =new Parser();
        List<String> subStrs=parser.getSubStringsByRegex("abbbaaacsk","ab*a+csk");
        assertIterableEquals(List.of("abbbaaacsk"),subStrs);
    }

    @Test
    void parse5(){
        Parser parser =new Parser();
        List<String> subStrs=parser.getSubStringsByRegex("aabbbaaacsk","ab+a+csk");
        assertIterableEquals(List.of("abbbaaacsk"),subStrs);
    }

    @Test
    void parseWhenYouHaveTwoWays1(){
        Parser parser =new Parser();
        List<String> subStrs=parser.getSubStringsByRegex("gfdkaaaaaaaabbb","ka+a*b");
        assertIterableEquals(List.of("kaaaaaaaab"),subStrs);
    }

    @Test
    void parseWhenYouHaveTwoWays2(){
        Parser parser =new Parser();
        List<String> subStrs=parser.getSubStringsByRegex("dslfkdddsldks","fk?d+d*sl");
        assertIterableEquals(List.of("fkdddsl"),subStrs);
    }
}