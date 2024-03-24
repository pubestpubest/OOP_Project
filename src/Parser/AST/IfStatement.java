package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Expr;
import Interfaces.Player;
import Interfaces.Statement;

public record IfStatement(Expr expression,Statement thenBody,Statement elseBody)implements Statement {
    @Override
    public void execute(Player player) throws EvaluationError {
        if(expression.eval(player.getVariables())>0){
            thenBody.execute(player);
        }else if(elseBody != null){
            elseBody.execute(player);
        }
    }
}
