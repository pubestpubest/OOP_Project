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
    private Region curCaptal;
    private HashMap<String,Integer> variables;
    private int currow;
    private int curcol;
    private int budget;


    public PlayerContext(String name,Region[][] regions){
        this.regions = regions;
        this.name = name;
        this.variables = new HashMap<>();
        this.budget = Board.init_budget ;

    }
    public String getName() {
        return name;
    }
    public int getBudget(){
        return budget;
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

        // x should be  the minimum moving distance from the current city center to the current region
        int x = 10;


        if(budget - (5*x)+10 > 0 || Objects.equals(regions[currow][curcol].getOwner(), name)){
            budget = budget - (5*x)+10;

            //set current capital
            curCaptal.setCapital(false);
            regions[currow][curcol].setCapital(true);


        }else{
            done();
        }



    }
    @Override
    public void move(String dir) {

        System.out.println(name+" moved "+dir);
//Not sure how much should decrease the budget
        if(budget-1 > 0){
            budget = budget-1;
//        if(isOpponent || isvalidDirection){
//           no-op
//        } else{

//            Do move...
            label:
            while(true){

                switch (dir) {
                    case "up":

                        regions[currow][curcol].setPosition("-");
                        regions[currow - 1][curcol].setPosition(name);
                        SetPlayerPos(currow - 1, curcol);

                        break label;

                    case "upright":

                        regions[currow][curcol].setPosition("-");
                        regions[currow - 1][curcol + 1].setPosition(name);
                        SetPlayerPos(currow - 1, curcol + 1);

                        break label;

                    case "downright":

                        regions[currow][curcol].setPosition("-");
                        regions[currow+1][curcol+1].setPosition(name);
                        SetPlayerPos(currow +1, curcol+1);

                        break label;

                    case "down":

                        regions[currow][curcol].setPosition("-");
                        regions[currow+1][curcol].setPosition(name);
                        SetPlayerPos(currow+1, curcol);

                        break label;

                    case "downleft":

                        regions[currow][curcol].setPosition("-");
                        regions[currow+1][curcol-1].setPosition(name);
                        SetPlayerPos(currow+ 1 , curcol -1);

                        break label;

                    case "upleft":

                        regions[currow][curcol].setPosition("-");
                        regions[currow - 1][curcol - 1].setPosition(name);
                        SetPlayerPos(currow - 1, curcol - 1);

                        break label;

                    default:

                        System.out.println("Invalid move. Please try again.");

                        break;
                }

            }
//        }

        }else{
            done();
        }

    }

    private void SetPlayerPos(int x,int y){
        currow = x;
        curcol = y;
    }
    @Override
    public void invest(int eval) {
        System.out.println(name+" invested "+eval);
        //do this
    }
    @Override
    public void collect(int eval) {
        System.out.println(name+" collected "+eval);
        //do this
    }
    @Override
    public void shoot(String dir, int eval) {
        System.out.println(name+" shooting "+dir+" "+ eval);
        //do this

    }
    @Override
    public Expr opponent() {
        System.out.println("opponent called");


        //should returns the location of the closest region belonging to an opponent
        return new IntLiteral(999);

    }
    @Override
    public Expr nearby(String dir) {
        System.out.println("nearby "+dir+"called");


        //return  100*x + y;
        return  new IntLiteral(999);
    }

    @Override
    public String toString() {
        return "PlayerContext{" +
                "name='" + name + '\'' +
                ", variables=" + variables +
                '}';
    }

    @Override
    public void ClaimCityCenter(Region[][] board, Scanner scanner) {

        while (true) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 1 || row >= board.length+1 || col < 1 || col >= board[0].length+1 || !Objects.equals(board[row - 1][col - 1].getOwner(), "-")) {
                System.out.println("Invalid RegionP.Region. Please try again.");
            } else {
                board[row-1][col-1].setPosition(name);
                board[row-1][col-1].setStandHere(true);
                board[row-1][col-1].setCapital(true);
                curCaptal = board[row-1][col-1] ;
                currow = row-1;
                curcol = col-1;
                break;
            }
        }


    }
}
