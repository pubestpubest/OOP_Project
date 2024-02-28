package Parser;
import org.junit.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class KeywordsTest {

    @Test
    public void getKeywordTest() {
        System.out.println(Keywords.getKeyword());
    }
    @Test
    public void getCommandTest() {
        System.out.println(Keywords.getCommand());
    }

    @Test
    public void getActionTest() {
        System.out.println(Keywords.getActionCM());
    }
}