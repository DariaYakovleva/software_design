import java.text.ParseException;

/**
 * Created by Daria on 15.12.2016.
 */
public class Bracket implements Token {


    TokenType type;

    public void accept(TokenVisitor visitor) throws ParseException {
        visitor.visit(this);
    }

    public Bracket(char exp) {
        switch (exp) {
            case '(':
                type = TokenType.LEFT;
                break;
            case ')':
                type = TokenType.RIGHT;
                break;
        }
    }

    public String toString() {
        return type.toString();
    }

    public TokenType getTokenType() {
        return type;
    }
}
