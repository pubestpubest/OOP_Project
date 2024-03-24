package Interfaces;
import java.util.Map;
import java.util.Scanner;

import Exceptions.EvaluationError;
import Parser.AST.Plan;
import RegionP.Region;

public interface Player {

    public void setPlan(Plan plan);
    public void executePlan() throws EvaluationError;
    int getVar(String variable);
    void setVar(String variable, int value);
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
    public void RandomlyClaimCityCenter(Region[][] board, Scanner scanner);

    String getName();

    int getBudget();
    public int getBoardRow();
    public int getBoardCol();
    int getCurrow();
    int getCurcol();
    public int getCurDeposit();
    int getInt();
    int getMdeposit();
}
