import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.*;
import Interfaces.Player;
import Parser.*;

import Parser.AST.Plan;
import Tokenizer.ConstructionPlanTokenizer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws EvaluationError {


//        ConstructionPlanTokenizer tokenizeeeer = new ConstructionPlanTokenizer(InputStream);



        Scanner scanner = new Scanner(System.in);

        Board game = new Board();

        System.out.println(Board.getRows()+"x"+Board.getCols()+" UPBEAT board:");

        PlayerContext[] players = null; // Declares the array reference without initial size
        players =  new PlayerContext[2];

        players[0] = new PlayerContext("1",game.regions);
        players[1] = new PlayerContext("2",game.regions);

        System.out.println("Current board:");
        game.printBoard();
        System.out.println("Player 1's turn. Enter row and column to claim a region:");

        players[0].RandomlyClaimCityCenter(game.regions, scanner);

        System.out.println("Current board:");
        game.printBoard();

        System.out.println("Player 2's turn. Enter row and column to claim a region:");

        players[1].RandomlyClaimCityCenter(game.regions, scanner);

        System.out.println("Current board:");
        game.printBoard();

        int currentPlayer =  0;

        int i = 1;
        while(true){
            //calculate interest of player1

                playerPlay(scanner,players[currentPlayer],i);
                System.out.println("Current board:");
                game.printBoard();

            System.out.print("End turn? (y/n): ");
            String answer1 = scanner.nextLine().toLowerCase();
            if (answer1.equals("y")) {

                currentPlayer = (currentPlayer + 1) % 2;
                System.out.println("\nTurn switching to " + players[currentPlayer] + ".");
                System.out.println("Current board after calculate interest:");
                game.printBoard();

            } else if (!answer1.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
            i++;
       }
    }

    public static void playerPlay(Scanner scanner, Player player,int i){
        System.out.println("Player "+player.getName()+"'s turn. Enter action");

        System.out.println("current budget:" +  player.getBudget());

        String action2 = scanner.nextLine();

        Tokenizer tokenizer2 = new ConstructionPlanTokenizer(action2);
        try{
//            System.out.println(tokenizer2.getTokens());
            Parser parser = new ASTParser();
            Plan plan = parser.parse(tokenizer2.getTokens());
            player.setPlan(plan);
//            System.out.println(plan.getStatements());
            player.executePlan();
        }catch(SyntaxError | EvaluationError e){
            System.out.println(e);
        }

    }


}