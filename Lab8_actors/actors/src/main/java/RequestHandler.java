import actors.items.Query;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import actors.*;

/**
 * Created by Daria on 18.03.2017.
 */
public class RequestHandler {

    public RequestHandler() {

    }


    public String getResponses(String query) {

        ActorSystem system = ActorSystem.create("QuerySystem");

        ActorRef master = system.actorOf(Props.create(MasterActor.class, query), "master");
        for (int i = 0; i < MasterActor.WORKERSCOUNT; i++) {
            master.tell(new Query(Query.Searcher.values()[i], query), ActorRef.noSender());
        }

        return null;
    }
}
