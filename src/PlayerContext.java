import Interfaces.Expr;
import Interfaces.Player;
import Parser.*;
import RegionP.Region;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class PlayerContext implements Player {
    private String name;
    private HashMap<String,Integer> variables;
    private int posX;
    private int posY;


    public PlayerContext(String name){
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
                board[row-1][col-1].setOwner(name);

                posX = row-1;
                posY = col-1;
                break;
            }
        }
    }
}
