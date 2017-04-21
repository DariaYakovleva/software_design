package searchers;

import actors.items.Query;
import org.json.JSONObject;

import java.io.*;

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
