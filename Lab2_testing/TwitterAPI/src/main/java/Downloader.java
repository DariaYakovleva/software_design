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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Daria on 08.10.16.
 */
public class Downloader {

    String tag;
    final String requestPref = "https://api.twitter.com/1.1/search/tweets.json?q=%23";
    final String requstSuff = "&since=2016-10-06";//&count=10";
    String request = "";

    OAuthConsumer consumer;

    public Downloader() {}

    public Downloader(Date fromDate, String tag) {
        this.tag = tag;
        request = requestPref + tag + requstSuff;
        consumer = new DefaultOAuthConsumer(TagFrequency.consumerKey, TagFrequency.consumerSecret);
        consumer.setTokenWithSecret(TagFrequency.accessToken, TagFrequency.accessTokenSecret);
    }

    JSONObject getResponse() {
        try {
            URL url = new URL(request);
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
                return new JSONObject(buffer.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
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

}
