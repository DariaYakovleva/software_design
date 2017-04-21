package actors.items;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daria on 18.03.2017.
 */
public class Response {

    private String query;
    private Map<Query.Searcher, JSONObject> responses;

    public Response(String query) {
        this.query = query;
        responses = new HashMap<Query.Searcher, JSONObject>();
    }

    public String getQuery() {
        return query;
    }

    public void addResponse(Query.Searcher searcher, JSONObject response) {
        responses.put(searcher, response);
    }

    public Map<Query.Searcher, JSONObject> getResponses() {
        return responses;
    }
}
