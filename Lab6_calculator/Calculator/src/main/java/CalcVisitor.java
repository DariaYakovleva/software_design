import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Daria on 15.12.2016.
 */
public class CalcVisitor implements TokenVisitor {

    private Stack<Integer> stack = new Stack<Integer>();

    public void visit(NumberToken token) {
        stack.add(token.getValue());
    }

    public void visit(Bracket token) {

    }

    public void visit(Operation token) {
        int num1 = stack.pop();
        int num2 = stack.pop();
        if (token.getTokenType() == Token.TokenType.ADD) {
            stack.add(num1 + num2);
        } else if (token.getTokenType() == Token.TokenType.SUB) {
            stack.add(num2 - num1);
        } else if (token.getTokenType() == Token.TokenType.MUL) {
            stack.add(num1 * num2);
        } else if (token.getTokenType() == Token.TokenType.DIV) {
            stack.add(num2 / num1);
        }
    }


    private int evaluate(List<Token> tokens) throws ParseException {
        for (Token token: tokens) {
            token.accept(this);
        }
        return stack.pop();
    }

    public static int evaluateExpression(List<Token> tokens) throws ParseException {
        return new CalcVisitor().evaluate(tokens);
    }
}
