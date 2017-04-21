package searchers;

import actors.items.Query;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import static java.lang.Thread.sleep;

/**
 * Created by Daria on 18.03.2017.
 */
public abstract class SearcherA {

    public SearcherA() {
        setSearcherType();
    }

    Query.Searcher searcherType;

    public void setSearcherType() {
    }


    public Query.Searcher getSearcherType() {
        return searcherType;
    }

    protected JSONObject getResps(final String url1, String request) {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            request = URLEncoder.encode(request, Charset.defaultCharset().name());
            final URL url = new URL(url1 + request);
            final URLConnection connection = url.openConnection();
            JSONObject res = getResponse(connection.getInputStream());
            return res;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getResponse(InputStream input) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String inputLine;
            final StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return new JSONObject(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getResponses(String request) {
        return null;
    }
}
