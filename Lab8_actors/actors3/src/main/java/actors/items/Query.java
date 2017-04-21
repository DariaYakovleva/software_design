package actors.items;

/**
 * Created by Daria on 18.03.2017.
 */
public class Query {

    public enum Searcher {YANDEX, BING, GOOGLE};

    private String query;
    private Searcher searcher;

    public Query(Searcher searcher, String query)
    {
        this.searcher = searcher;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public Searcher getSearcher() {
        return searcher;
    }
}
