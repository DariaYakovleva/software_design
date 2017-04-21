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

import static java.lang.Thread.sleep;

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
        System.err.println("GOOGLE SLEEP");
        try {
            sleep(10000);
            System.err.println("WAKE UP");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final String goUrl = "google.com";
        return getResps(goUrl, request);
    }

}
