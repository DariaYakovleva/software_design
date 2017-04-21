import servlet.RequestHandler;
import servlet.SearchServlet;

import java.io.PrintWriter;

/**
 * Created by Daria on 18.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        RequestHandler handler = new RequestHandler(new PrintWriter(System.out));
        handler.getResponses("russia");
        SearchServlet servlet = new SearchServlet();

    }
}
