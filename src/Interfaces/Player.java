package Interfaces;
import java.util.Map;
public interface Player {
    void done();
    void relocate();
    void move(String dir);
    void invest(int eval);
    void collect(int eval);
    Map<String, Integer> getVariables();
    public String toString();
    void shoot(String dir, int eval);
    Expr opponent();
    Expr nearby(String dir);
}
