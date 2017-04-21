package searchers;

import org.json.JSONObject;

import static org.mockito.Mockito.*;
/**
 * Created by Daria on 18.03.2017.
 */
public class MockSearchers {


    static String ans = "russia";
    public static SearcherA yaSearcher() {
        YandexSearch searcher = mock(YandexSearch.class);
        searcher.setSearcherType();
        JSONObject json = new JSONObject("{" +
                "    \"responses\": [\n" +
                "      {\"text\": \"Yandex New\"},\n" +
                "      {\"text\": \"Yandex Open\",},\n" +
                "      {\"text\": \"Yandex Close\",}\n" +
                "    ]\n" +
                "  }\n");
        when(searcher.getResponses(ans)).thenReturn(json);
        return searcher;
    }

    public static SearcherA biSearcher() {
        BingSearch searcher = mock(BingSearch.class);
        searcher.setSearcherType();
        JSONObject json = new JSONObject("{" +
                "    \"responses\": [\n" +
                "      {\"text\": \"Bing New\"},\n" +
                "      {\"text\": \"Bing Open\",},\n" +
                "      {\"text\": \"Bing Close\",}\n" +
                "    ]\n" +
                "  }\n");
        when(searcher.getResponses(ans)).thenReturn(json);
        return searcher;
    }
    public static SearcherA goSearcher() {
        GoogleSearch searcher = mock(GoogleSearch.class);
        searcher.setSearcherType();
        JSONObject json = new JSONObject("{" +
                "    \"responses\": [\n" +
                "      {\"text\": \"Google New\"},\n" +
                "      {\"text\": \"Google Open\",},\n" +
                "      {\"text\": \"Google Close\",}\n" +
                "    ]\n" +
                "  }\n");
        when(searcher.getResponses(ans)).thenReturn(json);
        return searcher;
    }
}
