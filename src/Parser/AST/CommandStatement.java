package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Player;
import Interfaces.Statement;
public record CommandStatement(String command) implements Statement {
    @Override
    public void execute(Player player) throws EvaluationError {

    }
}
