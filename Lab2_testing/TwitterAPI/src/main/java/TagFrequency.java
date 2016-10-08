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

    Integer hours;
    String tag;
    JSONObject data;
    Calendar cal = Calendar.getInstance();

    public TagFrequency() {}

    public TagFrequency(String tag, int hours) {
        this.tag = tag;
        this.hours = hours;
    }

    Integer getHours() {
        return hours;
    }
    String getTag() {
        return tag;
    }

    List<Long> frequencyCount(List<Tweet> tweets) {
        Long[] result = new Long[getHours()];
        for (int i = 0; i < getHours(); i++) {
            result[i] = (long)0;
        }
        Date curDate = cal.getTime();
        for (Tweet tweet: tweets) {
            int hoursBetween = (int)TimeUnit.MILLISECONDS.toHours(Math.abs(tweet.date.getTime() - curDate.getTime()));
            if (hoursBetween >= getHours()) continue;
            result[hoursBetween]++;
        }
        return Arrays.asList(result);
    }

    List<Tweet> getTweets() {
        Date fromDate = cal.getTime();
        fromDate.setTime(fromDate.getTime() - TimeUnit.HOURS.toMillis(getHours()));
        Downloader downloader = new Downloader(fromDate, getTag());
        data = downloader.getResponse();
        try {
            JSONParser parser = new JSONParser(data);
            List<Tweet> tweets = parser.parse();
            return tweets;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Long> getFrequency() {
        List<Tweet> tweets = getTweets();
        return frequencyCount(tweets);
    }
}
