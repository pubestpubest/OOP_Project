package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Expr;
import Interfaces.Player;
import Interfaces.Statement;
import Parser.Keywords;

public record AssignmentStatement(String identifier, Expr expr) implements Statement {

    @Override
    public void execute(Player player) throws EvaluationError {
        if(!(Keywords.getSpecialVar().contains(identifier))){
            int value = expr.eval(player.getVariables(),player); // Consider using player for variable access in expressions
            player.setVar(identifier, value);
        }
    }
}
