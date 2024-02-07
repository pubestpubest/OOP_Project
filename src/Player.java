public class Player {
    private String name ;
    private Region[][] CurRegion ;
    private double budget;

    //constructor
    public Player(String name ){
        this.name = name;
    }

    private void done(){
        //Do something
    }
    private void relocate(){
//        double cost = 5 * minDisFromCurrentCityCenter+ 10;
//        if(budget - cost > 0 || CurrentRegionBelongToThePlayer){
//            budget = budget - cost ;
//
//            //Do something to relocate
//
//        }else{
//            System.out.println("Relocation fail");
//        }
//
//        done();
    }

    private void move(int direction){
        //player's budget is decreased by one unit
//        budget = budget-1 ;
//        if(budget < 0){
//            done();
//        }else{
//            if(CurrentRegionDontBelongToTheopponent){
//
//                ////Do something to move
//
//            }else{
//                //No-op
//            }
//        }

    }

    /*adds more deposits to the current region occupied by the city crew.
    The total cost of an investment is i+1, where i is the investment amount.
    If the player does not have enough budget to execute this command, the command acts like a no-op
    , but the player still pays for the unit cost of the command.
    Otherwise, the player's deposit in the current region is increased by i,
     but will not exceed the maximum deposit as specified in the configuration file.
     A player may invest in a region belonging to no player as long as that region is adjacent to another region belonging to the player.
     */
    private void invest(){}

    /*retrieves deposits from the current region occupied by the city crew.
    Whenever this command is executed, the player's budget is decreased by one unit.
     If the player does not have enough budget to execute this command
     , the evaluation of the construction plan in that turn ends.
     If the specified collection amount is more than the deposit in the current region,
      the command acts like a no-op, but the player still pays for the unit cost of the command.
       Otherwise, the player's deposit in the current region is decreased by the collection amount,
        which is then added to the player's available budget.  If the deposit becomes zero after the collection
        , the player loses the possession of that region.
     */
    private void collect(){}


    private void shoot(){}
