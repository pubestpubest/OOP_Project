package Parser;
import java.util.Map;
import Exceptions.*;
import Interfaces.*;
public record IntLiteral(int value) implements Expr{
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
record Opponent() implements Expr{
    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        return 0;
    }
}

record Nearby(String direction) implements Expr{
    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        return 0;
    }
}
record BinaryArithExpr(Expr left, String op, Expr right) implements Expr{
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        int le = left.eval(bindings);
        int re = right.eval(bindings);
        if(op.equals("+")) return le + re;
        if(op.equals("-")) return le - re;
        if(op.equals("*")) return le * re;
        if(op.equals("/")) return le / re;
        if(op.equals("%")) return le % re;
        if(op.equals("^")) return (int) Math.pow(le,re);
        throw new EvaluationError("unknown operator : " + op);
    }
}