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
public class BingSearch extends SearcherA {

    @Override
    public void setSearcherType() {
        this.searcherType = Query.Searcher.BING;
    }

    @Override
    public JSONObject getResponses(String request) {
        final String biUrl = "bing.com";
        return getResps(biUrl, request);
    }

}
