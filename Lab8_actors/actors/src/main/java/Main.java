import servlet.SearchServlet;

/**
 * Created by Daria on 18.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        RequestHandler handler = new RequestHandler();
        handler.getResponses("russia");
        SearchServlet servlet = new SearchServlet();

    }
}
