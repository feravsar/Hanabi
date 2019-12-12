import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Trace
{
    Circle body;

    public static ArrayList<Trace> tracer = new ArrayList<>();
    public Trace(double x, double y)
    {
        body = new Circle(x,y,8, Color.YELLOW);
        tracer.add(this);
        Main.child.add(this.body);
    }
    public void update()
    {
        this.body.setRadius(this.body.getRadius()-0.30);
        if (this.body.getRadius() <= 0.02)
        {
            Main.child.remove(this.body);
            tracer.remove(this);
        }
    }
}
