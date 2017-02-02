import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 15.12.2016.
 */

public class Tokenizer {


    private Tokenizer() {

    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isSpace(char c) {
        return c == ' ';
    }

    private boolean isOpers(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isBracket(char c) {
        return c == '(' || c == ')';
    }

    private List<Token> parse(String expression) throws ParseException {
        List<Token> result = new ArrayList<Token>();
        int len = expression.length();
        for (int pos = 0; pos < len; pos++) {
            char cur = expression.charAt(pos);
            if (isSpace(cur)) continue;
            if (isDigit(cur)) {
                int cpos = pos;
                String num = "";
                while (cpos < len && isDigit(expression.charAt(cpos))) {
                    num += expression.charAt(cpos);
                    cpos++;
                }
                result.add(new NumberToken(num));
                pos = cpos - 1;
            } else if (isOpers(cur)) {
                result.add(new Operation(cur));
            } else if (isBracket(cur)) {
                result.add(new Bracket(cur));
            } else {
                throw new ParseException("can't parse expression, bad symbol is " + cur + " on position " + pos, 1);
            }
        }
        return result;
    }

    public static List<Token> getTokens(String expr) throws ParseException {
        return new Tokenizer().parse(expr);
    }
}
