package Tokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Interfaces.*;
/**
 * This class is responsible for tokenize the input stream and returns
 * the result in a list of tokens
 */
public class ConstructionPlanTokenizer implements Tokenizer{
    private final List<Token> tokens;
    /**
     * Construct a new tokenizer instance with the given string and
     * tokenize the input stream
     *
     * @param input the input stream
     */
    public ConstructionPlanTokenizer(String input) {
        this.tokens = tokenizer(input);
    }
    /**
     * Getter for tokens
     * @return list of tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }
    /**
     * Tokenizes a construction plan input string into a list of tokens.
     *
     * @param input the input string
     * @return a list of tokens
     */
    private List<Token> tokenizer(String input) {
        List<Token> tokens = new ArrayList<>();
        String[] lines = input.split("\n");
        for(String line : lines) {
            line = line.trim();
            if(line.isEmpty()) continue;
            /*
             * 1.[a-zA-Z_][a-zA-Z_0-9]* : Alphabet follow by optional underscore and more alphanumeric character
             * 2.\\d+ : One or more digits
             * 3.\\S : Any non-alphanumeric character
             */
            Matcher matcher = Pattern.compile("(^#.*$|[a-zA-Z_][a-zA-Z_0-9]*|\\d+|\\S)").matcher(line);
            while(matcher.find()) {
                String value = matcher.group();
                TokenType type = getTokenType(value);
                tokens.add(new Token(type, value));
            }
        }
        return tokens;
    }
    /**
     * Returns the type of the specified token.
     *
     * @param tokenValue the value of the token
     * @return the type of the specified token
     */
    private TokenType getTokenType(String tokenValue){
        if(tokenValue.matches("[a-zA-Z_][a-zA-Z_0-9]*")){
            if(isKeyWord(tokenValue))
                return TokenType.KEYWORD;
            else
                return TokenType.IDENTIFIER;
        }else if(tokenValue.matches("[-+*/%^]"))
            return TokenType.OPERATOR;
        else if(tokenValue.matches("\\d+"))
            return TokenType.NUMBER;
        else if(tokenValue.matches("[()]"))
            return TokenType.PARENTHESIS;
        else if(tokenValue.matches("\\{"))
            return TokenType.BLOCK_START;
        else if(tokenValue.matches("}"))
            return TokenType.BLOCK_END;
        else if(tokenValue.matches("^#.*$"))
            return TokenType.COMMENT;
        else return TokenType.ERROR;
    }
    /**
     * Returns whether the specified value is a reserved keyword in the game.
     *
     * @param value the value to check
     * @return true if the specified value is a reserved keyword, false otherwise
     */
    private boolean isKeyWord(String value){
        List<String> reservedWords = List.of(
                "collect","done","down","downleft","downright",
                "else","if","invest","move","nearby","opponent","relocate",
                "shoot","then","up","upleft","upright","while"
        );
        return reservedWords.contains(value);
    }
}
