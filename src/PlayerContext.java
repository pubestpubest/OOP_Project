import Interfaces.Expr;
import Interfaces.Player;
import Parser.*;
import RegionP.Region;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class PlayerContext implements Player {
    private String name;
    private Region[][] regions;
    private HashMap<String,Integer> variables;
    private int currow;
    private int curcol;


    public PlayerContext(String name,Region[][] Board){
        this.regions = Board;
        this.name = name;
        this.variables = new HashMap<>();

    }
    public String getName() {
        return name;
    }
    @Override
    public HashMap<String, Integer> getVariables() {
        return variables;
    }
    public void setVariables(HashMap<String, Integer> variables) {
        this.variables = variables;
    }
    @Override
    public void done() {
        System.out.println(name+" done");
    }
    @Override
    public void relocate() {
        System.out.println(name+" relocate");
    }
    @Override
    public void move(String dir) {

        System.out.println(name+" moved "+dir);

        label:
        while(true){
            switch (dir) {
                case "up":

                    regions[currow][curcol].setPosition("-");
                    regions[currow - 1][curcol].setPosition(name);
                    SetPlayerPos(currow - 1, curcol);

                    CheckPos();
                    break label;

                case "upright":

                    regions[currow][curcol].setPosition("-");
                    regions[currow - 1][curcol + 1].setPosition(name);
                    SetPlayerPos(currow - 1, curcol + 1);

                    CheckPos();
                    break label;

                case "downright":

                    regions[currow][curcol].setPosition("-");
                    regions[currow+1][curcol+1].setPosition(name);
                    SetPlayerPos(currow +1, curcol+1);

                    CheckPos();
                    break label;

                case "down":

                    regions[currow][curcol].setPosition("-");
                    regions[currow+1][curcol].setPosition(name);
                    SetPlayerPos(currow+1, curcol);

                    CheckPos();
                    break label;

                case "downleft":

                    regions[currow][curcol].setPosition("-");
                    regions[currow+1][curcol-1].setPosition(name);
                    SetPlayerPos(currow+ 1 , curcol -1);

                    CheckPos();
                    break label;

                case "upleft":

                    regions[currow][curcol].setPosition("-");
                    regions[currow - 1][curcol - 1].setPosition(name);
                    SetPlayerPos(currow - 1, curcol - 1);

                    CheckPos();
                    break label;

                default:

                    System.out.println("Invalid move. Please try again.");

                    break;
            }

        }
    }
    public void CheckPos(){
        System.out.println("currow :"+currow );
        System.out.println("curcol :"+curcol);
    }
    private void SetPlayerPos(int x,int y){
        currow = x;
        curcol = y;
    }
    @Override
    public void invest(int eval) {
        System.out.println(name+" invested "+eval);
    }
    @Override
    public void collect(int eval) {
        System.out.println(name+" collected "+eval);
    }
    @Override
    public void shoot(String dir, int eval) {
        System.out.println(name+" shooting "+dir+" "+ eval);
    }
    @Override
    public Expr opponent() {
        System.out.println("opponent called");
        return new IntLiteral(999);
    }
    @Override
    public Expr nearby(String dir) {
        System.out.println("nearby "+dir+"called");
        return new IntLiteral(999);
    }

    @Override
    public String toString() {
        return "PlayerContext{" +
                "name='" + name + '\'' +
                ", variables=" + variables +
                '}';
    }
    @Override
    public void ClaimFirstRegion(Region[][] board, Scanner scanner) {
        while (true) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 1 || row >= board.length+1 || col < 1 || col >= board[0].length+1 || !Objects.equals(board[row - 1][col - 1].getOwner(), "-")) {
                System.out.println("Invalid RegionP.Region. Please try again.");
            } else {
                board[row-1][col-1].setPosition(name);
                board[row-1][col-1].setStandHere(true);
                currow = row-1;
                curcol = col-1;
                break;
            }
        }
    }
}
