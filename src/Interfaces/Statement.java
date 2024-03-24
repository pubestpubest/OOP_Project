package Interfaces;
import Exceptions.EvaluationError;
public interface Statement {
    void execute(Player player) throws EvaluationError;
}
