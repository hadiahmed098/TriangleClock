import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockBody extends JPanel {

    private JLabel timeString;
    private JLabel dateString;
    private final int BODY_SIZE = 300;

    //Mathematical Offsets for positioning the hour markers on the clock body
    //    Found by drawing a circle, A, with radius of 77.646 centered on the hour marker
    //    and seeing where it intersects a circle, B, of radius 150 centered on the origin.
    //    By subtracting the coordinates of the center of A with the intersections of A and B, you find OFFSET_A and OFFSET_B.
    //    77.646 was found by using Law of Cosines to solve an SAS triangle where the Sides are 150 and the Angle is 30.
    private final double OFFSET_A = 74.837;
    private final double OFFSET_B = 20.002;
    //Offsets for end of hour markers
    private final double OFFSET_A_END = 9.978;
    private final double OFFSET_B_END = 17.333;

    public static ClockBodyPair[] BODY_COORDS = new ClockBodyPair[12];

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

        //Array with pairs of coordinates representing the x and y of the hour markers where 12=[0],1=[1],2=[2],...
        //using the offsets found above.
        BODY_COORDS = new ClockBodyPair[]{
                new ClockBodyPair(getWidth() / 2, (getHeight() - BODY_SIZE) / 2.0),
                new ClockBodyPair((getWidth() / 2.0) + OFFSET_A, ((getHeight() - BODY_SIZE) / 2.0) + OFFSET_B),
                new ClockBodyPair(((getWidth() + BODY_SIZE) / 2.0) - OFFSET_B, (getHeight() / 2.0) - OFFSET_A),
                new ClockBodyPair((getWidth() + BODY_SIZE) / 2.0, getHeight() / 2),
                new ClockBodyPair(((getWidth() + BODY_SIZE) / 2.0) - OFFSET_B, (getHeight() / 2.0) + OFFSET_A),
                new ClockBodyPair((getWidth() / 2.0) + OFFSET_A, ((getHeight() + BODY_SIZE) / 2.0) - OFFSET_B),
                new ClockBodyPair(getWidth() / 2, (getHeight() + BODY_SIZE) / 2.0),
                new ClockBodyPair((getWidth() / 2.0) - OFFSET_A, ((getHeight() + BODY_SIZE) / 2.0) - OFFSET_B),
                new ClockBodyPair(((getWidth() - BODY_SIZE) / 2.0) + OFFSET_B, (getHeight() / 2.0) + OFFSET_A),
                new ClockBodyPair((getWidth() - BODY_SIZE) / 2.0, getHeight() / 2),
                new ClockBodyPair(((getWidth() - BODY_SIZE) / 2.0) + OFFSET_B, (getHeight() / 2.0) - OFFSET_A),
                new ClockBodyPair((getWidth() / 2.0) - OFFSET_A, ((getHeight() - BODY_SIZE) / 2.0) + OFFSET_B),
        };
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
        Shape[] lines = new Shape[12];

        lines[0] = new Line2D.Double(BODY_COORDS[0].getX(),BODY_COORDS[0].getY(),getWidth() / 2,((getHeight() - BODY_SIZE) / 2.0) + 20);
        lines[1] = new Line2D.Double(BODY_COORDS[1].getX(),BODY_COORDS[1].getY(),BODY_COORDS[1].getX()-OFFSET_A_END,BODY_COORDS[1].getY()+OFFSET_B_END);
        lines[11] = new Line2D.Double(BODY_COORDS[11].getX(),BODY_COORDS[11].getY(),BODY_COORDS[11].getX()+OFFSET_A_END,BODY_COORDS[11].getY()+OFFSET_B_END);

        lines[3] = new Line2D.Double(BODY_COORDS[3].getX(),BODY_COORDS[3].getY(),((getWidth() + BODY_SIZE) / 2.0) - 20, getHeight() / 2);
        lines[2] = new Line2D.Double(BODY_COORDS[2].getX(),BODY_COORDS[2].getY(),BODY_COORDS[2].getX()-OFFSET_B_END,BODY_COORDS[2].getY()+OFFSET_A_END);
        lines[4] = new Line2D.Double(BODY_COORDS[4].getX(),BODY_COORDS[4].getY(),BODY_COORDS[4].getX()-OFFSET_B_END,BODY_COORDS[4].getY()-OFFSET_A_END);

        lines[6] = new Line2D.Double(BODY_COORDS[6].getX(),BODY_COORDS[6].getY(),getWidth() / 2,((getHeight() + BODY_SIZE) / 2.0) - 20);
        lines[5] = new Line2D.Double(BODY_COORDS[5].getX(),BODY_COORDS[5].getY(),BODY_COORDS[5].getX()-OFFSET_A_END,BODY_COORDS[5].getY()-OFFSET_B_END);
        lines[7] = new Line2D.Double(BODY_COORDS[7].getX(),BODY_COORDS[7].getY(),BODY_COORDS[7].getX()+OFFSET_A_END,BODY_COORDS[7].getY()-OFFSET_B_END);

        lines[9] = new Line2D.Double(BODY_COORDS[9].getX(),BODY_COORDS[9].getY(),((getWidth() - BODY_SIZE) / 2.0) + 20, getHeight() / 2);
        lines[8] = new Line2D.Double(BODY_COORDS[8].getX(),BODY_COORDS[8].getY(),BODY_COORDS[8].getX()+OFFSET_B_END,BODY_COORDS[8].getY()-OFFSET_A_END);
        lines[10] = new Line2D.Double(BODY_COORDS[10].getX(),BODY_COORDS[10].getY(),BODY_COORDS[10].getX()+OFFSET_B_END,BODY_COORDS[10].getY()+OFFSET_A_END);

        //Draw each hour marker line
        g2.setStroke(new BasicStroke(3));
        for(Shape s : lines) {
            g2.draw(s);
        }


        //not sure why it works, but has something to do with how paintComponent is called
        timeString.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date())); //updates the timeString
        dateString.setText(new SimpleDateFormat("EEE, MMM d").format(new Date())); //updates the dateString
    }
}
