import java.text.ParseException;

/**
 * Created by Daria on 15.12.2016.
 */
interface Token {

    enum TokenType {ADD, SUB, MUL, DIV, LEFT, RIGHT, NUM}

    void accept(TokenVisitor visitor) throws ParseException;
    public String toString();
    public TokenType getTokenType();

}

