package Interfaces;
import Tokenizer.Token;
import Tokenizer.TokenType;

import java.util.List;

/**
 * Interface for a tokenizer.
 *
 * @author Pubest
 */
public interface Tokenizer {
    public List<Token> getTokens();
}
