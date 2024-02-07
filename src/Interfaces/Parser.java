package Interfaces;
import java.util.List;

import Exceptions.EvaluationError;
import Exceptions.SynataxError;
import Tokenizer.Token;
/**
 * This interface defines the methods that a parser should implement.
 * A parser is responsible for parsing the entire construction plan, one statement at a time.
 */
public interface Parser {
    /**
     * Parses the entire construction plan, one statement at a time.
     */
    public void parse(List<Token> tokens, Player player) throws EvaluationError, SynataxError;
}
