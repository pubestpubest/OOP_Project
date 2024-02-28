import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.*;
import Tokenizer.*;
import Parser.*;

import java.util.HashMap;
import java.util.Map;
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
        HashMap<String, Integer> map = new HashMap<>();
//        ConstructionPlanTokenizer tokenizeeeer = new ConstructionPlanTokenizer(InputStream);
        ConstructionPlanTokenizer tokenizer = new ConstructionPlanTokenizer(InputStream);
        Parser parser = new ConstructionPlanParser();
        PlayerContext player = new PlayerContext("Player 1");
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

/*
        //Test Player movement
        Board Board1 = new Board(2);
        Player P1 = new Player("Palm");
        Board1.printBoard();

 */
    }
}