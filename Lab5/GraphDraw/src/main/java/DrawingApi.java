import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Daria on 26.11.2016.
 */
public interface DrawingApi {
    long getDrawingAreaWidth();
    long getDrawingAreaHeight();
    void showFigure();
    void drawCircle(double x, double y, double r, int color);
    void drawLine(double x1, double y1, double x2, double y2);
}
