import org.json.*;
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


    JSONObject data;
    Calendar cal = Calendar.getInstance();
    Downloader downloader = null;
    JSONParser parser = null;

    public TagFrequency() {
        downloader = new Downloader();
        parser = new JSONParser();
    }

    public TagFrequency(Downloader downloader, JSONParser parser) {
        this.downloader = downloader;
        this.parser = parser;
    }

    private List<Long> frequencyCount(List<Tweet> tweets, int hours) {
        Long[] result = new Long[hours];
        for (int i = 0; i < hours; i++) {
            result[i] = (long)0;
        }
        Date curDate = cal.getTime();
        for (Tweet tweet: tweets) {
            int hoursBetween = (int)TimeUnit.MILLISECONDS.toHours(Math.abs(tweet.date.getTime() - curDate.getTime()));
            if (hoursBetween >= hours) continue;
            result[hoursBetween]++;
        }
        return Arrays.asList(result);
    }

    private List<Tweet> getTweets(String tag, int hours) {

        try {
            Date fromDate = cal.getTime();
            fromDate.setTime(fromDate.getTime() - TimeUnit.HOURS.toMillis(hours));
            data = downloader.getResponse(fromDate, tag);
            List<Tweet> tweets = parser.parse(data);
            return tweets;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Long> getFrequency(String tag, int hours) {
        List<Tweet> tweets = getTweets(tag, hours);
        return frequencyCount(tweets, hours);
    }
}
