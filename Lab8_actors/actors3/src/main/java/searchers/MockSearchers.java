package searchers;

import actors.items.Query;
import org.json.JSONObject;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
/**
 * Created by Daria on 18.03.2017.
 */
public class MockSearchers {


    public static void addMock(final SearcherA searcher, final String name) {
        when(searcher.getResponses(anyString())).thenAnswer(new Answer<JSONObject>() {
            public JSONObject answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                String query = (String) args[0];
                JSONObject json = new JSONObject("{" +
                        "    \"responses\": [\n" +
                        "      {\"text\": \"" + name + " response1 = " + query + "\"},\n" +
                        "      {\"text\": \"" + name + " response2 = " + query + "\"},\n" +
                        "      {\"text\": \"" + name + " response3 = " + query + "\"},\n" +
                        "      {\"text\": \"" + name + " response4 = " + query + "\"},\n" +
                        "      {\"text\": \"" + name + " response5 = " + query + "\"},\n" +
                        "    ]\n" +
                        "  }\n");
                return json;
            }
        });
    }

    public static SearcherA yaSearcher() {
        YandexSearch searcher = mock(YandexSearch.class);
        searcher.setSearcherType();
        addMock(searcher, "YANDEX");
        return searcher;
    }

    public static SearcherA biSearcher() {
        final BingSearch searcher = mock(BingSearch.class);
        searcher.setSearcherType();
        addMock(searcher, "BING");
        return searcher;
    }
    public static SearcherA goSearcher() {
        GoogleSearch searcher = mock(GoogleSearch.class);
        searcher.setSearcherType();
        addMock(searcher, "GOOGLE");
        return searcher;
    }
}
