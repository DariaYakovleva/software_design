import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daria on 15.12.2016.
 */
public class Main {


//    Необходимо реализовать программу, которая с консоли считывает входное выражение
//    и выводит в консоль сначала выражение преобразованное в обратную польскую нотации,
//    а затем вычисленное значение выражения. Если было введено некорректное выражение, необходимо вывести ошибку.
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        try {
            List<Token> tokens = Tokenizer.getTokens(expr);
            List<Token> RPNTokens = ParserVisitor.parseToRPN(tokens);
            String str = PrintVisitor.printTokens(tokens);
            System.out.println(str);
            String str2 = PrintVisitor.printTokens(RPNTokens);
            System.out.println(str2);
            int result = CalcVisitor.evaluateExpression(RPNTokens);
            System.out.println("RESULT = " + result);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
