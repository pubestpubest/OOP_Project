package Tokenizer;
/**
 * A class representing a token in the input code.
 *
 * @author Pubest
 */
public class Token {
    private final TokenType type;
    private final String value;
    /**
     * Constructs a new Token with the specified type and value.
     *
     * @param type The type of the token.
     * @param value The value of the token.
     */
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
    /**
     * Returns the type of the token.
     *
     * @return The type of the token.
     */
    public TokenType getType() {
        return type;
    }
    /**
     * Returns the value of the token.
     *
     * @return The value of the token.
     */
    public String getValue() { return value; }
    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
