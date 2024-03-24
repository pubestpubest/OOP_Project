package Parser.AST;
import Interfaces.Statement;

import java.util.List;
public class Plan {
    private List<Statement> statements;
    public Plan(List<Statement> statements) {
        this.statements = statements;
    }
    public List<Statement> getStatements() {
        return statements;
    }
}
