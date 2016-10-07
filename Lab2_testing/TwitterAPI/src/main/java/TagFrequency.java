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

/**
 * Created by Daria on 01.10.2016.
 */
public class TagFrequency {

    String tag;
    Integer hours;
    final String consumerKey = "xq2apcICjgF2cJg9Kaa29ASws";
    final String consumerSecret = "ht5V1NNBTt7FxvwhuyBxEim2fyPZVv5RP28FsZ1jyYqeRXFYMs";
    final String accessToken = "198169876-QA644fBZrrsCnYUDM4YWkxPbj6RBsG6R7nbHJNCZ";
    final String accessTokenSecret = "tUf9SAQAHfcDuCQXOi8cox9yyiJHGtu5rv4lePhqQpbFG";
    DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.US);
    public TagFrequency() {}

    public TagFrequency(String tag, Integer hours) {
        this.tag = tag;
        this.hours = hours;
    }

    String getResponse(OAuthConsumer consumer, String stringRequest) {
        try {
            URL url = new URL(stringRequest);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            consumer.sign(request);
            request.connect();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
                StringBuffer buffer = new StringBuffer();
                String inputLine;

                while((inputLine=in.readLine()) != null) {
                    buffer.append(inputLine);
                    buffer.append("\n");
                }
                return buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    List<Tweet> getTweets(String data) {
        List<Tweet> tweets = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray arr = obj.getJSONArray("statuses");
            for (int i = 0; i < arr.length(); i++) {
                String text = arr.getJSONObject(i).get("text").toString();
                String strDate = arr.getJSONObject(i).get("created_at").toString();
                Date date = formatter.parse(strDate);
                tweets.add(new Tweet(text, date));
            }
            return tweets;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    List<Integer> getFrequency() {
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        OAuthConsumer consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        String data = getResponse(consumer, "https://api.twitter.com/1.1/search/tweets.json?q=%23" + tag +
                "&since=2016-09-28&count=10");
        List<Tweet> tweets = getTweets(data);
        for (Tweet t: tweets) {
            System.err.println(t);
        }
        currentDate.
        return null;
    }
}
