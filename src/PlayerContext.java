import Interfaces.Expr;
import Parser.*;
import RegionP.Region;


import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class PlayerContext implements Interfaces.Player {
    private String name;
    private Region[][] regions;
    private Region curCaptal;
    private HashMap<String,Integer> variables;
    private int currow;
    private int curcol;
    private int budget;


    public PlayerContext(String name, Region[][] regions){
        this.regions = regions;
        this.name = name;
        this.variables = new HashMap<>();
        this.budget = Board.init_budget ;


    }
    public int getCurrow(){return currow;}
    public void setCurrow(int x){currow = x;}
    public int getCurcol(){return curcol;}
    public void setCurcol(int x){curcol = x;}
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
            //check isOpponent || isvalidDirection
//        if(isOpponent || isvalidDirection){
//            //no-op
//           return;
//
//        } else {

            label:
            while (true) {

                switch (dir) {
                    case "up":

                        regions[currow][curcol].setStandHere("-");
                        regions[currow - 1][curcol].setStandHere(name);
                        SetPlayerPos(currow - 1, curcol);

                        break label;

                    case "upright":

                        if (curcol % 2 == 0) {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow][curcol + 1].setStandHere(name);
                            SetPlayerPos(currow, curcol + 1);
                        } else {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow - 1][curcol + 1].setStandHere(name);
                            SetPlayerPos(currow - 1, curcol + 1);
                        }


                        break label;

                    case "downright":
                        if (curcol % 2 == 1) {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow][curcol + 1].setStandHere(name);
                            SetPlayerPos(currow, curcol + 1);
                        } else {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow + 1][curcol + 1].setStandHere(name);
                            SetPlayerPos(currow + 1, curcol + 1);
                        }

                        break label;

                    case "down":

                        regions[currow][curcol].setStandHere("-");
                        regions[currow + 1][curcol].setStandHere(name);
                        SetPlayerPos(currow + 1, curcol);

                        break label;

                    case "downleft":
                        if (curcol % 2 == 1) {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow][curcol - 1].setStandHere(name);
                            SetPlayerPos(currow, curcol - 1);
                        } else {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow + 1][curcol - 1].setStandHere(name);
                            SetPlayerPos(currow + 1, curcol - 1);
                        }


                        break label;

                    case "upleft":

                        if (curcol % 2 == 0) {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow][curcol - 1].setStandHere(name);
                            SetPlayerPos(currow, curcol - 1);
                        } else {
                            regions[currow][curcol].setStandHere("-");
                            regions[currow - 1][curcol - 1].setStandHere(name);
                            SetPlayerPos(currow - 1, curcol - 1);
                        }


                        break label;

                    default:

                        System.out.println("Invalid move. Please try again.");

                        break;
                }

            }

        }else{
            done();
        }


    }

    private void SetPlayerPos(int x,int y){
        currow = x;
        curcol = y;
    }



    // the region need to adjacent to another region belonging to the player.
    // still doing this
//    private boolean isAdjtoOwnRegion(){
//        regions[currow][curcol].getOwner() ;
//    }
    // mot 100%
    @Override
    public void invest(int i) {
        System.out.println(name+" invested "+i);

        if(budget- (1+i) > 0){

            if(i < Board.max_dep){

                budget = budget-i;
                regions[currow][curcol].setOwner(name);
                regions[currow][curcol].increaseCurdeposit( (double)i );
                System.out.println(regions[currow][curcol].getCurrentDeposite());

            }

        }else{
            budget = budget-1;
        }

    }
    @Override
    public void collect(int i) {
        System.out.println(name+" collected "+i);

        if(budget-1 > 0) {
            budget = budget - 1;
            if (Objects.equals(regions[currow][curcol].getOwner(), name)) {
                budget = budget - 1;
                if (i < regions[currow][curcol].getCurrentDeposite()) {
                    regions[currow][curcol].decreaseCurdeposit(i);
                    budget += i;
                }
            }
        }else{
            done();
        }
        //do this
    }
    private void SHoot(int x, int addrow , int addcol){
        budget -= x+1;
        regions[currow+addrow][curcol+addcol].decreaseCurdeposit(x) ;
        if(regions[currow+addrow][curcol+addcol].getCurrentDeposite() < 1 ){
            regions[currow+addrow][curcol+addcol].setOwner("-");
            regions[currow+addrow][curcol+addcol].setCurrentDeposite(0);

            if(regions[currow+addrow][curcol+addcol].isCapital()){
                for(int i = 0; i< Board.getRows(); i++){
                    for(int j = 0; j< Board.getCols(); j++){

                        //delete all the owner in the regions that belong to deleted player
                        if(!regions[i][j].getOwner().equals(regions[currow + addrow][curcol + addcol].getOwner())){
                            regions[i][j].setOwner("-");
                        }
                        //delete the player that stand
                        if(!regions[i][j].getStandHere().equals(regions[currow + addrow][curcol + addcol].getOwner())){
                            regions[i][j].setStandHere("-");
                        }

                    }
                }
                regions[currow+addrow][curcol+addcol].setCapital(false);
            }
        }
    }
    @Override
    public void shoot(String dir, int x) {
        System.out.println(name+" shooting "+dir+" "+ x);

        if(budget - x+1 > 0){

            switch (dir) {
                case "up":
                    SHoot(x,-1,0);
                    break;

                case "upright":
                    if(curcol%2 == 0){
                        SHoot(x,0,+1);
                    }else{
                        SHoot(x,-1,1);
                    }
                    break;

                case "downright":
                    if(curcol%2 == 1){
                        SHoot(x,0,+1);
                    }else{
                        SHoot(x,1,1);
                    }

                    break;

                case "down":
                    SHoot(x,1,0);
                    break;

                case "downleft":
                    if(curcol%2 == 1){
                        SHoot(x,0,-1);
                    }else{
                        SHoot(x,1,-1);
                    }

                    break;

                case "upleft":
                    if(curcol%2 == 0){
                        SHoot(x,0,-1);
                    }else{
                        SHoot(x,-1,-1);
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


    //not sure && not done
    @Override
    public Expr nearby(String dir) {
        System.out.println("nearby "+dir+"called");

            switch (dir) {
                case "up":
                    while (true){
                        int i=1;
                        if(regions[curcol][currow+i] == null ){
                            break;
                        }
                        if(!regions[curcol][currow+i].getOwner().equals(name) && !regions[curcol][currow+i].getOwner().equals("-")){

                            String text = Double.toString(regions[curcol][currow+i].getCurrentDeposite());
                            int index = text.indexOf('.');
                            int digits = 0;
                            if (index != -1) {

                                digits = text.length() - index - 1;
                            }

                            return  new IntLiteral((int) 100*i + digits);

                        }
                        i++;
                    }
                case "upright":
                    for(int i=1; i<currow  ;i++){
                        if(!regions[curcol-i][currow+i].getOwner().equals(name) && !regions[curcol-i][currow+i].getOwner().equals("-")){

                            return  new IntLiteral((int) 100*i + (int)(regions[curcol-i][currow+i].getCurrentDeposite()/10));

                        }
                    }
                case "downright":
                    for(int i=1; i<Board.getRows()-currow  ;i++){
                        if(!regions[curcol+i][currow+i].getOwner().equals(name) && !regions[curcol+i][currow+i].getOwner().equals("-")){

                            return  new IntLiteral((int) 100*i + (int)(regions[curcol+i][currow+i].getCurrentDeposite()/10));

                        }
                    }
                case "down":
                    for(int i=1; i<Board.getRows()-currow  ;i++){
                        if(!regions[curcol+i][currow].getOwner().equals(name) && !regions[curcol+i][currow].getOwner().equals("-")){

                            return  new IntLiteral((int) 100*i + (int)(regions[curcol+i][currow].getCurrentDeposite()/10));

                        }
                    }
                case "downleft":
                    for(int i=1; i<currow  ;i++){
                        if(!regions[curcol+i][currow-i].getOwner().equals(name) && !regions[curcol+i][currow-i].getOwner().equals("-")){

                            return  new IntLiteral((int) 100*i + (int)(regions[curcol+i][currow-i].getCurrentDeposite()/10));

                        }
                    }
                case "upleft":
                    for(int i=1; i<currow  ;i++){
                        if(!regions[curcol-i][currow-i].getOwner().equals(name) && !regions[curcol-i][currow-i].getOwner().equals("-")){

                            return  new IntLiteral((int) 100*i + (int)(regions[curcol-i][currow-i].getCurrentDeposite()/10));

                        }
                    }
                default:

                    return  new IntLiteral(0);


            }



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
                board[row-1][col-1].setOwner(name);
                board[row-1][col-1].setStandHere(name);
                board[row-1][col-1].setCapital(true);
                curCaptal = board[row-1][col-1] ;
                board[row-1][col-1].increaseCurdeposit( (double)Board.init_center_dep );
                currow = row-1;
                curcol = col-1;
                break;
            }
        }


    }

    public void calculateInterest(){
        for(int i = 0; i< Board.getRows(); i++){
            for(int j = 0; j< Board.getCols(); j++){

                if(regions[i][j].getOwner().equals(name)){
                    regions[i][j].increaseCurdeposit((Board.interest_pct*regions[i][j].getCurrentDeposite())/100);
                }

            }
        }
    }

}
