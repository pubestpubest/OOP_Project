package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Expr;
import Interfaces.Player;
import Interfaces.Statement;
public record AssignmentStatement(String identifier, Expr expr) implements Statement {

    @Override
    public void execute(Player player) throws EvaluationError {
        int value = expr.eval(player.getVariables()); // Consider using player for variable access in expressions
        player.setVar(identifier, value);
    }
}
