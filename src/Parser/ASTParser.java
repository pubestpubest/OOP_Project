package Parser;
import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.Expr;
import Interfaces.Parser;
import Interfaces.Player;
import Interfaces.Statement;
import Parser.AST.*;
import Tokenizer.Token;
import Tokenizer.TokenType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
public class ASTParser implements Parser {
    private int currentPosition = 0;
    private List<Token> tokens = new ArrayList<Token>();

    //Singleton constructor
    private static final ASTParser instance = new ASTParser();
    public static ASTParser getInstance() {return instance;}
    private ASTParser() {}

    private Token getCurrentToken(){
        return tokens.get(currentPosition);
    }
    private void advance() throws SyntaxError {
        if(hasNextToken()) {
            currentPosition++;
        }
        else throw new SyntaxError("At "+tokens.get(currentPosition)+" : Expected next token at position " + currentPosition);
    }
    private boolean hasNextToken(){
        return currentPosition < tokens.size()-1;
    }
    private boolean expect(String value) {
        Token token = tokens.get(currentPosition);
        return token.value().equals(value);
    }
    private boolean isExpression() {
        Token token = getCurrentToken();
        return token.type() == TokenType.NUMBER
                || token.type() == TokenType.IDENTIFIER
                || token.type() == TokenType.PARENTHESIS
                || token.value().equals("opponent")
                || token.value().equals("nearby");
    }
    private boolean isDirection(){
        Token token = getCurrentToken();
        return Keywords.getDirection().contains(token.value());
    }
    private boolean peek(String exp){
        if(hasNextToken()){
            Token token = tokens.get(currentPosition+1);
            return token.value().equals(exp);
        }else
            return false;
    }

    @Override
    public Plan parse(List<Token> tokens) throws EvaluationError, SyntaxError {
        this.tokens = new ArrayList<>(tokens);  // deep copy
        List<Statement> plan = new ArrayList<Statement>();
        while ( currentPosition < tokens.size() ) {
            Statement statement = parseStatement();
            plan.add(statement);
            currentPosition++;
        }
        return new Plan(plan);
    }
    private Statement parseStatement() throws SyntaxError {
        Token token = getCurrentToken();
        TokenType type = token.type();
        String value = token.value();
        if(type == TokenType.IDENTIFIER
                || (type == TokenType.KEYWORD && Keywords.getCommand().contains(value)))
            if(type == TokenType.IDENTIFIER)
                return parseAssignment();
            else
                return parseAction();
        else if(type == TokenType.BLOCK_START)
            return parseBlockStatement();
        else if(type == TokenType.KEYWORD)
            if(value.equals("if"))
                return parseIfStatement();
            else if(value.equals("while"))
                return parseWhileStatement();
            else
                throw new SyntaxError(currentPosition+token.value()+"Expected a number or variable or keyword");
        throw new SyntaxError(currentPosition+token.value());
    }
    private Statement parseWhileStatement() throws SyntaxError {
        Expr condition = null;
        Statement body = null;
        if(!expect("while"))    throw new SyntaxError(currentPosition+getCurrentToken().value()+"expected while ???");
        advance();
        if(expect("("))advance();
        condition = parseExpression();
        advance();
        if(expect(")")) advance(); else throw new SyntaxError(currentPosition+getCurrentToken().value()+"Expected )");
        body = parseStatement();
        return new WhileStatement(condition,body);
    }
    private Statement parseIfStatement() throws SyntaxError {
        Expr condition = null;
        Statement thenBody = null,
                    elseBody = null;
        advance();
        Token token = getCurrentToken();
        if(token.type() == TokenType.PARENTHESIS){
            if(expect("(")){
                advance();
                condition = parseExpression();
                advance();
                if(expect(")")){
                    advance();
                }else throw new SyntaxError(currentPosition+token.value()+"Expected )");
            }
            if(expect("then")) advance();
            thenBody = parseStatement();
            advance();
            if(expect("else"))advance();
            elseBody = parseStatement();
        }
        return new IfStatement(condition,thenBody,elseBody);
    }
    private Statement parseBlockStatement() throws SyntaxError {
        advance();
        List<Statement> statements = new ArrayList<Statement>();
        for(int i = 0;i<10000&&!expect("}");i++){
            statements.add(parseStatement());
            advance();
        }
        return new BlockStatement(statements);
    }
    private Statement parseAction() throws SyntaxError {
        Token token = getCurrentToken();
        if(token.type() == TokenType.KEYWORD){
            switch(token.value()){
                case "done":
                    return new CommandStatement("done");
                case "relocate":
                    return new CommandStatement("relocate");
                case "move":
                    advance();
                    token = getCurrentToken();
                    if(isDirection())
                        return new MoveStatement(token.value());
                    else throw new SyntaxError(currentPosition+token.value()+"Expected Direction");
                case "shoot":
                    advance();
                    if(!isDirection())  throw new SyntaxError(currentPosition+token.value()+"Expected Direction");
                    token = getCurrentToken();
                    String dir = token.value();
                    advance();
                    if(!isExpression()) throw new SyntaxError(currentPosition+token.value()+"Expected Expression");
                    Expr expr = parseExpression();
                    return new AttackStatement(dir,expr);
                case "collect":
                case "invest":
                    token = getCurrentToken();
                    String command = token.value();
                    advance();
                    if(!isExpression()) throw new SyntaxError(currentPosition+token.value()+"Expected Expression");
                    Expr expression = parseExpression();
                    return new RegionStatement(command,expression);
                default:
                    throw new SyntaxError(currentPosition+token.value()+"Expected a number or variable or keyword");
            }
        }
        throw new SyntaxError(currentPosition+token.value()+"unknown action command");
    }
    private Statement parseAssignment() throws SyntaxError {
        Token token = getCurrentToken();
        if(token.type() == TokenType.IDENTIFIER){
            String name = token.value();
            advance();
            if(!expect("="))
                throw new SyntaxError(currentPosition+token.value()+"Expected =");
            advance();
            Expr expr = parseExpression();
//            advance();
            return new AssignmentStatement(name, expr);
        }else throw new SyntaxError(currentPosition+" expect Identifier");
    }
    private Expr parseExpression() throws SyntaxError {
        if (!isExpression())
            throw new SyntaxError(getCurrentToken().value() + ": Invalid expression");
        Expr v = parseTerm();
        if (hasNextToken()) {
            while (peek("+") || peek("-")) {
                advance(); // Move to the operator token.
                if (expect("+")) {
                    advance(); // Move past the '+' operator.
                    v = new BinaryArithExpr(v, "+", parseTerm()); // Combine the terms using the '+' operator.
                } else if (expect("-")) {
                    advance(); // Move past the '-' operator.
                    v = new BinaryArithExpr(v, "-", parseTerm()); // Combine the terms using the '-' operator.
                }
            }
        }
        return v;
    }
    private Expr parseTerm() throws SyntaxError {
        Expr v = parseFactor();
        if (hasNextToken()) {
            while (peek("*") || peek("/") || peek("%")) {
                advance(); // Move to the operator token.
                if (expect("*")) {
                    advance(); // Move past the '*' operator.
                    v = new BinaryArithExpr(v, "*", parseFactor()); // Combine the terms using the '*' operator.
                } else if (expect("/")) {
                    advance(); // Move past the '/' operator.
                    v = new BinaryArithExpr(v, "/", parseFactor()); // Combine the terms using the '/' operator.
                } else if (expect("%")) {
                    advance(); // Move past the '%' operator.
                    v = new BinaryArithExpr(v, "%", parseFactor()); // Combine the terms using the '%' operator.
                }
            }
        }
        return v;
    }
    private Expr parseFactor() throws SyntaxError {
        Expr v = parsePower();
        if(hasNextToken() && peek("^")){
            advance(); // Move to the operator token.
            advance(); // Move past the '^' operator.
            v = new BinaryArithExpr(v, "^", parsePower()); // Combine the terms using the '^' operator.
        }
        return v;
    }
    private Expr parsePower() throws SyntaxError {
        Token token = getCurrentToken();
        if(token.type() == TokenType.NUMBER) {
            return new IntLiteral(Integer.parseInt(token.value()));
        }else if(token.type() == TokenType.IDENTIFIER){
            return new Variable(token.value());
        }else if(expect("(")){
            advance();
            Expr v = parseExpression();
            advance();
            if(!expect(")"))
                throw new SyntaxError(currentPosition+token.value()+"Expected )");
            return v;
        }else{
            if(!(Keywords.getInfo().contains(token.value()))){
                throw new SyntaxError(currentPosition+token.value()+"Expected a number or variable or keyword");
            }
            return parseInfoExpr();
        }
    }
    private Expr parseInfoExpr() throws SyntaxError {
        if(expect("opponent")){
            return new Opponent();
        }else{
            advance();
            Token token = getCurrentToken();
            if(Keywords.getDirection().contains(token.value())){
                String direction = token.value();
                return new Nearby(direction);
            }else throw new SyntaxError(currentPosition+token.value());
        }
    }
}
