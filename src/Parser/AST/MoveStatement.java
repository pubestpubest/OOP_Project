package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Player;
import Interfaces.Statement;

// MoveCommand → move Direction

public record MoveStatement(String dir) implements Statement {
    @Override
    public void execute(Player player) throws EvaluationError {
        player.move(dir);
    }
}
