import javafx.util.Pair;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Daria on 26.11.2016.
 */
public abstract class Graph {

    /**
     * Bridge to drawing api
     */
    protected DrawingApi drawingApi;
    protected List<List<Integer>> graph;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
        graph = new ArrayList<List<Integer>>();
    }

    public Graph(DrawingApi drawingApi, List<List<Integer>> graph) {
        this.drawingApi = drawingApi;
        this.graph = graph;
    }

    public abstract void read(Scanner in);

    private final double stX = 300, stY = 300;
    private final double r = 30;

    public void drawGraph() {
        int n = graph.size();
        boolean[] used = new boolean[n];
        int[] dist = new int[n];
        int[] prev = new int[n];
        Arrays.fill(used, false);
        Arrays.fill(dist, -1);
        Arrays.fill(prev, -1);
        for (int i = 0; i < n; i++) {
            if (!used[i]) bfs(i, used, dist, prev);
        }
        for (int v = 0; v < n; v++) {
            Pair<Double, Double> point = getCoordinate(v, dist);
            double vX = point.getKey(), vY = point.getValue();
            drawingApi.drawCircle(vX, vY, r, dist[v]);
            for (Integer to: graph.get(v)) {
                Pair<Double, Double> point2 = getCoordinate(to, dist);
                double toX = point2.getKey(), toY = point2.getValue();
                drawingApi.drawLine(vX + r / 2, vY + r / 2, toX + r / 2, toY + r / 2);
            }
        }
        drawingApi.showFigure();
    }

    Pair<Double, Double> getCoordinate(int v, int[] dist) {
        //x = x0 + R cos fi
        //y = y0 + R sin fi, fi = 0..2pi
        final int x = dist[v];
        double all = (double)IntStream.of(dist).filter(a -> a == x).count();
        double cnt = IntStream.of(dist).limit(v).filter(a -> a == x).count();
        double phi = 2 * Math.PI / all * cnt;
        double vX = stX + dist[v] * 2 * r * Math.cos(phi), vY = stY + dist[v] * 2 * r * Math.sin(phi);
        Pair<Double, Double> res = new Pair<>(vX, vY);
        return res;
    }

    void bfs(int s, boolean[] used, int[] dist, int[] prev) {
        Queue<Integer> q = new LinkedList<Integer>();
        int st = 0;
        q.add(st);
        used[st] = true;
        dist[st] = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            for (Integer to: graph.get(v)) {
                if (!used[to]) {
                    q.add(to);
                    used[to] = true;
                    dist[to] = dist[v] + 1;
                    prev[to] = v;
                }
            }
        }
    }
}
