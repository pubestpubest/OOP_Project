package Tokenizer;
import java.util.Objects;
/**
 * A class representing a token in the input code.
 *
 * @param type  The type of the token.
 * @param value The value of the token.
 * @author Pubest
 */
public record Token(TokenType type, String value) {
    /**
     * Returns the type of the token.
     *
     * @return The type of the token.
     */
    @Override
    public TokenType type() {
        return type;
    }
    /**
     * Returns the value of the token.
     *
     * @return The value of the token.
     */
    @Override
    public String value() {
        return value;
    }
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
}
