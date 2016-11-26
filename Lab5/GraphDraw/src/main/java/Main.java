import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daria on 26.11.2016.
 */
public class Main {

    public static void main(String[] args) {
        String api = args[0]; //api
        String graphType = args[1]; //matrix or adj_list
        System.out.println("Write your graph:");
        DrawingApi drawingApi;
        Graph graph;
        Scanner in = new Scanner(System.in);
        if (api.equals("javafx")) {
            drawingApi = new JavaFxDrawing();
        } else {
            drawingApi = new AwtDrawing();
        }
        if (graphType.equals("adj")) {
            graph = new AdjListGraph(drawingApi);
        } else {
            graph = new MatrixGraph(drawingApi);
        }
        graph.read(in);
        graph.drawGraph();
    }

}

