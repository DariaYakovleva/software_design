/**
 * Created by Daria on 15.12.2016.
 */
public class Operation implements Token {

    private TokenType type;
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    public Operation(char exp) {
        switch (exp) {
            case '+':
                type = TokenType.ADD;
                break;
            case '-':
                type = TokenType.SUB;
                break;
            case '*':
                type = TokenType.MUL;
                break;
            case '/':
                type = TokenType.DIV;
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
