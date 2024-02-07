package Interfaces;
import java.util.Map;
public interface Player {
    void done();
    void relocate();
    void move();
    void invest();
    void collect();
    void shoot();
    Map<String, Integer> getVariables();
    public String toString();
}
