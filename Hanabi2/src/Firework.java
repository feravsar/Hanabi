import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Random;

public class Firework implements IObservable, IFallable
{
    Circle body;
    double x;
    double y;
    Color fillColor;
    Color borderColor;


    double power = Utils.getRandom(0, 15);
    double angle = Utils.getRandom(0, 360);

    int deadCount = 100;

    double xVel = 0;
    double yVel = 0;

    public static ArrayList<Firework> fireworks = new ArrayList<>();

    public Firework(double x, double y, Color color)
    {
        this.fillColor = color;
        borderColor = fillColor.brighter();
        this.x = x;
        this.y = y;
        //
        this.body = new Circle(x, y, 3, fillColor);
        body.setStrokeWidth(0.5);
        body.setStroke(borderColor);
        xVel = power * Math.cos(Utils.angleToRadian(angle));
        yVel = -power * Math.sin(Utils.angleToRadian(angle));
        fireworks.add(this);
    }

    public void update()
    {
        this.x += xVel;
        this.y += yVel;
        //
        this.body.setCenterX(x);
        this.body.setCenterY(y);

        //
        fall();
        this.xVel *= 0.90;
        this.yVel *= 0.90;
        Trace traces = new Trace(this.x,this.y);
        traces.body.setRadius(3);
        traces.body.setFill(borderColor);

        if(deadCount <= -200)
        {
            Main.child.remove(this.getNode());
            fireworks.remove(this);
        }
    }

    @Override
    public void fall()
    {
        this.yVel += 0.01;
        deadCount--;
    }

    @Override
    public Node getNode()
    {
        return this.body;
    }
}
