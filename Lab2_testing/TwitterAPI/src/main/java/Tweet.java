import java.util.Date;

/**
 * Created by Daria on 01.10.2016.
 */
public class Tweet {
    String text;
    Date date;
    public Tweet() {}
    public Tweet(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    @Override
    public String toString() {
        return date.toString() + " " + text;
    }
}
