package servlet;

import actors.items.Query;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import actors.*;

import java.io.PrintWriter;

/**
 * Created by Daria on 18.03.2017.
 */
public class RequestHandler {

    PrintWriter out;

    public RequestHandler(PrintWriter out) {
        this.out = out;
    }


    public String getResponses(String query) {

        ActorSystem system = ActorSystem.create("QuerySystem");

        ActorRef master = system.actorOf(Props.create(MasterActor.class, query, out), "master");
        for (int i = 0; i < MasterActor.WORKERSCOUNT; i++) {
            master.tell(new Query(Query.Searcher.values()[i], query), ActorRef.noSender());
        }

        return null;
    }
}
