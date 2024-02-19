package Parser;
import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.Expr;
import Interfaces.Parser;
import Interfaces.Player;
import Tokenizer.Token;
import Tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ConstructionPlanParser implements Parser {
    List<Token> tokens = new ArrayList<Token>();
    Player player;
    Map<String, Integer> playerVariables;
    int currentPosition = 0;


    public void parse(List<Token> tokens, Player player) throws EvaluationError, SyntaxError {
        this.tokens = new ArrayList<>(tokens);  // deep copy
        this.player = player;   //Alias
        this.playerVariables = player.getVariables(); //Alias
        currentPosition = 0;
        while (hasNextToken()) {
            parseStatement();

        }
    }

    private boolean hasNextToken() {
        return currentPosition<tokens.size()-1;
    }
    private void advance() throws SyntaxError {
        if(hasNextToken())  currentPosition++;
        else throw new SyntaxError("Expected next token");
    }
    private boolean peek(String exp){
        if(hasNextToken()){
            Token token = tokens.get(currentPosition+1);
            return token.getValue().equals(exp);
        }else
            return false;
    }

    private boolean expect(String value) {
        Token token = tokens.get(currentPosition);
        return token.getValue().equals(value);
    }

    private void advance(int position) throws EvaluationError {
        if(currentPosition+position< tokens.size())
            currentPosition+=position;
        else
            throw new EvaluationError("Position out of bounds");
    }

    private Token getNextToken(){
        if(hasNextToken())
            return tokens.get(currentPosition+1);
        else
            throw new NullPointerException();
    }

    private Token getCurrentToken() {
        return tokens.get(currentPosition);
    }

    private void parseStatement() throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        if(token.getType() == TokenType.IDENTIFIER)
            parseAssignment();
        if(hasNextToken())advance();
    }
    private void parseAssignment() throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        if(token.getType() == TokenType.IDENTIFIER) {
            //TODO:implement
            String name = token.getValue();
            advance();
            expect("=");
            advance();
            Expr expr = parseExpression();
            if(!playerVariables.containsKey(name))
                playerVariables.put(name, 0);
            playerVariables.put(name, expr.eval(playerVariables));
        }
    }
    private Expr parseExpression() throws SyntaxError, EvaluationError {
        Expr v = parseTerm();
        if (hasNextToken()) {
            while (peek("+") ||peek("-")){
                advance();
                if (expect("+")) {
                    advance();
                    Expr y = parseTerm();
                    v = new BinaryArithExpr(v, "+", y);
                } else if (expect("-")) {
                    advance();
                    v = new BinaryArithExpr(v, "-", parseTerm());
                }
            }
        }
        return v;
    }
    private Expr parseTerm() throws SyntaxError, EvaluationError {
        Expr v = parseFactor();
        if(hasNextToken()){
            while (peek("*") || peek("/") || peek("%")) {
                advance();
                if (expect("*")) {
                    advance();
                    v = new BinaryArithExpr(v, "*", parseFactor());
                } else if (expect("/")) {
                    advance();
                    v = new BinaryArithExpr(v, "/", parseFactor());
                } else if (expect("%")) {
                    advance();
                    v = new BinaryArithExpr(v, "%", parseFactor());
                }
            }
        }
        return v;
    }


    private Expr parseFactor() throws SyntaxError, EvaluationError {
        Expr v = parsePower();
        if(hasNextToken()){
            if (peek("^")) {
                advance(2);
                v = new BinaryArithExpr(v, "^", parseFactor());
            }
        }
        return v;
    }
    private Expr parsePower() throws SyntaxError, EvaluationError {
        if (getCurrentToken().getType() == TokenType.NUMBER) {
            int value = Integer.parseInt(getCurrentToken().getValue());
//            advance();
            return new IntLiteral(value);
        } else if (getCurrentToken().getType() == TokenType.IDENTIFIER) {
            if (!playerVariables.containsKey(getCurrentToken().getValue()))
                playerVariables.put(getCurrentToken().getValue(), 0);
            return new Variable(getCurrentToken().getValue());
        } else if (expect("(")) {
            advance();
            Expr expr = parseExpression();
            advance();
            if(!expect(")"))
                throw new SyntaxError("Expected )");
            return expr;
        } else
            if(!(Keywords.getInfo().contains(getCurrentToken().getValue())))
                throw new SyntaxError("Expected a number or variable or keyword");
            return parseInfoExpr();
    }
    private Expr parseInfoExpr() throws SyntaxError {
        if(expect( "opponent")){
            //call opponent
            /*
            Expr expr = player.opponent();
            return expr;
             */
            return null;
        }else {
            if(hasNextToken())
                advance();
            else
                throw new SyntaxError("Expected an expression");
            Token token = getCurrentToken();
            if(Keywords.getDirection().contains(token.getValue())){
                advance();
                String dir = token.getValue();
                /*
                call
                Expr expr = player.nearby(dir);
                return expr;
                 */
            }else throw new SyntaxError("Expect direction");
        }
        //call
        return null;
    }
}
