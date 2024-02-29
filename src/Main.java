import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.*;
import Interfaces.Player;
import Parser.*;

import java.util.HashMap;

import Tokenizer.ConstructionPlanTokenizer;
import Tokenizer.Token;

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
        String action = "done \n"+"done\n";
        Tokenizer tokenizer = new ConstructionPlanTokenizer(InputStream);
        Parser parser = new ConstructionPlanParser();
        Player player = new PlayerContext("Player 1");
        int i=0;
        for (Token token : tokenizer.getTokens()) {
            System.out.print(i++);
            System.out.println(token);
        }
//        if(false)
        try{
            System.out.println(player);
            parser.parse(tokenizer.getTokens(), player);
            System.out.println(player);
        }catch(SyntaxError s){
            System.out.println(s);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of rows and columns for the UPBEAT board:");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        Board game = new Board(rows,cols);

        Player player1 = new PlayerContext("1");
        Player player2 = new PlayerContext("2");

        System.out.println("Current board:");
        game.printBoard();
        System.out.println("Player 1's turn. Enter row and column to claim a region:");

        player1.ClaimFirstRegion(game.board, scanner);

        System.out.println("Current board:");
        game.printBoard();
        System.out.println("Player 2's turn. Enter row and column to claim a region:");

        player2.ClaimFirstRegion(game.board, scanner);

        System.out.println("Current board:");
        game.printBoard();

        // Game loop
//        while (true) {
//
//            System.out.println("Current board:");
//            game.printBoard();
//            System.out.println("Player 1's turn. Enter direction to a move");
//            player1.move(game.board , scanner);
//
//            System.out.println("Current board:");
//            game.printBoard();
//            System.out.println("Player 2's turn. Enter direction to a move");
//            int di2 = scanner.nextInt();
//            player2.move(game.board, scanner);
//
//            // Check for game over condition (for demonstration, you'll need to implement this)
//            // If game over, break out of loop and declare winner
//        }


    }
}