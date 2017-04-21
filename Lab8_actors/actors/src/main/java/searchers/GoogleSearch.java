package searchers;

import actors.items.Query;
import org.json.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Created by Daria on 18.03.2017.
 */
public class GoogleSearch extends SearcherA {

    @Override
    public void setSearcherType() {
        this.searcherType = Query.Searcher.GOOGLE;
    }

    @Override
    public JSONObject getResponses(String request) {
        final String goUrl = "google.com";

        try {
            request = URLEncoder.encode(request, Charset.defaultCharset().name());
            final URL url = new URL(goUrl + request);
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

}
