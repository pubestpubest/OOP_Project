package Tokenizer;
import Interfaces.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class ConstructionPlanTokenizerTest {

    @Test
    void sizeTest1() {
        ConstructionPlanTokenizer tokenizer = new ConstructionPlanTokenizer("done \n"+"done\n");
        List<Token> tokens = tokenizer.getTokens();
        assertEquals(2, tokens.size());
    }
    @Test
    void sizeTest2() {
        ConstructionPlanTokenizer tokenizer = new ConstructionPlanTokenizer("done "+"done\n");
        List<Token> tokens = tokenizer.getTokens();
        assertEquals(2, tokens.size());
    }
    @Test
    void sizeTest3() {
        ConstructionPlanTokenizer tokenizer = new ConstructionPlanTokenizer("done"+"done\n");
        List<Token> tokens = tokenizer.getTokens();
        assertEquals(1, tokens.size());
    }
    @Test
    void sizeTest4() {
        String arithmetic = "i = 100\n" +
                "while(i){\n" +
                "invest i\n" +
                "i = i - 2\n" +
                "}\n";
        ConstructionPlanTokenizer tokenizer = new ConstructionPlanTokenizer(arithmetic);
        List<Token> tokens = tokenizer.getTokens();
        assertEquals(16, tokens.size());
    }
    @Test
    void getTokenTest1() {
        Tokenizer tokenizer = new ConstructionPlanTokenizer("t = t + 1");
        List<Token> tokens = tokenizer.getTokens();
        StringBuilder actual = new StringBuilder(),
                expected = new StringBuilder("""
                                Token{type=IDENTIFIER, value='t'}
                                Token{type=ASSIGNMENT, value='='}
                                Token{type=IDENTIFIER, value='t'}
                                Token{type=OPERATOR, value='+'}
                                Token{type=NUMBER, value='1'}
                                """);
        for(Token token:tokens){
            actual.append(token).append("\n");
        }
        assertEquals(0, actual.compareTo(expected));
        System.out.println(actual);
    }
    @Test
    void getTokenTest2() {
        Tokenizer tokenizer = new ConstructionPlanTokenizer("done t + = 9  ( ) { }");
        List<Token> tokens = tokenizer.getTokens();
        StringBuilder actual = new StringBuilder(),
                expected = new StringBuilder("""
                                Token{type=KEYWORD, value='done'}
                                Token{type=IDENTIFIER, value='t'}
                                Token{type=OPERATOR, value='+'}
                                Token{type=ASSIGNMENT, value='='}
                                Token{type=NUMBER, value='9'}
                                Token{type=PARENTHESIS, value='('}
                                Token{type=PARENTHESIS, value=')'}
                                Token{type=BLOCK_START, value='{'}
                                Token{type=BLOCK_END, value='}'}
                                """);
        for(Token token:tokens){
            actual.append(token).append("\n");
        }
        assertEquals(0, actual.compareTo(expected));
    }
}