import java.text.ParseException;

/**
 * Created by Daria on 15.12.2016.
 */
interface TokenVisitor {
    void visit(NumberToken token);
    void visit(Bracket token) throws ParseException;
    void visit(Operation token);
}
