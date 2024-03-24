package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Expr;
import Interfaces.Player;
import Interfaces.Statement;

//RegionCommand → invest Expression | collect Expression

public record RegionStatement(String action, Expr expr) implements Statement {
    @Override
    public void execute(Player player) throws EvaluationError {
        switch (action){
            case "invest":
                player.invest(expr.eval(player.getVariables()));
                break;
            case "collect":
                player.collect(expr.eval(player.getVariables()));
                break;
            default:
                throw new EvaluationError("Unknown action: " + action);
        }
    }
}
