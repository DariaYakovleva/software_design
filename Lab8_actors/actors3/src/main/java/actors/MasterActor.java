package actors;

import actors.items.Query;
import actors.items.Response;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import org.json.JSONArray;
import org.json.JSONObject;
import scala.concurrent.duration.Duration;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Daria on 18.03.2017.
 */
public class MasterActor extends  UntypedActor {

    private final long start = System.currentTimeMillis();
    public static int WORKERSCOUNT = Query.Searcher.values().length;
    private final ActorRef workerRouter;
    private String query;
    private int resultCount = 0;
    private Response response;

    PrintWriter out;

    public MasterActor(String query, PrintWriter out) {
        this.query = query;
        this.out = out;
        response = new Response(query);
        workerRouter = getContext().actorOf(new RoundRobinPool(WORKERSCOUNT).props(Props.create(WorkerActor.class)),
                "workerRouter");
        getContext().setReceiveTimeout(Duration.create("5 seconds"));
    }

    public void onReceive(Object message) {
        if (message instanceof Query) {
                Query mess = (Query) message;
                workerRouter.tell(new Query(mess.getSearcher(), mess.getQuery()), getSelf());
        } else if (message instanceof Response) {
            Response result = (Response) message;
            Map<Query.Searcher, JSONObject> res = result.getResponses();
            for (int i = 0; i < WORKERSCOUNT; i++) {
                Query.Searcher value = Query.Searcher.values()[i];
                if (res.containsKey(value)) {
                    if (!response.getResponses().containsKey(value)) {
                        resultCount++;
                        System.err.println("RECEIVE " + value);
                        response.addResponse(value, res.get(value));
                    }
                }
            }
            if (resultCount == 3) {
                Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);

                Map<Query.Searcher, JSONObject> resps = response.getResponses();
                out.println("Your query: " + query);
                for (int i = 0; i < WORKERSCOUNT; i++) {
                    Query.Searcher value = Query.Searcher.values()[i];
                    JSONObject resp = resps.get(value);
                    final JSONArray results = resp.getJSONArray("responses");
                    final int resultsLength = results.length();
                    out.println(value + " RETURN:");
                    for (int j = 0; j < resultsLength; j++) {
                        final JSONObject aResult = results.getJSONObject(j);
                        out.println(aResult.get("text"));
                    }
                }
//                out.close();
//                getContext().system().terminate();
            } else if (message instanceof ReceiveTimeout) {
                System.err.println("MASTER TIMEOUT");
                out.println("TIMEOUT:(");
                getContext().system().terminate();
            } else {
                unhandled(message);
            }
        }
    }
}
