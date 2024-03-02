package Interfaces;
import java.util.Map;
import java.util.Scanner;
import RegionP.Region;

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
    public void ClaimCityCenter(Region[][] board, Scanner scanner);
}
