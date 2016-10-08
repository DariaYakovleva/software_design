import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Daria on 08.10.16.
 */
public class Downloader {

    String tag;
    final String requestPref = "https://api.twitter.com/1.1/search/tweets.json?q=%23";
    int count = 5;
    String request = "";
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    OAuthConsumer consumer;

    public Downloader() {}

    public Downloader(Date fromDate, String tag) {
        this.tag = tag;
        request = requestPref + tag + "&since=" + formatter.format(fromDate) + "&count=" + count;
        consumer = new DefaultOAuthConsumer(TagFrequency.consumerKey, TagFrequency.consumerSecret);
        consumer.setTokenWithSecret(TagFrequency.accessToken, TagFrequency.accessTokenSecret);
    }

    String downloadByUrl(String inputUrl) {
        try {
            URL url = new URL(inputUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            consumer.sign(request);
            request.connect();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
                StringBuffer buffer = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    buffer.append(inputLine);
                    buffer.append("\n");
                }
                request.disconnect();
                in.close();
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
            throw new UncheckedIOException(e);
        }
        return null;
    }
    JSONObject getResponse() {
        String output = downloadByUrl(request);
        try {
            return new JSONObject(output);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
