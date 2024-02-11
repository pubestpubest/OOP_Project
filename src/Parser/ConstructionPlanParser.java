package Parser;
import Exceptions.EvaluationError;
import Exceptions.SynataxError;
import Interfaces.Parser;
import Interfaces.Player;
import Tokenizer.Token;
import Tokenizer.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConstructionPlanParser implements Parser {
    List<Token> tokens = new ArrayList<Token>();
    Player player;
    Map<String, Integer> playerVariables;
    int currentPosition = 0;


    public void parse(List<Token> tokens, Player player) throws EvaluationError, SynataxError {
        this.tokens = new ArrayList<>(tokens);  // deep copy
        this.player = player;   //Alias
        this.playerVariables = player.getVariables(); //Alias
        currentPosition = 0;
    }

    private boolean hasNextToken() {
        return currentPosition<tokens.size();
    }
    private void advance(){
        if(hasNextToken())  currentPosition++;
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

    public void parse(){

    }

    private void parseStatement(){
        Token token = getCurrentToken();
        if(token.getType() == TokenType.IDENTIFIER);
    }
}
