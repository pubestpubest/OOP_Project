package Parser;
import Exceptions.EvaluationError;
import Exceptions.SynataxError;
import Interfaces.Expr;
import Interfaces.Parser;
import Interfaces.Player;
import Tokenizer.*;

import java.util.*;
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
    private Player player;
    private Map<String, Integer> playerVariables;
    /**
     * The index of the current token in the list.
     */
    private int currentPosition;

    /**
     * Creates a new ConstructionPlanParser that will parse the given list of tokens.
     */
    public ConstructionPlanParser() {
        this.currentPosition = 0;
    }

    /**
     * Parses the construction plan and returns the result.
     */
    public void parse(List<Token> tokens, Player player) throws EvaluationError, SynataxError {
        this.player = player;
        this.playerVariables = player.getVariables();
        this.tokens = new ArrayList<Token>(tokens);
        parsePlan();
    }

    private boolean hasNextToken(){
        return currentPosition < tokens.size();
    }

    /**
     * Returns the current token in the list without advancing the position.
     *
     * @return The current token, or null if there are no more tokens
     */
    private Token getCurrentToken() {
        assert tokens.get(currentPosition) == null;
        return tokens.get(currentPosition);
    }
    private void advance(){
        currentPosition++;
    }
//
//    /**
//     * Returns the next token in the list without advancing the position.
//     *
//     * @return The next token, or null if there are no more tokens
//     */
//    private Token peek(){return tokens.get(currentPosition+1);}
//    /**
//     * Returns the next token in the list without advancing the position.
//     *
//     * @return The next token, or null if there are no more tokens
//     */
//    private Token consume(){return tokens.get(++currentPosition);}
//
//    private boolean peek(Token expectToken){return tokens.get(currentPosition+1).equals(expectToken);}
//    private boolean peek(String s){return Objects.equals(tokens.get(currentPosition + 1).getValue(), s);}
//    private boolean consume(Token expectToken){return tokens.get(++currentPosition).equals(expectToken);}

    /**
     * Parses the entire construction plan, one statement at a time.
     */
    private void parsePlan() throws EvaluationError, SynataxError {
        while (currentPosition < tokens.size()) {
            parseStatement();
        }
    }

    /**
     * Parses a single statement in the construction plan.
     */
    private void parseStatement() throws EvaluationError, SynataxError {
        Token token = getCurrentToken();
        assert token != null;
        if(token.getType() == TokenType.IDENTIFIER
            ) {
            //TODO:implement
            parseAssignment();
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
    private void parseAssignment() throws EvaluationError,SynataxError {
        // TODO: Implement me
        String identifier = getCurrentToken().getValue();
        advance();
        if(!(getCurrentToken().getType() == TokenType.ASSIGNMENT))
            throw new SynataxError("Expected =");
        advance();
        Expr e = parseExpr();
        int value = e.eval(playerVariables);
        if(!playerVariables.containsKey(identifier))
            playerVariables.put(identifier, 0);
        else
            playerVariables.put(identifier, value);
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
    private Expr parseExpr() {
        // TODO: Implement me
        Expr v = parseTerm();
        if(hasNextToken()){
            advance();
            while(Objects.equals(getCurrentToken().getValue(), "+") ||
                    Objects.equals(getCurrentToken().getValue(), "-")){
                    if(Objects.equals(getCurrentToken().getValue(), "+")){
                        consume();
                        v = new BinaryArithExpr(v, "+", parseTerm());
                    }else if(peek("-")){
                        consume();
                        v = new BinaryArithExpr(v, "-", parseTerm());
                    }
                }
            }
        }
        return v;
    }

    /**
     * Parses a term in the construction plan.
     */
    private Expr parseTerm() {
        // TODO: Implement me
        Expr v = parseFactor();
        while(peek("*")||peek("/")||peek("%")){
            if(hasNextToken()){
                if(peek("*")){
                    consume();
                    v = new BinaryArithExpr(v, "*", parseFactor());
                }else if(peek("/")){
                    consume();
                    v = new BinaryArithExpr(v, "/", parseFactor());
                }else if(peek("%")){
                    consume();
                    v = new BinaryArithExpr(v, "%", parseFactor());
                }
            }
        }
        return v;
    }

    /**
     * Parses a factor in the construction plan.
     */
    private Expr parseFactor() {
        // TODO: Implement me
        Expr v = parsePower();
        if(peek("^")) {
            consume();
            v = new BinaryArithExpr(v, "^", parseFactor());
        }
        return v;
    }

    private Expr parsePower() {
        // TODO: Implement me
        if(getCurrentToken().getType() == TokenType.NUMBER){
            int value = Integer.parseInt(getCurrentToken().getValue());
            return new IntLiteral(value);
        } else if(getCurrentToken().getType()==TokenType.IDENTIFIER) {
            if(!playerVariables.containsKey(getCurrentToken().getValue()))
                playerVariables.put(getCurrentToken().getValue(),0);
            return new Variable(getCurrentToken().getValue());
        } else if(peek("(")) {
            consume(new Token(TokenType.PARENTHESIS,"("));
            Expr expr = parseExpr();
            consume(new Token(TokenType.PARENTHESIS,")"));
        }else
            parseInfoExpr();

        return null;
    }

    /**
     * Parses an information expression in the construction plan.
     */
    private void parseInfoExpr() {
        // TODO: Implement me

    }

}