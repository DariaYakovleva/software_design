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
public class YandexSearch extends SearcherA {

    @Override
    public void setSearcherType() {
        this.searcherType = Query.Searcher.YANDEX;
    }

    @Override
    public JSONObject getResponses(String request) {
        final String yaUrl = "https://yandex.ru/search/xml?user=yakovleva-d-v&key=03.298653504:494507f910d796d3cce5951576a98f9c&query=";
        return getResps(yaUrl, request);
    }

}
