import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockBody extends JPanel {

    private JLabel timeString;
    private JLabel dateString;
    private final int BODY_SIZE = 300;

    public ClockBody(Rectangle bounds) {
        setBounds(bounds);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //String with time in pre-made format
        timeString = new JLabel(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        timeString.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeString.setFont(new Font(timeString.getFont().getName(), timeString.getFont().getStyle(),40));

        //String with date in pre-made format
        dateString = new JLabel(new SimpleDateFormat("EEE, d MMM").format(new Date()));
        dateString.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateString.setFont(new Font(dateString.getFont().getName(), dateString.getFont().getStyle(),25));

        add(Box.createRigidArea(new Dimension(0,5)));
        add(timeString);
        add(dateString);

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;

        g2.setStroke(new BasicStroke(5));
        Shape body = new Ellipse2D.Double((getWidth() - BODY_SIZE) / 2,(getHeight() - BODY_SIZE) / 2 ,BODY_SIZE,BODY_SIZE); //Place the body in the X center of the frame
        g2.draw(body);

        //Center Point Drawing
        g2.fillOval(getWidth()/2 - 5, getHeight()/2 - 5,10,10);

        //Hour marker lines for the body
        Shape[] lines = new Shape[60];

        for(int i=0; i < lines.length; i++) {

            double angle = (i * 6) + 90;


            lines[i] = new Line2D.Double(getPointOf(angle,BODY_SIZE/2 - 15, Math.round(getWidth() /2), Math.round(getHeight() /2)),getPointOf(angle,BODY_SIZE/2,Math.round(getWidth() /2), Math.round(getHeight() /2)));
        }

        //Draw each hour marker line
        g2.setStroke(new BasicStroke(1));
        for(Shape s : lines) {
            g2.draw(s);
        }

        g2.setStroke(new BasicStroke(3));
        for(int i=0; i<lines.length; i+=5) {
            g2.draw(lines[i]);
        }


        //not sure why it works, but has something to do with how paintComponent is called
        timeString.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date())); //updates the timeString
        dateString.setText(new SimpleDateFormat("EEE, MMM d").format(new Date())); //updates the dateString
    }


    //Public method to find the coordinates of a point given an angle and radius.
    //Parameter angle MUST be in degrees!
    //Also note that the angle used by cos and sin assumes 0 is on positive x-axis. To use positive y-axis, add 90
    //to every angle fed into method

    //Parameters x and y are used to identify the offsets because math only words if centered on (0,0).
    //Since the JFrame center is not (0,0) use the center as an offset.
    /*
        @see https://stackoverflow.com/questions/35116766/how-to-divide-a-circle-into-60-part-in-java-gui
        @see https://math.stackexchange.com/questions/260096/find-the-coordinates-of-a-point-on-a-circle

     */
    public static Point2D.Double getPointOf(double angle, int radius, int x, int y) {
    //Identify the offsets because math only words if centered on (0,0).
   //Since the JFrame center is not (0,0) use the center as an offset.

        double rads = Math.toRadians(angle); //Math.cos and Math.sin use radians for calculations

        //Reference above links
        double xCoord = (double) (x + Math.cos(rads) * radius);
        double yCoord = (double) (y - Math.sin(rads) * radius);

        return new Point2D.Double(xCoord, yCoord);
    }
}
