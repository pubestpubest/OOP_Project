import static org.junit.Assert.*;
import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.*;
import Interfaces.Player;
import Parser.*;

import Tokenizer.ConstructionPlanTokenizer;
import Exceptions.EvaluationError;
import Exceptions.SyntaxError;
import Interfaces.Parser;
import Interfaces.Tokenizer;
import org.junit.Before;
import org.junit.Test;
import RegionP.Region;
import java.util.Scanner;

// Import other necessary classes
public class PlayerActionsTest {

    private PlayerContext playerContext;
    private Region[][] regions;

    @Before
    public void setUp() {
        // Create a test board
        Board TestBoard = new Board();
        regions = TestBoard.regions; // Implement this method to create a test board
        playerContext = new PlayerContext("TestPlayer", regions);

    }

    @Test
    public void testRelocate() {
        playerContext.setCurrow(3);
        playerContext.setCurcol(4);
        playerContext.move("up");
        playerContext.relocate();

        assertTrue(regions[2][4].isCapital());
    }

    @Test
    public void testMove() {
        playerContext.setCurrow(5);
        playerContext.setCurcol(5);
        playerContext.move("up");
        // Assert that player position is updated correctly
        assertEquals(4, playerContext.getCurrow());
        playerContext.move("down");
        assertEquals(5, playerContext.getCurrow());
        playerContext.move("upright");
        assertEquals(4, playerContext.getCurrow());
        assertEquals(6, playerContext.getCurcol());
        playerContext.move("upright");
        assertEquals(playerContext.getCurrow(), 4);
        assertEquals(playerContext.getCurcol(), 7);
        playerContext.move("downleft");
        assertEquals(playerContext.getCurrow(), 4);
        assertEquals(playerContext.getCurcol(), 6);
        playerContext.move("downleft");
        assertEquals(playerContext.getCurrow(), 5);
        assertEquals(playerContext.getCurcol(), 5);
        playerContext.move("upleft");
        assertEquals(playerContext.getCurrow(), 4);
        assertEquals(playerContext.getCurcol(), 4);
        playerContext.move("upleft");
        assertEquals(playerContext.getCurrow(), 4);
        assertEquals(playerContext.getCurcol(), 3);
        playerContext.move("downright");
        assertEquals(playerContext.getCurrow(), 4);
        assertEquals(playerContext.getCurcol(), 4);
        playerContext.move("downright");
        assertEquals(playerContext.getCurrow(), 5);
        assertEquals(playerContext.getCurcol(), 5);


    }


    @Test
    public void testInvestValidAmount() {
        // ... Set up region with deposit ...
        playerContext.setCurrow(5);
        playerContext.setCurcol(5);
        playerContext.invest(40);
        // Assert that budget is increased, region deposit is decreased
        assertEquals(9960, playerContext.getBudget());
    }

    @Test
    public void testCollectValidAmount() {
        // ... Set up region with deposit ...
        playerContext.setCurrow(5);
        playerContext.setCurcol(5);

        int initialBudget = playerContext.getBudget();
        playerContext.collect(40);
        // Assert that budget is increased, region deposit is decreased
        assertEquals(9999, playerContext.getBudget());
    }
    @Test
    public void getCurrow() {

    }

    @Test
    public void setCurrow() {
    }

    @Test
    public  void getCurcol() {
    }

    @Test
    public void setCurcol() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getBudget() {
    }

    @Test
    public void getVariables() {
    }

    @Test
    public void setVariables() {
    }

    @Test
    public void done() {
    }
    @Test
    public void shoot() {
    }

    @Test
    public void opponent() {
    }

    @Test
    public void nearby() {
//        Region[][] testMap1 ; // Mock map data with city crew and opponent cities
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                testMap1[i][j] = new Region(i, j);
//            }
//        }
//        testMap1[1][1].setOwner("1");
//        testMap1[0][1].setOwner("2");
//        PlayerContext player = new PlayerContext("1",testMap1);
//
//        String direction2 = "up";
//        String expectedOutput2 = "0";
//
//// Use your parsing method to parse the expected output string
//        Tokenizer tokenizer2 = new ConstructionPlanTokenizer("nearby up");
//        Parser parser2 = new ConstructionPlanParser();
//        try{
//            parser2.parse(tokenizer2.getTokens(), player);
//        }catch(SyntaxError | EvaluationError e){
//            System.out.println(e);
//        }
//
//// Call your nearby function with the test data
//
//// Assert that the actual output matches the parsed expected output
//        assertEquals(new ConstructionPlanTokenizer("0"), player.nearby(direction2));
    }

    @Test
    public void testToString() {
    }

    @Test
    public void claimCityCenter() {
    }

    @Test
    public void calculateInterest() {

    }




}
