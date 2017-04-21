package actors;

import actors.items.Query;
import actors.items.Response;
import akka.actor.UntypedActor;
import searchers.MockSearchers;
import searchers.SearcherA;

/**
 * Created by Daria on 18.03.2017.
 */
public class WorkerActor extends UntypedActor {


    public void onReceive(Object message) {
        if (message instanceof Query) {
            Query query = (Query)message;
            SearcherA searcher = MockSearchers.yaSearcher();
            Query.Searcher searcherName = Query.Searcher.YANDEX;
            switch (query.getSearcher()) {
                case YANDEX:
                    searcher = MockSearchers.yaSearcher();
                    searcherName = Query.Searcher.YANDEX;
                    break;
                case BING:
                    searcher = MockSearchers.biSearcher();
                    searcherName = Query.Searcher.BING;
                    break;
                case GOOGLE:
                    searcher = MockSearchers.goSearcher();
                    searcherName = Query.Searcher.GOOGLE;
                    break;
            }

//        searchers.SearcherA searcher = new searchers.SearcherA(connection);
            Response response = new Response(query.getQuery());
//            System.out.println(query.getQuery() + searcher.getResponses(query.getQuery()).toString());
            response.addResponse(searcherName, searcher.getResponses(query.getQuery()));
            getSender().tell(response, getSelf());
        } else {
            unhandled(message);
        }
    }
}
