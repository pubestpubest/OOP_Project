package Parser;
import java.util.Map;
import java.util.Random;

import Exceptions.*;
import Interfaces.*;
import RegionP.*;
public record IntLiteral(int value) implements Expr{
    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        return value;
    }

    @Override
    public int eval(Map<String, Integer> bindings, Player player) throws EvaluationError {
        return eval(bindings);
    }
}
record Variable(String name) implements Expr{

    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {

        if( bindings.containsKey(name) ){
            return bindings.get(name);
        }else {
            bindings.put(name,0);
            return 0;
        }
//        throw new EvaluationError ("Unknown variable " + name);
    }

    @Override
    public int eval(Map<String, Integer> bindings, Player player) throws EvaluationError {
        if(Keywords.getSpecialVar().contains(name)){
            return switch (name) {
                case "rows" -> player.getBoardRow();
                case "cols" -> player.getBoardCol();
                case "currow" -> player.getCurrow() + 1;
                case "curcol" -> player.getCurcol() + 1;
                case "budget" -> player.getBudget();
                case "deposit" -> player.getCurDeposit();
                case "int" -> player.getInt();
                case "maxdeposit" -> player.getMdeposit();
                case "random" -> {
                    Random random = new Random();
                    yield random.nextInt(0, 999);
                }
                default -> eval(bindings);
            };
        }else return eval(bindings);
    }
}
record Opponent() implements Expr{
    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        return 0;
    }

    @Override
    public int eval(Map<String, Integer> bindings, Player player) throws EvaluationError {
        return player.opponent().eval(bindings);
    }
}

record Nearby(String direction) implements Expr{
    @Override
    public int eval(Map<String, Integer> bindings) throws EvaluationError {
        return 0;
    }

    @Override
    public int eval(Map<String, Integer> bindings, Player player) throws EvaluationError {
        System.out.println("123564");
        return player.nearby(direction).eval(bindings);
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

    @Override
    public int eval(Map<String, Integer> bindings, Player player) throws EvaluationError {
        int le = left.eval(bindings,player);
        int re = right.eval(bindings,player);
        if(op.equals("+")) return le + re;
        if(op.equals("-")) return le - re;
        if(op.equals("*")) return le * re;
        if(op.equals("/")) return le / re;
        if(op.equals("%")) return le % re;
        if(op.equals("^")) return (int) Math.pow(le,re);
        throw new EvaluationError("unknown operator : " + op);
    }
}