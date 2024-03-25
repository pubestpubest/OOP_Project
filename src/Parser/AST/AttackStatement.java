package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Expr;
import Interfaces.Player;
import Interfaces.Statement;

//AttackCommand → shoot Direction Expression

public record AttackStatement(String dir, Expr expr) implements Statement {
    @Override
    public void execute(Player player) throws EvaluationError {
        player.shoot(dir, expr.eval(player.getVariables(),player));
    }
}
