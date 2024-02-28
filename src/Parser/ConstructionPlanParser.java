package Parser;
import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.Expr;
import Interfaces.Parser;
import Interfaces.Player;
import Tokenizer.Token;
import Tokenizer.TokenType;

import java.util.*;

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
        while (currentPosition < tokens.size()) {
            parseStatement(true);
            currentPosition++;
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

    private boolean isExpression() {
        Token token = getCurrentToken();
        return token.getType() == TokenType.NUMBER
                || token.getType() == TokenType.IDENTIFIER
                || token.getType() == TokenType.PARENTHESIS
                || token.getValue().equals("opponent")
                || token.getValue().equals("nearby");
    }
    private boolean isDirection(){
        Token token = getCurrentToken();
        Set<String> Dir= Keywords.getDirection();
        return Dir.contains(token.getValue());
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

    private  boolean isBlockStart() {
        Token token = getCurrentToken();
        return token.getType() == TokenType.BLOCK_START && token.getValue().equals("{");
    }
    private boolean isBlockEnd() {
        Token token = getCurrentToken();
        return token.getType() == TokenType.BLOCK_END && token.getValue().equals("}");
    }

    private void parseStatement(boolean enable) throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        TokenType type = token.getType();
        TokenType keyword = TokenType.KEYWORD;
        String value = token.getValue();
        Set<String> commandSet = Keywords.getCommand();
        /**
         * parse to the command
         */
        if(token.getType() == TokenType.IDENTIFIER
                || (type == keyword && commandSet.contains(value)) ){ // equivalent to parser command
            if(token.getType() == TokenType.IDENTIFIER)
                parseAssignment(enable);
            else
                parseAction(enable);
        }else if(token.getType() == TokenType.BLOCK_START){
            parseBlockStatement(enable);
        }else if(token.getType() == TokenType.KEYWORD){
            if(token.getValue().equals("if")){
                parseIfStatement(enable);
            }else if(token.getValue().equals("while")){
                parseWhileStatement(enable);
            }else throw new SyntaxError(currentPosition+token.getValue()+"Unknown keyword");
        }
    }
    private void parseWhileStatement(boolean enable) throws SyntaxError, EvaluationError {
        boolean statement;
        if(hasNextToken()) advance();
        else throw new SyntaxError(currentPosition+"Expected next token");
        Token token = getCurrentToken();
        if(token.getType() == TokenType.PARENTHESIS){
            if(expect("(")){
                if(hasNextToken()) advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Missing expression");
                Expr expr = parseExpression(enable);
                statement = expr.eval(playerVariables) >= 1;
                if(hasNextToken())
                    if(peek(")"))
                        advance();
                    else throw new SyntaxError(currentPosition+token.getValue()+"Expected ending parentheses");
                if(hasNextToken())
                    advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Expected statement");
                int startPosition = currentPosition;
                for(int i = 0; (i<10000)&&statement;i++){
                    currentPosition = startPosition;
                    parseStatement(statement&&enable);
                    statement = expr.eval(playerVariables) >= 1;
                }
            }
        }

    }
    private void parseIfStatement(boolean enable) throws SyntaxError, EvaluationError {
        boolean statement;
        if(hasNextToken()) advance();
        else throw new SyntaxError(currentPosition+"Expected next token");
        Token token = getCurrentToken();
        if(token.getType() == TokenType.PARENTHESIS){
            if(expect("(")){
                if(hasNextToken()) advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Missing expression");
                Expr expr = parseExpression(enable);
                statement = expr.eval(playerVariables) >= 1;
                if(hasNextToken())
                    if(peek(")"))
                        advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Expected ending parentheses");
                if(hasNextToken())
                    if(peek("then"))
                            advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Expected then keyword");
                if(hasNextToken())
                    advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Expected statement");
                parseStatement(statement&&enable);
                if(hasNextToken())
                    if(peek("else"))
                        advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Expected else keyword");
                if(hasNextToken())
                    advance();
                else throw new SyntaxError(currentPosition+token.getValue()+"Expected statement");
                parseStatement(!statement&&enable);
            }else throw new SyntaxError(currentPosition+token.getValue()+"Expected opening parenthesis");
        }
    }
    private void parseBlockStatement(boolean enable) throws SyntaxError, EvaluationError {
        if(isBlockStart()){
            if(hasNextToken()) advance();
            else throw new SyntaxError(currentPosition+"Expected next token");
            for(int i = 0;i<10000&&!expect("}");i++){
                parseStatement(enable);
                advance();
            }
        }
    }
    private void parseAction(boolean enable) throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        if(token.getType() == TokenType.KEYWORD) {
            switch (token.getValue()) {
                case "done":
                    if(enable)player.done();
                    break;
                case "relocate":
                    if(enable)player.relocate();
                    break;
                case "move":    //Parser MoveCommand
                    if(hasNextToken()){
                        advance();
                        token = getCurrentToken();
                        if(isDirection()){
                            if(enable) player.move(token.getValue());
                        } else throw new SyntaxError(currentPosition+token.getValue()+"Invalid direction");
                    }else throw new SyntaxError(currentPosition+token.getValue()+"Expect next Token");
                    break;
                case "invest":
                    if(hasNextToken()){
                        advance();
                        Expr expr = parseExpression(enable);
                        if(enable)player.invest(expr.eval(playerVariables));
                    }
                    break;
                case "collect":
                    if(hasNextToken()){
                        advance();
                        Expr expr = parseExpression(enable);
                        if(enable)player.collect(expr.eval(playerVariables));
                    }
                    break;
                case "shoot":
                    if(hasNextToken()){
                        advance();
                        if(isDirection()){
                            token=getCurrentToken();
                            String dir = token.getValue();
                            if(hasNextToken()) {
                                advance();
                                Expr expr = parseExpression(enable);
                                if(enable)player.shoot(dir, expr.eval(playerVariables));
                            }else throw new SyntaxError(currentPosition+token.getValue()+"Expect expression");

                        }else throw new SyntaxError(currentPosition+token.getValue()+"Invalid direction");
                    }else throw new SyntaxError(currentPosition+token.getValue()+"Expect next Token");
                    break;

            }
        }
    }
    private void parseAssignment(boolean enable) throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        if(token.getType() == TokenType.IDENTIFIER) {
            //TODO:implement
            String name = token.getValue();
            advance();
            expect("=");
            advance();
            Expr expr = parseExpression(enable);
            if(enable){
                if(!playerVariables.containsKey(name))
                    playerVariables.put(name, 0);
                playerVariables.put(name, expr.eval(playerVariables));
            }
        }
    }
    private Expr parseExpression(boolean enable) throws SyntaxError, EvaluationError {
        if(!isExpression())
            throw new SyntaxError(getCurrentToken().getValue()+": Invalid expression");
        Expr v = parseTerm(enable);
        if (hasNextToken()) {
            while (peek("+") ||peek("-")){
                advance();
                if (expect("+")) {
                    advance();
                    Expr y = parseTerm(enable);
                    v = new BinaryArithExpr(v, "+", y);
                } else if (expect("-")) {
                    advance();
                    v = new BinaryArithExpr(v, "-", parseTerm(enable));
                }
            }
        }
        return v;
    }
    private Expr parseTerm(boolean enable) throws SyntaxError, EvaluationError {
        Expr v = parseFactor(enable);
        if(hasNextToken()){
            while (peek("*") || peek("/") || peek("%")) {
                advance();
                if (expect("*")) {
                    advance();
                    v = new BinaryArithExpr(v, "*", parseFactor(enable));
                } else if (expect("/")) {
                    advance();
                    v = new BinaryArithExpr(v, "/", parseFactor(enable));
                } else if (expect("%")) {
                    advance();
                    v = new BinaryArithExpr(v, "%", parseFactor(enable));
                }
            }
        }
        return v;
    }
    private Expr parseFactor(boolean enable) throws SyntaxError, EvaluationError {
        Expr v = parsePower(enable);
        if(hasNextToken()){
            if (peek("^")) {
                advance(2);
                v = new BinaryArithExpr(v, "^", parseFactor(enable));
            }
        }
        return v;
    }
    private Expr parsePower(boolean enable) throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        if (token.getType() == TokenType.NUMBER) {
            if(enable){
                int value = Integer.parseInt(getCurrentToken().getValue());
                return new IntLiteral(value);
            }else
                return new IntLiteral(1);
        } else if (token.getType() == TokenType.IDENTIFIER) {
            if(enable){
                if (!playerVariables.containsKey(getCurrentToken().getValue()))
                    playerVariables.put(getCurrentToken().getValue(), 0);
                return new Variable(getCurrentToken().getValue());
            }   else
                return new IntLiteral(1);
        } else if (expect("(")) {
            advance();
            Expr expr = parseExpression(enable);
            advance();
            if(!expect(")"))
                throw new SyntaxError(currentPosition+token.getValue()+"Expected )");
            return expr;
        } else
            if(!(Keywords.getInfo().contains(getCurrentToken().getValue())))
                throw new SyntaxError(currentPosition+token.getValue()+"Expected a number or variable or keyword");
            return parseInfoExpr(enable);
    }
    private Expr parseInfoExpr(boolean enable) throws SyntaxError {
        if(expect( "opponent")){
//            if(enable)
            //call opponent
                return player.opponent();
        }else {
            if(hasNextToken())
                advance();
            else
                throw new SyntaxError(currentPosition+"Expected an expression");
            Token token = getCurrentToken();
            if(Keywords.getDirection().contains(token.getValue())){
                String dir = token.getValue();
//                if(enable)
                    return player.nearby(dir);
            }else throw new SyntaxError(currentPosition+token.getValue()+"Expect direction");
        }
//        return null;
    }
}
