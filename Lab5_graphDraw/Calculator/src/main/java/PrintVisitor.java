import java.text.ParseException;
import java.util.List;

/**
 * Created by Daria on 15.12.2016.
 */
public class PrintVisitor implements TokenVisitor {


    String result = "";

    private PrintVisitor() {

    }

    public void visit(NumberToken token) {
        result += token.toString() + " ";
    }

    public void visit(Bracket token) {
        result += token.toString() + " ";
    }

    public void visit(Operation token) {
        result += token.toString() + " ";
    }

    private String print(List<Token> tokens) throws ParseException {
        for (Token token: tokens) {
            token.accept(this);
        }
        return result;
    }

    public static String printTokens(List<Token> tokens) throws ParseException {
        return new PrintVisitor().print(tokens);
    }

}
