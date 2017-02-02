import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Daria on 15.12.2016.
 */
public class ParserVisitor implements  TokenVisitor {

    List<Token> result = new ArrayList<Token>();
    Stack<Token> stack = new Stack<Token>();

    public void visit(NumberToken token) {
        result.add(token);
    }

    public void visit(Bracket token) throws ParseException {
        if (token.getTokenType() == Token.TokenType.LEFT) {
            stack.add(token);
        } else {
            while (!stack.isEmpty() && stack.peek().getTokenType() != Token.TokenType.LEFT) {
                result.add(stack.pop());
            }
            if (stack.isEmpty()) {
                throw new ParseException("LEFT BRACKET EXPECTED, FOUND NOTHING", 1);
            }
            stack.pop();
        }
    }

    private boolean isOpers(Token.TokenType type) {
        return (type == Token.TokenType.ADD || type == Token.TokenType.SUB ||
                type == Token.TokenType.MUL || type == Token.TokenType.DIV);
    }

    public void visit(Operation token) {
        while (!stack.isEmpty() && (token.getTokenType() == Token.TokenType.ADD ||
                token.getTokenType() == Token.TokenType.SUB) && (isOpers(stack.peek().getTokenType()))) {
            result.add(stack.pop());
        }
        stack.add(token);
    }

    private List<Token> parse(List<Token> tokens) throws ParseException {
        for (Token token: tokens) {
            token.accept(this);
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static List<Token> parseToRPN(List<Token> tokens) throws ParseException {
        return new ParserVisitor().parse(tokens);
    }
}
