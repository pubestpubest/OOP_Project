package Parser;
import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.Expr;
import Interfaces.Parser;
import Interfaces.Player;
import Parser.AST.Plan;
import Tokenizer.Token;
import Tokenizer.TokenType;

import java.util.*;

public class ConstructionPlanParser implements Parser {
    List<Token> tokens = new ArrayList<>();
    Player player;
    Map<String, Integer> playerVariables;
    int currentPosition = 0;
    //  TODO: Fix the Special variables;Variables from the board
    //  TODO: Implement singleton constructor
    //  TODO: Testers
    /**
     * Parses a list of tokens into a sequence of statements.
     *
     * @param tokens the list of tokens to parse
     * @param player the player object that is used to access game state
     * @throws EvaluationError if an error occurs during evaluation
     * @throws SyntaxError if a syntax error is encountered
     */
    public Plan parse(List<Token> tokens) throws EvaluationError, SyntaxError {
        this.tokens = new ArrayList<>(tokens);  // deep copy
        this.player = player;   //Alias
        this.playerVariables = player.getVariables(); //Alias
        currentPosition = 0;
        while (currentPosition < tokens.size()) {
            parseStatement(true);
            currentPosition++;
        }
        return null;
    }

    /**
     * @return true if there is another token in the list of tokens, otherwise returns false.
     */
    private boolean hasNextToken() {
        return currentPosition<tokens.size()-1;
    }

    /**
     * Increments the current position of the tokenizer by one.
     * If there are no more tokens in the list, throws a SyntaxError.
     *
     * @throws SyntaxError if there are no more tokens in the list
     */
    private void advance() throws SyntaxError {
        if(hasNextToken())  currentPosition++;
        else throw new SyntaxError("Expected next token");
    }

    /**
     * Returns true if the next token in the list of tokens matches the given string, otherwise returns false.
     *
     * @param exp the string to check for
     * @return true if the next token in the list of tokens matches the given string, otherwise returns false
     */
    private boolean peek(String exp){
        if(hasNextToken()){
            Token token = tokens.get(currentPosition+1);
            return token.value().equals(exp);
        }else
            return false;
    }

    /**
     * Checks if the next token in the list of tokens is an expression.
     * An expression is defined as a number, an identifier, a parenthesis, the keyword "opponent", or the keyword "nearby".
     *
     * @return true if the next token in the list of tokens is an expression, false otherwise.
     */
    private boolean isExpression() {
        Token token = getCurrentToken();
        return token.type() == TokenType.NUMBER
                || token.type() == TokenType.IDENTIFIER
                || token.type() == TokenType.PARENTHESIS
                || token.value().equals("opponent")
                || token.value().equals("nearby");
    }

    /**
     * Returns true if the next token in the list of tokens is a direction.
     * A direction is defined as one of the eight directions: "up", "upright", "upleft", "down", "downright", "downleft".
     *
     * @return true if the next token in the list of tokens is a direction, false otherwise.
     */
    private boolean isDirection(){
        Token token = getCurrentToken();
        Set<String> Dir= Keywords.getDirection();
        return Dir.contains(token.value());
    }

    /**
     * Checks if the current token is an expected token
     * @param value the expected token
     * @return true if the current token is matching the expected token
     */
    private boolean expect(String value) {
        Token token = tokens.get(currentPosition);
        return token.value().equals(value);
    }

    /**
     * Get the current token in the list of tokens.
     *
     * @return the current token in the list of tokens
     */
    private Token getCurrentToken() {
        return tokens.get(currentPosition);
    }

    /**
     * Checks if the current position is at the start of a block '{'.
     *
     * @return true if the current position is at the start of a block, false otherwise.
     */
    private  boolean isBlockStart() {
        Token token = getCurrentToken();
        return token.type() == TokenType.BLOCK_START && token.value().equals("{");
    }

    /**
     * Parses a single statement from the current list of tokens. This method determines
     * the type of statement (e.g., assignment, action, block, conditional) based on the
     * current token and delegates to the appropriate parsing method.
     * The method supports conditional execution of statements based on the 'enable' parameter.
     * This allows for conditional parsing and execution in control flow constructs like
     * 'if' and 'while' statements without actually performing the actions if 'enable' is false.
     *
     * @param enable Flag indicating whether the statement should be executed. This is
     *               useful for conditionally executing statements based on control flow.
     * @throws SyntaxError Thrown when the syntax of the statement does not match expected patterns.
     * @throws EvaluationError Thrown when an error occurs during the evaluation of expressions within the statement.
     */
    private void parseStatement(boolean enable) throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        TokenType type = token.type();
        TokenType keyword = TokenType.KEYWORD;
        String value = token.value();
        Set<String> commandSet = Keywords.getCommand();

        // Determine the type of statement and delegate to the appropriate method.
        // Supports assignment, action commands, block statements, and control flow constructs.
        if (token.type() == TokenType.IDENTIFIER || (type == keyword && commandSet.contains(value))) {
            // For IDENTIFIER tokens, parse as an assignment. For KEYWORD tokens that are recognized commands, parse as an action.
            if (token.type() == TokenType.IDENTIFIER) {
                parseAssignment(enable);
            } else {
                parseAction(enable);
            }
        } else if (token.type() == TokenType.BLOCK_START) {
            // For BLOCK_START tokens, parse as a block statement.
            parseBlockStatement(enable);
        } else if (token.type() == TokenType.KEYWORD) {
            // For KEYWORD tokens, handle specific control flow constructs like 'if' and 'while'.
            if (token.value().equals("if")) {
                parseIfStatement(enable);
            } else if (token.value().equals("while")) {
                parseWhileStatement(enable);
            } else {
                // Throw a SyntaxError for unrecognized keywords.
                throw new SyntaxError(currentPosition + token.value() + "Unknown keyword");
            }
        }
    }

    /**
     * Parses a while loop from the current position in the list of tokens.
     *
     * @param enable Flag indicating whether the while loop should be executed. This is
     *               useful for conditionally executing while loops based on control flow.
     * @throws SyntaxError Thrown when the syntax of the while loop does not match expected patterns.
     * @throws EvaluationError Thrown when an error occurs during the evaluation of expressions within the while loop.
     */
    private void parseWhileStatement(boolean enable) throws SyntaxError, EvaluationError {
        boolean statement;
        if(hasNextToken()) advance();
        else throw new SyntaxError(currentPosition+"Expected next token");
        Token token = getCurrentToken();
        if(token.type() == TokenType.PARENTHESIS){
            if(expect("(")){
                if(hasNextToken()) advance();
                else throw new SyntaxError(currentPosition+token.value()+"Missing expression");
                Expr expr = parseExpression(enable);
                statement = expr.eval(playerVariables) >= 1;
                if(hasNextToken())
                    if(peek(")"))
                        advance();
                    else throw new SyntaxError(currentPosition+token.value()+"Expected ending parentheses");
                if(hasNextToken())
                    advance();
                else throw new SyntaxError(currentPosition+token.value()+"Expected statement");
                int startPosition = currentPosition;
                for(int i = 0; (i<10000)&&statement;i++){
                    currentPosition = startPosition;
                    parseStatement(statement&&enable);
                    statement = expr.eval(playerVariables) >= 1;
                }
            }
        }

    }

    /**
     * Parses a while loop from the current position in the list of tokens.
     *
     * @param enable Flag indicating whether the while loop should be executed. This is
     *               useful for conditionally executing while loops based on control flow.
     * @throws SyntaxError Thrown when the syntax of the while loop does not match expected patterns.
     * @throws EvaluationError Thrown when an error occurs during the evaluation of expressions within the while loop.
     */
    private void parseIfStatement(boolean enable) throws SyntaxError, EvaluationError {
        boolean statement;
        if(hasNextToken()) advance();
        else throw new SyntaxError(currentPosition+"Expected next token");
        Token token = getCurrentToken();
        if(token.type() == TokenType.PARENTHESIS){
            if(expect("(")){
                if(hasNextToken()) advance();
                else throw new SyntaxError(currentPosition+token.value()+"Missing expression");
                Expr expr = parseExpression(enable);
                statement = expr.eval(playerVariables) >= 1;
                if(hasNextToken())
                    if(peek(")"))
                        advance();
                else throw new SyntaxError(currentPosition+token.value()+"Expected ending parentheses");
                if(hasNextToken())
                    if(peek("then"))
                            advance();
                else throw new SyntaxError(currentPosition+token.value()+"Expected then keyword");
                if(hasNextToken())
                    advance();
                else throw new SyntaxError(currentPosition+token.value()+"Expected statement");
                parseStatement(statement&&enable);
                if(hasNextToken())
                    if(peek("else"))
                        advance();
                else throw new SyntaxError(currentPosition+token.value()+"Expected else keyword");
                if(hasNextToken())
                    advance();
                else throw new SyntaxError(currentPosition+token.value()+"Expected statement");
                parseStatement(!statement&&enable);
            }else throw new SyntaxError(currentPosition+token.value()+"Expected opening parenthesis");
        }
    }

    /**
     * Parses a while loop from the current position in the list of tokens,
     * which limit at 10,000 loops.
     *
     * @param enable Flag indicating whether the while loop should be executed. This is
     *               useful for conditionally executing while loops based on control flow.
     * @throws SyntaxError Thrown when the syntax of the while loop does not match expected patterns.
     * @throws EvaluationError Thrown when an error occurs during the evaluation of expressions within the while loop.
     */
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


    /**
     * Parses an action command from the current token and executes the corresponding player action.
     * This method handles various action commands such as 'done', 'relocate', 'move', 'invest',
     * 'collect', and 'shoot'. Execution of these actions is contingent upon the 'enable' parameter,
     * allowing for conditional execution based on control flow logic in the game script.
     *
     * @param enable Flag indicating whether the action should be executed. This allows for the parsing
     *               of actions without execution in certain conditions, useful in constructs like
     *               'if' statements or for dry-run parsing.
     * @throws SyntaxError Thrown when an action command does not follow the expected syntax, such as
     *                     missing direction for 'move' or 'shoot' actions, or if an unexpected token
     *                     is encountered.
     * @throws EvaluationError Thrown when an error occurs during the evaluation of any expressions
     *                         that are part of the action commands, such as 'invest' or 'collect' amounts.
     */
    private void parseAction(boolean enable) throws SyntaxError, EvaluationError{
        Token token = getCurrentToken();
        // Ensure the current token is a KEYWORD type as actions are predefined commands.
        if(token.type() == TokenType.KEYWORD) {
            // Switch on the value of the token to determine the specific action command.
            switch (token.value()) {
                case "done":
                    // Complete the player's turn or action.
                    if(enable) player.done();
                    break;
                case "relocate":
                    // Relocate the player to a new position.
                    if(enable) player.relocate();
                    break;
                case "move":
                    // Move the player in a specified direction.
                    if(hasNextToken()){
                        advance();
                        token = getCurrentToken();
                        if(isDirection()){
                            if(enable) player.move(token.value());
                        } else throw new SyntaxError(currentPosition + token.value() + "Invalid direction");
                    }else throw new SyntaxError(currentPosition + token.value() + "Expect next Token");
                    break;
                case "invest":
                    // Invest resources by evaluating an expression for the amount.
                    if(hasNextToken()){
                        advance();
                        Expr expr = parseExpression(enable);
                        if(enable) player.invest(expr.eval(playerVariables));
                    }
                    break;
                case "collect":
                    // Collect resources by evaluating an expression for the amount.
                    if(hasNextToken()){
                        advance();
                        Expr expr = parseExpression(enable);
                        if(enable) player.collect(expr.eval(playerVariables));
                    }
                    break;
                case "shoot":
                    // Shoot in a specified direction with a determined intensity or amount.
                    if(hasNextToken()){
                        advance();
                        if(isDirection()){
                            token = getCurrentToken();
                            String dir = token.value();
                            if(hasNextToken()) {
                                advance();
                                Expr expr = parseExpression(enable);
                                if(enable) player.shoot(dir, expr.eval(playerVariables));
                            }else throw new SyntaxError(currentPosition + token.value() + "Expect expression");
                        }else throw new SyntaxError(currentPosition + token.value() + "Invalid direction");
                    }else throw new SyntaxError(currentPosition + token.value() + "Expect next Token");
                    break;
            }
        }
    }

    /**
     * Parses an assignment statement from the current position in the token list. This method
     * is responsible for handling variable assignments in the form of 'identifier = expression'.
     * The method evaluates the right-hand side expression and updates the player's variable map
     * with the new value. The actual assignment is conditionally performed based on the 'enable'
     * parameter, allowing for the parsing of assignments without execution, useful in conditional
     * constructs or for dry-run parsing scenarios.
     *
     * @param enable Flag indicating whether the assignment should be executed. This can be used
     *               to parse without affecting the actual game state, especially useful in
     *               conditional logic where assignments may not always be executed.
     * @throws SyntaxError Thrown if the assignment syntax is incorrect, such as missing '='.
     * @throws EvaluationError Thrown if there's an error evaluating the expression on the right
     *                         side of the assignment.
     */
    private void parseAssignment(boolean enable) throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        // Ensure the current token is an IDENTIFIER, which is necessary for an assignment.
        if(token.type() == TokenType.IDENTIFIER) {
            String name = token.value(); // Get the variable name from the token.
            advance(); // Move past the variable name token.
            if (!expect("=")) throw new SyntaxError("Expected '=' after identifier."); // Ensure the next token is '='.
            advance(); // Move past the '=' token.
            Expr expr = parseExpression(enable); // Parse the expression on the right side of the assignment.
            if(enable){
                // If enabled, perform the assignment.
                playerVariables.put(name, expr.eval(playerVariables)); // Assign the evaluated expression to the variable.
            }
        }
    }

    /**
     * Parses an expression starting from the current token position. This method is capable of
     * parsing binary arithmetic expressions involving addition and subtraction. It constructs
     * an abstract syntax tree (Parser.AST) for the expression by recursively parsing terms and combining
     * them into more complex expressions based on the operators encountered.
     * The parsing of expressions is controlled by the 'enable' flag, which can be used to conditionally
     * parse expressions without evaluating them. This is particularly useful in scenarios where the
     * expression's evaluation is dependent on certain conditions being met (e.g., within an 'if' statement).
     *
     * @param enable Flag indicating whether the expression should be evaluated. This allows for the
     *               parsing of expressions in a conditional context without immediate evaluation.
     * @return Expr An Parser.AST node representing the parsed expression. This can be evaluated to obtain
     *              a numerical result based on the current game state and player variables.
     * @throws SyntaxError Thrown if the expression syntax is invalid, such as an unexpected token.
     * @throws EvaluationError Thrown if an error occurs during the evaluation of the expression.
     */
    private Expr parseExpression(boolean enable) throws SyntaxError, EvaluationError {
        // Check if the current token starts a valid expression.
        if (!isExpression())
            throw new SyntaxError(getCurrentToken().value() + ": Invalid expression");

        // Parse the first term of the expression.
        Expr v = parseTerm(enable);

        // Continue parsing if there are more tokens and the next token is an addition or subtraction operator.
        if (hasNextToken()) {
            while (peek("+") || peek("-")) {
                advance(); // Move to the operator token.
                if (expect("+")) {
                    advance(); // Move past the '+' operator.
                    v = new BinaryArithExpr(v, "+", parseTerm(enable)); // Combine the terms using the '+' operator.
                } else if (expect("-")) {
                    advance(); // Move past the '-' operator.
                    v = new BinaryArithExpr(v, "-", parseTerm(enable)); // Combine the terms using the '-' operator.
                }
            }
        }

        return v; // Return the constructed expression Parser.AST.
    }

    /**
     * Parses arithmetic terms involving multiplication, division, and modulus operations.
     * It builds an expression tree for terms by combining parsed factors based on operators.
     * The 'enable' flag determines if the term is actually parsed and evaluated, allowing
     * for conditional execution within the game's logic. This is useful for scenarios where
     * expressions might not always need to be evaluated, like in conditional statements.
     *
     * @param enable If true, the term is parsed and evaluated; if false, it's skipped.
     * @return Expr An expression representing the parsed term, ready for evaluation.
     * @throws SyntaxError If the syntax of the term is incorrect.
     * @throws EvaluationError If there's an issue evaluating the term.
     */
    private Expr parseTerm(boolean enable) throws SyntaxError, EvaluationError {
        // Start with parsing the first factor of the term.
        Expr v = parseFactor(enable);

        // Continue if there are more tokens and look for '*', '/', or '%' operators.
        if (hasNextToken()) {
            while (peek("*") || peek("/") || peek("%")) {
                advance(); // Move to the next token, which is the operator.
                // Depending on the operator, combine the current expression with the next factor.
                if (expect("*")) {
                    advance();
                    v = new BinaryArithExpr(v, "*", parseFactor(enable)); // For multiplication.
                } else if (expect("/")) {
                    advance();
                    v = new BinaryArithExpr(v, "/", parseFactor(enable)); // For division.
                } else if (expect("%")) {
                    advance();
                    v = new BinaryArithExpr(v, "%", parseFactor(enable)); // For modulus.
                }
            }
        }

        return v; // The constructed expression tree for the term.
    }

    /**
     * Parses factors in an expression, specifically handling exponentiation.
     * This method creates a part of the expression tree dealing with the '^' operator for powers.
     * The parsing and evaluation of the factor depend on the 'enable' flag, allowing for flexible
     * control over when expressions are evaluated, useful in conditions or loops.
     *
     * @param enable Controls whether the factor is evaluated.
     * @return Expr The parsed expression representing the factor.
     * @throws SyntaxError If there's an error in the syntax of the factor.
     * @throws EvaluationError If evaluating the factor encounters issues.
     */
    private Expr parseFactor(boolean enable) throws SyntaxError, EvaluationError {
        Expr v = parsePower(enable); // Parse the base of the exponentiation.

        // Check for exponentiation operator '^' and parse accordingly.
        if (hasNextToken() && peek("^")) {
            advance(); // Skip '^' operator.
            advance(); // Move to the exponent part.
            v = new BinaryArithExpr(v, "^", parseFactor(enable)); // Construct the power expression.
        }

        return v; // Return the expression for the factor, potentially with exponentiation.
    }

    /**
     * Parses expressions for powers and basic elements (numbers, variables, parentheses-enclosed expressions).
     * If 'enable' is false, returns placeholders instead of actual values.
     * - Numbers: Returns an IntLiteral with the number's value if 'enable' is true; otherwise, returns 1.
     * - Identifiers: Checks if the variable exists, updates it if necessary, and returns a Variable expression.
     * - Parentheses: Parses the enclosed expression recursively.
     * - Special keywords: Handles specific game-related expressions.
     *
     * @param enable Controls whether to evaluate expressions or return placeholders.
     * @return Expr The resulting expression object.
     * @throws SyntaxError For syntax issues.
     * @throws EvaluationError For evaluation problems.
     */
    private Expr parsePower(boolean enable) throws SyntaxError, EvaluationError {
        Token token = getCurrentToken();
        if (token.type() == TokenType.NUMBER) {
            if(enable){
                int value = Integer.parseInt(getCurrentToken().value());
                return new IntLiteral(value);
            }else
                return new IntLiteral(1);
        } else if (token.type() == TokenType.IDENTIFIER) {
            if(enable){
                if (!playerVariables.containsKey(getCurrentToken().value()))
                    playerVariables.put(getCurrentToken().value(), 0);
                return new Variable(getCurrentToken().value());
            }   else
                return new IntLiteral(1);
        } else if (expect("(")) {
            advance();
            Expr expr = parseExpression(enable);
            advance();
            if(!expect(")"))
                throw new SyntaxError(currentPosition+token.value()+"Expected )");
            return expr;
        } else
            if(!(Keywords.getInfo().contains(getCurrentToken().value())))
                throw new SyntaxError(currentPosition+token.value()+"Expected a number or variable or keyword");
            return parseInfoExpr(enable);
    }

    /**
     * Parses special information expressions, such as getting opponent details or entities in a specific direction.
     * The method decides based on the current token which game-specific information to retrieve and returns the
     * corresponding expression object.
     * - If the token matches "opponent", returns information about the opponent.
     * - If the token specifies a direction (and matches known directions), returns entities near the player in that direction.
     * The method uses the 'enable' flag to conditionally execute or parse these expressions, which can be leveraged
     * to adjust behavior based on game logic or conditions.
     *
     * @param enable If true, actual game information is retrieved; otherwise, behavior might be conditional.
     * @return Expr An expression object representing the retrieved game information.
     * @throws SyntaxError If the expression cannot be parsed or expected information is missing.
     */
    private Expr parseInfoExpr(boolean enable) throws SyntaxError {
        if(expect( "opponent")){
            if(enable)
            //call opponent
                return player.opponent();
            else
                return new IntLiteral(1);
        }else {
            if(hasNextToken())
                advance();
            else
                throw new SyntaxError(currentPosition+"Expected an expression");
            Token token = getCurrentToken();
            if(Keywords.getDirection().contains(token.value())){
                String dir = token.value();
                if(enable)
                    return player.nearby(dir);
                else
                    return new IntLiteral(1);
            }else throw new SyntaxError(currentPosition+token.value()+"Expect direction");
        }
//        return null;
    }
}
