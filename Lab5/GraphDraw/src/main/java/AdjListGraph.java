import java.util.*;

/**
 * Created by Daria on 26.11.2016.
 */
public class AdjListGraph extends Graph {

    public AdjListGraph(DrawingApi drawingApi) {
        super(drawingApi);
    }

    public void read(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                int to = in.nextInt();
                to--;
                graph.get(i).add(to);
            }
        }
    }
}
