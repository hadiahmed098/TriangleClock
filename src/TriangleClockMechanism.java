import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Calendar;

public class TriangleClockMechanism extends JPanel {

    public TriangleClockMechanism (Rectangle bounds) {
        setBounds(bounds);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;

        Calendar current = Calendar.getInstance();
         Point2D hour = ClockBody.getPointOf(-(current.get(Calendar.HOUR)*30 -90),150,  Math.round(getWidth() /2), Math.round(getHeight() /2));
         Point2D minute = ClockBody.getPointOf(-(current.get(Calendar.MINUTE)*6 -90),150,  Math.round(getWidth() /2), Math.round(getHeight() /2));
         Point2D second = ClockBody.getPointOf(-(current.get(Calendar.SECOND)*6 -90),150,  Math.round(getWidth() /2), Math.round(getHeight() /2));

        g2.setStroke(new BasicStroke(10));

        //Create the triangle
        Path2D triangle = new Path2D.Double();
        triangle.moveTo(hour.getX(),hour.getY());
        triangle.lineTo(minute.getX(),minute.getY());
        triangle.lineTo(second.getX(),second.getY());
        triangle.closePath();

        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(triangle);

        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.DARK_GRAY);
        g2.draw(new Line2D.Double(hour,minute));
        g2.draw(new Line2D.Double(hour,second));
        g2.draw(new Line2D.Double(second,minute));

        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(hour.getX()-5,hour.getY()-5,10,10));

        g2.setColor(new Color(0,212,0));
        g2.fill(new Ellipse2D.Double(minute.getX()-5,minute.getY()-5,10,10));

        g2.setColor(Color.BLUE);
        g2.fill(new Ellipse2D.Double(second.getX()-5,second.getY()-5,10,10));

        //Center Point Drawing
        g2.setColor(Color.BLACK);
        g2.fillOval(getWidth()/2 - 5, getHeight()/2 - 5,10,10);
    }
}
