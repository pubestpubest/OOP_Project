import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.*;
import Interfaces.Player;
import Parser.*;

import Tokenizer.ConstructionPlanTokenizer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws EvaluationError {
        String InputStream =
                "t = t + 1\n" +
                "m = 0\n" +
                "deposit = 100\n" +
                "while (deposit) { \n" +
                "  if (deposit - 100)\n" +
                "  then collect (deposit / 4)\n" +
                "  else if (budget - 25) then invest 25\n" +
                "  else {}\n" +
                "  if (budget - 100) then {} else done  \n" +
                "  opponentLoc = opponent\n" +
                "  if (opponentLoc / 10 - 1)\n" +
                "  then\n" +
                "    if (opponentLoc % 10 - 5) then move downleft\n" +
                "    else if (opponentLoc % 10 - 4) then move down\n" +
                "    else if (opponentLoc % 10 - 3) then move downright\n" +
                "    else if (opponentLoc % 10 - 2) then move downright\n" +
                "    else if (opponentLoc % 10 - 1) then move upright\n" +
                "    else move up\n" +
                "  else if (opponentLoc)\n" +
                "  then  \n" +
                "    if (opponentLoc % 10 - 5) then {\n" +
                "      cost = 10 ^ (nearby upleft % 100 + 1)\n" +
                "      if (budget - cost) then shoot upleft cost else {}\n" +
                "    }\n" +
                "    else if (opponentLoc % 10 - 4) then {\n" +
                "      cost = 10 ^ (nearby downleft % 100 + 1)\n" +
                "      if (budget - cost) then shoot downleft cost else {}\n" +
                "    }\n" +
                "    else if (opponentLoc % 10 - 3) then {\n" +
                "      cost = 10 ^ (nearby down % 100 + 1)\n" +
                "      if (budget - cost) then shoot down cost else {}\n" +
                "}\n" +
                "else if (opponentLoc % 10 - 2) then {\n" +
                "      cost = 10 ^ (nearby downright % 100 + 1)\n" +
                "      if (budget - cost) then shoot downright cost else {}\n" +
                "    }\n" +
                "    else if (opponentLoc % 10 - 1) then {\n" +
                "      cost = 10 ^ (nearby upright % 100 + 1)\n" +
                "      if (budget - cost) then shoot upright cost else {}\n" +
                "    }\n" +
                "    else {\n" +
                "      cost = 10 ^ (nearby up % 100 + 1)\n" +
                "      if (budget - cost) then shoot up cost else {}\n" +
                "    }\n" +
                "  else {\n" +
                "dir = random % 6\n" +
                "if (dir - 4) then move upleft\n" +
                "else if (dir - 3) then move downleft else if (dir - 2) then move down\n" +
                "else if (dir - 1) then move downright else if (dir) then move upright\n" +
                "else move up\n" +
                "m=m+1\n" +
                "  }\n" +
                "}\n" +
                "# city crew on a region belonging to nobody, so claim it\n" +
                "if (budget - 1) then invest 1 else {}\n" +
                "-1";

        String arithmetic = "i = 100\n" +
                "while(i){\n" +
                "invest i\n" +
                "i = i - 2\n" +
                "}\n";

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


        while(true){
            //calculate interest of player1

                playerPlay(scanner,players[currentPlayer]);
                System.out.println("Current board:");
                game.printBoard();

            System.out.print("End turn? (y/n): ");
            String answer1 = scanner.nextLine().toLowerCase();
            if (answer1.equals("y")) {

                currentPlayer = (currentPlayer + 1) % 2;
                System.out.println("\nTurn switching to " + players[currentPlayer] + ".");
                players[currentPlayer].calculateInterest();
                System.out.println("Current board after calculate interest:");
                game.printBoard();

            } else if (!answer1.equals("n")) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }


       }



    }

    public static void playerPlay(Scanner scanner, Player player){
        System.out.println("Player "+player.getName()+"'s turn. Enter action");

        System.out.println("current budget:" +  player.getBudget());

        String action2 = scanner.nextLine();

        Tokenizer tokenizer2 = new ConstructionPlanTokenizer(action2);
        Parser parser2 = new ConstructionPlanParser();
        try{
            System.out.println(player);
            parser2.parse(tokenizer2.getTokens(), player);
            System.out.println(player);
        }catch(SyntaxError | EvaluationError e){
            System.out.println(e);
        }

    }


}