import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Daria on 01.10.2016.
 */
public class TagFrequency {


    public static final String consumerKey = "xq2apcICjgF2cJg9Kaa29ASws";
    public static final String consumerSecret = "ht5V1NNBTt7FxvwhuyBxEim2fyPZVv5RP28FsZ1jyYqeRXFYMs";
    public static final String accessToken = "198169876-QA644fBZrrsCnYUDM4YWkxPbj6RBsG6R7nbHJNCZ";
    public static final String accessTokenSecret = "tUf9SAQAHfcDuCQXOi8cox9yyiJHGtu5rv4lePhqQpbFG";
    public static DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.US);

    String tag;
    Integer hours;
    JSONObject data;

    public TagFrequency() {}

    public TagFrequency(String tag, Integer hours) {
        this.tag = tag;
        this.hours = hours;
    }


    List<Integer> getFrequency() {
        Calendar cal = Calendar.getInstance();
        Date fromDate = cal.getTime();
        fromDate.setTime(fromDate.getTime() - TimeUnit.HOURS.toMillis(hours));
        Downloader downloader = new Downloader(fromDate, tag);
        data = downloader.getResponse();
        try {
            JSONParser parser = new JSONParser(data);
            List<Tweet> tweets = parser.parse();
            for (Tweet t: tweets) {

                System.err.println(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
