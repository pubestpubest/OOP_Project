package Parser;
import java.util.HashSet;
import java.util.Set;
public class Keywords {
    public static Set<String> getKeyword() {
        Set<String> words = new HashSet<>();
        words.add("collect");   //chk
        words.add("done");
        words.add("down");
        words.add("downleft");
        words.add("downright");
        words.add("else");
        words.add("if");
        words.add("invest");
        words.add("move");
        words.add("nearby");
        words.add("opponent");
        words.add("relocate");
        words.add("shoot");
        words.add("then");
        words.add("up");
        words.add("upleft");
        words.add("upright");
        words.add("while");
        return words;
    }
    public static Set<String> getCommand(){
        return new HashSet<>(getActionCM());
    }
    public static Set<String> getActionCM(){
        Set<String> word = new HashSet<>();
        word.add("done");
        word.add("relocate");
        word.addAll(getMove());
        word.addAll(getRegion());
        word.addAll(getAttack());
        return word;
    }

    public static Set<String> getMove(){
        Set<String> word = new HashSet<>();
        word.add("move");
        return word;
    }
    public static Set<String> getRegion(){
        Set<String> word = new HashSet<>();
        word.add("invest");
        word.add("collect");
        return word;
    }
    public static Set<String> getAttack(){
        Set<String> word = new HashSet<>();
        word.add("shoot");
        return word;
    }

    public static Set<String> getIfStatement(){
        Set<String> word = new HashSet<>();
        word.add("if");
        word.add("then");
        word.add("else");
        return word;
    }
    public static Set<String> getDirection(){
        Set<String> word = new HashSet<>();
        word.add("up");
        word.add("down");
        word.add("upleft");
        word.add("upright");
        word.add("downright");
        word.add("downleft");
        return word;
    }

    public static Set<String> getWhileStatement(){
        Set<String> word = new HashSet<>();
        word.add("while");
        return word;
    }

    public static Set<String> getInfo(){
        Set<String> word = new HashSet<>();
        word.add("opponent");
        word.add("nearby");
        return word;
    }
    public static Set<String> getSpecialVar(){
        Set<String> word = new HashSet<>();
        word.add("rows");
        word.add("cols");
        word.add("currow");
        word.add("curcol");
        word.add("budget");
        word.add("deposit");
        word.add("int");
        word.add("maxdeposit");
        word.add("random");
        return word;
    }
}