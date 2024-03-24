package Parser;
import Interfaces.Statement;
import Parser.AST.Plan;
import Tokenizer.*;
import Interfaces.Parser;
import Interfaces.Tokenizer;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ASTParserTest {


    @Test
    public void test01() throws Exception {
        String input = "i = 100\n" +
                "while(i){\n" +
                "invest i\n" +
                "i = i - 2\n" +
                "}\n";
        Tokenizer tokenizer = new ConstructionPlanTokenizer(input);
        List<Token> tokens = tokenizer.getTokens();
        for(Token token:tokens)
            System.out.println(token);
        Parser parser = ASTParser.getInstance();
        Plan plan = parser.parse(tokens);
        for(Statement statement:plan.getStatements()){
            System.out.println(statement);
        }
    }
    @Test
    public void test02() throws Exception {
        String input = "done\n"+"done\n";
        Tokenizer tokenizer = new ConstructionPlanTokenizer(input);
        List<Token> tokens = tokenizer.getTokens();
        for(Token token:tokens)
            System.out.println(token);
        Parser parser = ASTParser.getInstance();
        Plan plan = parser.parse(tokens);
        for(Statement statement:plan.getStatements()){
            System.out.println(statement);
        }
    }
    @Test
    public void test03() throws Exception {
        String input = "invest 300 move up move downleft relocate";
        Tokenizer tokenizer = new ConstructionPlanTokenizer(input);
        List<Token> tokens = tokenizer.getTokens();
        for(Token token:tokens)
            System.out.println(token);
        Parser parser = ASTParser.getInstance();
        Plan plan = parser.parse(tokens);
        for(Statement statement:plan.getStatements()){
            System.out.println(statement);
        }
    }
    @Test
    public void test04() throws Exception {
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
                        "if (budget - 1) then invest 1 else {}\n";
        Tokenizer tokenizer = new ConstructionPlanTokenizer(InputStream);
        List<Token> tokens = tokenizer.getTokens();
        int i = 0;
        for(Token token:tokens)
            System.out.println(i++ +" "+token);
        Parser parser = ASTParser.getInstance();
        Plan plan = parser.parse(tokens);
        for(Statement statement:plan.getStatements()){
            System.out.println(statement);
        }
    }
}