package Interfaces;
import java.util.Map;
import Exceptions.*;
public interface Expr {
    int eval(Map<String, Integer> bindings) throws EvaluationError;
    int eval(Map<String, Integer> bindings,Player player) throws EvaluationError;
}
