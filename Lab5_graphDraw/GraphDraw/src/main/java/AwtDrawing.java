import javafx.util.Pair;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

/**
 * Created by Daria on 26.11.2016.
 */
public class AwtDrawing extends Frame implements DrawingApi {
    final static int W = 1000;
    final static int H = 600;
    static List<Line2D> lines = new ArrayList<Line2D>();
    static List<Pair<Ellipse2D, Color>> circles = new ArrayList<Pair<Ellipse2D, Color>>();
    private static Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.magenta};

    public AwtDrawing() {
    }

    public long getDrawingAreaWidth() {
        return W;
    }

    public long getDrawingAreaHeight() {
        return H;
    }
    public void init() {

    }
    public void drawCircle(double x, double y, double r, int color) {
        System.out.println("AWT: draw circle");
        circles.add(new Pair(new Ellipse2D.Double(x, y, r, r), colors[color % colors.length]));
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        System.out.println("AWT: draw line");
        lines.add(new Line2D.Double(x1, y1, x2 , y2));
    }

    public void showFigure() {
        System.out.println("AWT: show");
        Frame frame = new AwtDrawing();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("AWT: paint");
        Graphics2D ga = (Graphics2D)g;
        ga.setPaint(Color.black);
        for (Line2D l: lines) {
            ga.draw(l);
        }
        for (Pair<Ellipse2D, Color> e: circles) {
            ga.setPaint(e.getValue());
            ga.fill(e.getKey());
        }
    }
}
