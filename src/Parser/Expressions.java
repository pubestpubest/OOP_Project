package Parser;
import java.util.Map;
import Exceptions.*;
import Interfaces.*;
record IntLiteral(int value) implements Expr{
    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        return value;
    }
}
record Variable(String name) implements Expr{
    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        if( bindings.containsKey(name) ) return bindings.get(name);
        throw new EvaluationError ("Unknown variable " + name);
    }
}