import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daria on 26.11.2016.
 */
public class MatrixGraph extends Graph {

    public MatrixGraph(DrawingApi drawingApi) {
        super(drawingApi);
    }

    public void read(Scanner in) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
            for (int j = 0; j < n; j++) {
                int cur = in.nextInt();
                if (cur == 1) {
                    graph.get(i).add(j);
                }
            }
        }
        System.out.println(n);
        for (List<Integer> lst: graph) {
            for (Integer v: lst) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
