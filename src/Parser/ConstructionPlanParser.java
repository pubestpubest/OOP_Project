package Parser;
import Interfaces.Parser;
import Tokenizer.*;
import java.util.List;
/**
 * This class is responsible for parsing the construction plan
 * given by the user. It uses a tokenizer to break the plan into
 * individual tokens, and then uses a series of methods to parse
 * these tokens into construction plan to be executed.
 */
public class ConstructionPlanParser implements Parser {

    /**
     * The list of tokens that make up the construction plan.
     */
    private List<Token> tokens;

    /**
     * The index of the current token in the list.
     */
    private int currentPosition;

    /**
     * Creates a new ConstructionPlanParser that will parse the given list of tokens.
     *
     * @param tokens The list of tokens to parse
     */
    public ConstructionPlanParser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    /**
     * Parses the construction plan and returns the result.
     */
    public void parse() {
        parsePlan();
    }

    /**
     * Returns the current token in the list without advancing the position.
     *
     * @return The current token, or null if there are no more tokens
     */
    private Token getCurrentToken() {
        if (currentPosition < tokens.size()) {
            return tokens.get(currentPosition);
        }
        return null;
    }

    /**
     * Parses the entire construction plan, one statement at a time.
     */
    private void parsePlan() {
        while (currentPosition < tokens.size()) {
            parseStatement();
        }
    }

    /**
     * Parses a single statement in the construction plan.
     */
    private void parseStatement() {
        Token token = getCurrentToken();
        switch (token.getType()) {
            case IDENTIFIER:
                // Handle an identifier
                break;
            default:
                // Handle an unknown token
                break;
        }
    }

    /**
     * Parses a command in the construction plan.
     */
    private void parseCommand() {
        // TODO: Implement me
    }

    /**
     * Parses an assignment in the construction plan.
     */
    private void parseAssignment() {
        // TODO: Implement me
    }

    /**
     * Parses an action in the construction plan.
     */
    private void parseAction() {
        // TODO: Implement me
    }

    /**
     * Parses a move in the construction plan.
     */
    private void parseMove() {
        // TODO: Implement me
    }

    /**
     * Parses a region in the construction plan.
     */
    private void parseRegion() {
        // TODO: Implement me
    }

    /**
     * Parses an attack in the construction plan.
     */
    private void parseAttack() {
        // TODO: Implement me
    }

    /**
     * Parses a direction in the construction plan.
     */
    private void parseDirection() {
        // TODO: Implement me
    }

    /**
     * Parses a block in the construction plan.
     */
    private void parseBlock() {
        // TODO: Implement me
    }

    /**
     * Parses an if statement in the construction plan.
     */
    private void parseIf() {
        // TODO: Implement me
    }

    /**
     * Parses a while statement in the construction plan.
     */
    private void parseWhile() {
        // TODO: Implement me
    }

    /**
     * Parses an expression in the construction plan.
     */
    private void parseExpr() {
        // TODO: Implement me
    }

    /**
     * Parses a term in the construction plan.
     */
    private void parseTerm() {
        // TODO: Implement me
    }

    /**
     * Parses a factor in the construction plan.
     */
    private void parseFactor() {
        // TODO: Implement me
    }

    /**
     * Parses an information expression in the construction plan.
     */
    private void parseInfoExpr() {
        // TODO: Implement me
    }

}