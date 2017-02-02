import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 26.11.2016.
 */
public class JavaFxDrawing extends Application implements DrawingApi {
    private static final long W = 1000;
    private static final long H = 600;
    static List<MyLine> lines = new ArrayList<MyLine>();
    static List<MyCircle> circles = new ArrayList<MyCircle>();
    private static Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};


    public JavaFxDrawing() {
    }

    public long getDrawingAreaWidth() {
        return W;
    }

    public long getDrawingAreaHeight() {
        return H;
    }

    public void drawCircle(double x, double y, double r, int color) {
        System.out.println("FX: circle x = " + x + ", y = " + y + ", r = " + r);
        circles.add(new MyCircle(x, y, r, color));
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        System.out.println("FX: line (" + x1 + ", " + y1 + ") to (" + x2 + ", " + y2 + ")");
        lines.add(new MyLine(x1, y1, x2, y2));
    }

    public void drawLine(GraphicsContext gc, MyLine line) {
        gc.beginPath();
        gc.moveTo(line.x1, line.y1);
        gc.lineTo(line.x2, line.y2);
        gc.stroke();
        gc.closePath();
    }

    public void drawCircle(GraphicsContext gc, MyCircle circle) {
        gc.setFill(circle.color);
        gc.fillOval(circle.x, circle.y, circle.r, circle.r);
    }

    public void showFigure() {
        Application.launch();
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing graph uses javafx");
        Group root = new Group();
        Canvas canvas = new Canvas(W, H);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        for (MyLine l: lines) {
            drawLine(gc, l);
        }
        for (MyCircle c: circles) {
            drawCircle(gc, c);
        }
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    private class MyLine {
        public double x1, x2, y1, y2;
        MyLine(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
        @Override
        public String toString() {
            return x1 + " " + y1 + " " + x2 + " " + y2;
        }
    }
    private class MyCircle {
        public double x, y, r;
        Color color;
        MyCircle(double x, double y, double r, int color) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.color = colors[color % colors.length];
        }
        @Override
        public String toString() {
            return x + " " + y + " " + r;
        }
    }


}
