package Interfaces;
import Tokenizer.Token;
import Tokenizer.TokenType;

public interface Tokenizer {
    public boolean hasNextToken();
    public Token peek();
    public Token consume();
    public boolean peek(TokenType type);
    public void consume(TokenType type);
}
