/**
 * Created by Daria on 15.12.2016.
 */
public class NumberToken implements Token {


    private int number = 0;
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public NumberToken(String exp) {
        number = Integer.parseInt(exp);
    }

    public String toString() {
        return "NUMBER(" + number + ")";
    }

    public TokenType getTokenType() {
        return TokenType.NUM;
    }

    public int getValue() {
        return number;
    }


}
