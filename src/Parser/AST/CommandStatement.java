package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Player;
import Interfaces.Statement;
public record CommandStatement(String command) implements Statement {
    @Override
    public void execute(Player player) throws EvaluationError {
        switch (command) {
            case "done":
                player.done();
                break;
            case "relocate":
                player.relocate();
                break;
            default:
                throw new EvaluationError("Unknown command: " + command);
        }
    }
}
