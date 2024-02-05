package Parser;
import Interfaces.Parser;
import Tokenizer.*;
import java.util.List;

public class ConstructionPlanParser implements Parser {
    private List<Token> tokens;
    private int currentPosition;

    public ConstructionPlanParser(List<Token> tokens){
        this.tokens = tokens;
        this.currentPosition = 0;
    }
    public void parse() {
        parsePlan();
    }

    private Token getCurrentToken() {
        if(currentPosition<tokens.size()){
            return tokens.get(currentPosition);
        }
        return null;
    }

    private void parsePlan() {
        while (currentPosition < tokens.size()){
            parseStatement();
        }
    }
    private void parseStatement(){
        Token token = getCurrentToken();
        switch (token.getType()) {
            case IDENTIFIER:
        }
    }
    private void parseCommand(){}
    private void parseAssignment(){}
    private void parseAction(){}
    private void parseMove(){}
    private void parseRegion(){}
    private void parseAttack(){}
    private void parseDirection(){}
    private void parseBlock(){}
    private void parseIf(){}
    private void parseWhile(){}
    private void parseExpr(){}
    private void parseTerm(){}
    private void parseFactor(){}
    private void parseInfoExpr(){}
}
