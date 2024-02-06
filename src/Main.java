import Tokenizer.ConstructionPlanTokenizer;
import Tokenizer.Token;
public class Main {
    public static void main(String[] args) {
        String InputStream ="Lorem ipsum dolor+- sit amet, consectetur \n#adipiscing";
        ConstructionPlanTokenizer tokenizer = new ConstructionPlanTokenizer(InputStream);
        for (Token token : tokenizer.getTokens()) {
            System.out.println(token);
        }
    }
}