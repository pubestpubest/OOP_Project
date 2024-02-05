package Tokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Interfaces.*;
public class ConstructionPlanTokenizer implements Tokenizer{
    private final List<Token> tokens;
    private int currentPosition;
    public ConstructionPlanTokenizer(String input) {
        this.tokens = tokenizer(input);
        this.currentPosition = 0;
    }
    /**
     * Returns the next token in the input sequence.
     *
     * @return the next token in the input sequence, or null if no more tokens are available
     */
    @Override
    public Token getNextToken() {
        if(currentPosition < tokens.size())
            return tokens.get(currentPosition++);
        return null;
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
            if(line.startsWith("#")||line.isEmpty()) continue;
            /*
             * 1.[a-zA-Z_][a-zA-Z_0-9]* : Alphabet follow by optional underscore and more alphanumeric character
             * 2.\\d+ : One or more digits
             * 3.\\S : Any non-alphanumeric character
             */
            Matcher matcher = Pattern.compile("([a-zA-Z_][a-zA-Z_0-9]*|\\d+|\\S)").matcher(line);
            while(matcher.find()) {
                String value = matcher.group().toUpperCase();
                TokenType type = getTokenType(value);
                tokens.add(new Token(type, value)); //Deep copy
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
