import Exceptions.EvaluationError;
import Interfaces.Expr;
import Interfaces.Statement;
import Parser.*;
import Parser.AST.Plan;
import RegionP.Region;

import java.util.*;

public class PlayerContext implements Interfaces.Player {
    private final String name;
    private final Region[][] regions;
    private Region curCaptal;
    private HashMap<String, Integer> variables;
    private int currow;
    private int curcol;
    private int budget;
    private final int Actioncost = 1;
    private Plan plan;
    private int exe = 0;
    private boolean isDone = false;



    public PlayerContext(String name, Region[][] regions) {
        this.regions = regions;
        this.name = name;
        this.variables = new HashMap<>();
        this.budget = Board.init_budget;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void executePlan() throws EvaluationError {
        List<Statement> statements = plan.getStatements();
        for (int i = 0; i < statements.size(); i++) {
            statements.get(i).execute(this);
            if(isDone)  break;
        }
        isDone = false;
        calculateInterest();
    }


    public int getCurrow() {
        return currow;
    }

    public void setCurrow(int x) {
        currow = x;
    }

    public int getCurcol() {
        return curcol;
    }

    public void setCurcol(int x) {
        curcol = x;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
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
    public int getVar(String variable) {
        return variables.getOrDefault(variable, 0);
    }
    @Override
    public void setVar(String variable, int value) {
        variables.put(variable, value);
    }
    @Override
    public void done() {
        isDone = true;
    }

    @Override
    public void relocate() {
        System.out.println(name + " relocate");

        // x should be  the minimum moving distance from the current city center to the current region
        int x = 10;
        if (budget - (5 * x) + 10 > 0 && Objects.equals(regions[currow][curcol].getOwner(), name)) {
            budget = budget - (5 * x) + 10;

            //set current capital
            curCaptal.setCapital(false);
            regions[currow][curcol].setCapital(true);
            regions[currow][curcol].setOwner(name);

        } else {
            done();
        }


    }

    private void DoMove(int added_row, int added_col) {
        //if the moved region is not belong to opponent
//        System.out.println(name);
//        System.out.println(regions[currow+added_row][curcol+added_col].getOwner());
        if(regions[currow+added_row][curcol+added_col].getOwner().equals("-")|| regions[currow+added_row][curcol+added_col].getOwner().equals(name)){

        regions[currow][curcol].setStandHere("-");
        regions[currow + added_row][curcol + added_col].setStandHere(name);
        //set new position of the player
        currow = currow + added_row;
        curcol = curcol + added_col;
        }else{
            //no op
            return;
        }
    }
    @Override
    public void move(String dir) {
        System.out.println(name + " moved " + dir);
        if (budget - Actioncost > 0) {
            budget = budget - Actioncost;
            label:
            while (true) {
                switch (dir) {
                    case "up":
                        DoMove(-1, 0);
                        break label;
                    case "upright":
                        if (curcol % 2 == 0) {
                            DoMove(0, 1);
                        } else {
                            DoMove(-1, 1);
                        }
                        break label;
                    case "downright":
                        if (curcol % 2 == 1) {
                            DoMove(0, 1);
                        } else {
                            DoMove(1, 1);
                        }
                        break label;
                    case "down":
                        DoMove(1, 0);
                        break label;
                    case "downleft":
                        if (curcol % 2 == 1) {
                            DoMove(0, -1);
                        } else {
                            DoMove(1, -1);
                        }
                        break label;
                    case "upleft":
                        if (curcol % 2 == 0) {
                            DoMove(0, -1);
                        } else {
                            DoMove(-1, -1);
                        }
                        break label;
                    default:
                        System.out.println("Invalid move. Please try again.");
                        break;
                }

            }

        } else {
            done();
        }


    }



    private boolean is_adjacent(){
        //ขอบบน
        if(currow + 1 > Board.getRows()){
            boolean case2 = regions[currow][curcol].getStandHere().equals(regions[currow][curcol + 1].getOwner());
            boolean case3 = regions[currow][curcol].getStandHere().equals(regions[currow - 1][curcol].getOwner());
            boolean case4 = regions[currow][curcol].getStandHere().equals(regions[currow][curcol - 1].getOwner());
            boolean case6 = regions[currow][curcol].getStandHere().equals(regions[currow - 1][curcol + 1].getOwner());
            boolean case8 = regions[currow][curcol].getStandHere().equals(regions[currow - 1][curcol - 1].getOwner());
            return  case2 || case3 || case4 ||  case6 ||  case8 ;
        }
        if(currow - 1 <0){
            boolean case1 = regions[currow][curcol].getStandHere().equals(regions[currow + 1][curcol].getOwner());
            boolean case2 = regions[currow][curcol].getStandHere().equals(regions[currow][curcol + 1].getOwner());
            boolean case4 = regions[currow][curcol].getStandHere().equals(regions[currow][curcol - 1].getOwner());
            boolean case5 = regions[currow][curcol].getStandHere().equals(regions[currow + 1][curcol - 1].getOwner());
            boolean case7 = regions[currow][curcol].getStandHere().equals(regions[currow + 1][curcol + 1].getOwner());
            return case1 || case2 ||  case4 || case5 ||  case7  ;
        }
        if(curcol + 1 > Board.getCols()){
            boolean case1 = regions[currow][curcol].getStandHere().equals(regions[currow + 1][curcol].getOwner());
            boolean case3 = regions[currow][curcol].getStandHere().equals(regions[currow - 1][curcol].getOwner());
            boolean case4 = regions[currow][curcol].getStandHere().equals(regions[currow][curcol - 1].getOwner());
            boolean case5 = regions[currow][curcol].getStandHere().equals(regions[currow + 1][curcol - 1].getOwner());
            boolean case8 = regions[currow][curcol].getStandHere().equals(regions[currow - 1][curcol - 1].getOwner());
            return case1 ||  case3 || case4 || case5 ||  case8 ;
        }
        if(curcol - 1 < 0){
            boolean case1 = regions[currow][curcol].getStandHere().equals(regions[currow + 1][curcol].getOwner());
            boolean case2 = regions[currow][curcol].getStandHere().equals(regions[currow][curcol + 1].getOwner());
            boolean case3 = regions[currow][curcol].getStandHere().equals(regions[currow - 1][curcol].getOwner());
            boolean case6 = regions[currow][curcol].getStandHere().equals(regions[currow - 1][curcol + 1].getOwner());
            boolean case7 = regions[currow][curcol].getStandHere().equals(regions[currow + 1][curcol + 1].getOwner());
            return case1 || case2 || case3 ||   case6 || case7 ;
        }
        return false;
    }


    @Override
    public void invest(int i) {
        System.out.println(name + " invested " + i);

        System.out.println(is_adjacent());
        if (budget - (Actioncost + i) > 0 && is_adjacent()) {
            budget = budget - Actioncost;
            if (i < Board.max_dep) {

                budget = budget - i;
                regions[currow][curcol].setOwner(name);
                regions[currow][curcol].increaseCurdeposit(i);

            }

        } else {
            budget = budget - Actioncost;
        }

    }

    @Override
    public void collect(int i) {
        System.out.println(name + " collected " + i);

        if (budget - Actioncost > 0) {
            budget = budget - Actioncost;
            if (Objects.equals(regions[currow][curcol].getOwner(), name)) {
                if (i < regions[currow][curcol].getCurrentDeposite()) {
                    regions[currow][curcol].decreaseCurdeposit(i);
                    budget += i;
                }else{
                    budget += (int) regions[currow][curcol].getCurrentDeposite();
                    regions[currow][curcol].setOwner("-");
                    regions[currow][curcol].setCurrentDeposite(0);
                }
            }
        } else {
            done();
        }
        //do this
    }

    private void SHoot(int x, int added_row, int added_col) {
        budget -= x + Actioncost;
        regions[currow + added_row][curcol + added_col].decreaseCurdeposit(x);
        if (regions[currow + added_row][curcol + added_col].getCurrentDeposite() < 1) {
            regions[currow + added_row][curcol + added_col].setCurrentDeposite(0);

            if (regions[currow + added_row][curcol + added_col].isCapital()) {
                for (int i = 0; i < Board.getRows(); i++) {
                    for (int j = 0; j < Board.getCols(); j++) {
//                        System.out.println(i+" "+j);
                        //delete all the owner in the regions that belong to deleted player
                        if (regions[i][j].getOwner().equals(regions[currow + added_row][curcol + added_col].getOwner())) {
                            System.out.println(regions[currow + added_row][curcol + added_col].getOwner() +"row:"+i+ "col:" +j);
                            regions[i][j].setOwner("-");
                        }
                        //delete the player that stand
                        if (regions[i][j].getStandHere().equals(regions[currow + added_row][curcol + added_col].getOwner())) {
                            regions[i][j].setStandHere("-");
                        }

                    }
                }
                regions[currow + added_row][curcol + added_col].setCapital(false);
            }
            regions[currow + added_row][curcol + added_col].setOwner("-");
        }
    }
    @Override
    public void shoot(String dir, int x) {
        System.out.println(name + " shooting " + dir + " " + x);

        if (budget - (x + Actioncost) > 0) {

            switch (dir) {
                case "up":
                    SHoot(x, -1, 0);
                    break;

                case "upright":
                    if (curcol % 2 == 0) {
                        SHoot(x, 0, +1);
                    } else {
                        SHoot(x, -1, 1);
                    }
                    break;

                case "downright":
                    if (curcol % 2 == 1) {
                        SHoot(x, 0, +1);
                    } else {
                        SHoot(x, 1, 1);
                    }

                    break;

                case "down":
                    SHoot(x, 1, 0);
                    break;

                case "downleft":
                    if (curcol % 2 == 1) {
                        SHoot(x, 0, -1);
                    } else {
                        SHoot(x, 1, -1);
                    }

                    break;

                case "upleft":
                    if (curcol % 2 == 0) {
                        SHoot(x, 0, -1);
                    } else {
                        SHoot(x, -1, -1);
                    }

                    break;

                default:

                    System.out.println("Invalid shoot. Please try again.");

                    break;
            }

        }

    }

    @Override
    public Expr opponent() {
        System.out.println("opponent called");


        //should returns the location of the closest region belonging to an opponent
        //not done

        return new IntLiteral(0);

    }


    //not sure && not don
    @Override
    public Expr nearby(String dir) {
        System.out.println("nearby " + dir + "called");

        switch (dir) {
            case "up":
                int newROW = currow + 1;
                int newCOL = curcol;
                while(regions[newROW][newCOL] != null){

                    //found opponent
                    if (!regions[newROW][newCOL].getOwner().equals(name) && !regions[newROW][newCOL].getOwner().equals("-")) {
                        return new IntLiteral((int) 100*(newROW - currow) + (int) countDigits((int) regions[newROW][newCOL].getCurrentDeposite()));
                    }
                    newROW = newROW + 1;
                    if(regions[newROW][newCOL] == null){
                        return new IntLiteral(0);
                    }
                }



//                if () {
//                    return new IntLiteral(100 * minDistance + countDigits((int) regions[ClosestLocation[0]][ClosestLocation[1]].getCurrentDeposite()));
//                } else {
//                    return new IntLiteral(0);
//                }


            case "upright":
                if (curcol % 2 == 0) {

                    regions[currow][curcol + 1].setStandHere(name);

                } else {

                    regions[currow - 1][curcol + 1].setStandHere(name);

                }

            case "downright":
                if (curcol % 2 == 1) {

                    regions[currow][curcol + 1].setStandHere(name);

                } else {

                    regions[currow + 1][curcol + 1].setStandHere(name);

                }

            case "down":

                regions[currow + 1][curcol].setStandHere(name);


            case "downleft":
                if (curcol % 2 == 1) {

                    regions[currow][curcol - 1].setStandHere(name);


                } else {

                    regions[currow + 1][curcol - 1].setStandHere(name);


                }

            case "upleft":

                if (curcol % 2 == 0) {

                    regions[currow][curcol - 1].setStandHere(name);

                } else {

                    regions[currow - 1][curcol - 1].setStandHere(name);

                }

            default:

                return new IntLiteral(0);


        }


    }

    private static int countDigits(int number) {
        return (int) Math.floor(Math.log10(Math.abs(number))) + 1;
    }

    @Override
    public String toString() {
        return "PlayerContext{" +
                "name='" + name + '\'' +
                ", variables=" + variables +
                '}';
    }

    @Override
    public void RandomlyClaimCityCenter(Region[][] board, Scanner scanner) {

        //scan v.
//        while (true) {
//            int row = scanner.nextInt();
//            int col = scanner.nextInt();
//
//            if (row < 1 || row >= board.length+1 || col < 1 || col >= board[0].length+1 || !Objects.equals(board[row - 1][col - 1].getOwner(), "-")) {
//                System.out.println("Invalid RegionP.Region. Please try again.");
//            } else {
//                board[row-1][col-1].setOwner(name);
//                board[row-1][col-1].setStandHere(name);
//                board[row-1][col-1].setCapital(true);
//                curCaptal = board[row-1][col-1] ;
//                board[row-1][col-1].increaseCurdeposit( Board.init_center_dep );
//                currow = row-1;
//                curcol = col-1;
//                break;
//            }
//        }

        //random v.

        Random random = new Random();
        int row = random.nextInt(9) + 1;
        int col = random.nextInt(9) + 1;
        board[row - 1][col - 1].setOwner(name);
        board[row - 1][col - 1].setStandHere(name);
        board[row - 1][col - 1].setCapital(true);
        curCaptal = board[row - 1][col - 1];
        board[row - 1][col - 1].increaseCurdeposit(Board.init_center_dep);
        currow = row - 1;
        curcol = col - 1;


    }

    private void calculateInterest() {
        for (int i = 0; i < Board.getRows(); i++) {
            for (int j = 0; j < Board.getCols(); j++) {

                if (regions[i][j].getOwner().equals(name)) {
                    regions[i][j].increaseCurdeposit((Board.interest_pct * regions[i][j].getCurrentDeposite()) / 100);
                }

            }
        }
    }

}
