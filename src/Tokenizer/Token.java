package Tokenizer;
import java.util.Objects;
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
    /**
     * Returns true if the specified object is equal to this Token.
     *
     * @param o the object to be compared for equality with this Token
     * @return true if the specified object is equal to this Token, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type && Objects.equals(value, token.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
