package Interfaces;
/**
 * This interface defines the methods that a parser should implement.
 * A parser is responsible for parsing the entire construction plan, one statement at a time.
 */
public interface Parser {
    /**
     * Parses the entire construction plan, one statement at a time.
     */
    public void parse();
}
