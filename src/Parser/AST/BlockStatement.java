package Parser.AST;
import Exceptions.EvaluationError;
import Interfaces.Player;
import Interfaces.Statement;

import java.util.List;
public record BlockStatement(List<Statement> statements) implements Statement {

    @Override
    public void execute(Player player) throws EvaluationError {
        for(Statement statement:statements){
            statement.execute(player);
        }
    }
}
