import Tokenizer.ConstructionPlanTokenizer;
import Tokenizer.Token;
public class Main {
    public static void main(String[] args) {
        String InputStream ="Lorem ipsum dolor sit amet, consectetur adipiscing";
        ConstructionPlanTokenizer tokenizer = new ConstructionPlanTokenizer(InputStream);
        Token token;
        while ((token = tokenizer.getNextToken()) != null) {
            System.out.println(token);
        }
    }
}