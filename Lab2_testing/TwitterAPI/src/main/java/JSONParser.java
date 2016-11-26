import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Daria on 08.10.16.
 */
public class JSONParser {

    public JSONParser() {}

    public List<Tweet> parse(JSONObject data) throws JSONException, ParseException {
        List<Tweet> tweets = new ArrayList<>();
        JSONArray arr = data.getJSONArray("statuses");
        for (int i = 0; i < arr.length(); i++) {
            String text = arr.getJSONObject(i).get("text").toString();
            String strDate = arr.getJSONObject(i).get("created_at").toString();
            Date date = TagFrequency.formatter.parse(strDate);
            tweets.add(new Tweet(text, date));
        }
        return tweets;
    }

}
