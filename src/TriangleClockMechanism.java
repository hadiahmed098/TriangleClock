import javax.swing.*;
import java.awt.*;
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
        Point2D hour = new Point2D.Double(ClockBody.BODY_COORDS[current.get(Calendar.HOUR)].getX(), ClockBody.BODY_COORDS[current.get(Calendar.HOUR)].getY());
        Point2D minute = new Point2D.Double(ClockBody.BODY_COORDS[(int)current.get(Calendar.MINUTE)/5].getX(),ClockBody.BODY_COORDS[current.get(Calendar.MINUTE) /5].getY());
        Point2D second = new Point2D.Double(ClockBody.BODY_COORDS[current.get(Calendar.SECOND) /5].getX(),ClockBody.BODY_COORDS[current.get(Calendar.SECOND) /5].getY());

        g2.setStroke(new BasicStroke(10));

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

    }
}
