package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Expr;
import Interfaces.Player;
import Interfaces.Statement;
public record WhileStatement(Expr condition, Statement body)implements Statement {

    @Override
    public void execute(Player player) throws EvaluationError {
        for (int i = 0; i < 10000 && condition.eval(player.getVariables(),player)>0; i++) {
            body.execute(player);
        }
    }
}
