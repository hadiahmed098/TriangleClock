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
    private final double OFFSET_A = 74.837;
    private final double OFFSET_B = 20.002;
    private final double OFFSET_A_END = 9.978;
    private final double OFFSET_B_END = 17.333;

    public ClockBodyPair[] BODY_COORDS = new ClockBodyPair[12];

    public ClockBody() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        timeString = new JLabel(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        timeString.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeString.setFont(new Font(timeString.getFont().getName(), timeString.getFont().getStyle(),45));

        dateString = new JLabel(new SimpleDateFormat("EEE, d MMM").format(new Date()));
        dateString.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateString.setFont(new Font(dateString.getFont().getName(), dateString.getFont().getStyle(),30));

        add(Box.createRigidArea(new Dimension(10,10)));
        add(timeString);
        add(Box.createRigidArea(new Dimension(0,330)));
        add(dateString);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;

        //Create a set of standard locations every redraw for the hour markers
        ClockBodyPair[] internalCoords = {
                new ClockBodyPair(getWidth() / 2, (getHeight() - BODY_SIZE) / 2.0),
                new ClockBodyPair((getWidth() / 2.0)+OFFSET_A,((getHeight() - BODY_SIZE) / 2.0)+OFFSET_B),
                new ClockBodyPair(((getWidth() + BODY_SIZE) / 2.0)-OFFSET_B,(getHeight() / 2.0)-OFFSET_A),
                new ClockBodyPair((getWidth() + BODY_SIZE) / 2.0, getHeight() / 2),
                new ClockBodyPair(((getWidth() + BODY_SIZE) / 2.0)-OFFSET_B,(getHeight() / 2.0)+OFFSET_A),
                new ClockBodyPair((getWidth() / 2.0)+OFFSET_A,((getHeight() + BODY_SIZE) / 2.0)-OFFSET_B),
                new ClockBodyPair(getWidth() / 2,(getHeight() + BODY_SIZE) / 2.0),
                new ClockBodyPair((getWidth() / 2.0)-OFFSET_A,((getHeight() + BODY_SIZE) / 2.0)-OFFSET_B),
                new ClockBodyPair(((getWidth() - BODY_SIZE) / 2.0)+OFFSET_B,(getHeight() / 2.0)+OFFSET_A),
                new ClockBodyPair((getWidth() - BODY_SIZE) / 2.0, getHeight() / 2),
                new ClockBodyPair(((getWidth() - BODY_SIZE) / 2.0)+OFFSET_B,(getHeight() / 2.0)-OFFSET_A),
                new ClockBodyPair((getWidth() / 2.0)-OFFSET_A,((getHeight() - BODY_SIZE) / 2.0)+OFFSET_B),
        };
        //Copy the hour markers to the public list for the clock mechanism
        for(int i=0;i<BODY_COORDS.length;i++) {
            BODY_COORDS[i]=internalCoords[i];
        }



        g2.setStroke(new BasicStroke(5));
        Shape body = new Ellipse2D.Double((getWidth() - BODY_SIZE) / 2,(getHeight() - BODY_SIZE) / 2 ,BODY_SIZE,BODY_SIZE); //Place the body in the X center of the frame
        g2.draw(body);

        //Center Point Drawing
        g2.fillOval(getWidth()/2 - 5, getHeight()/2 - 5,10,10);

        //Hour lines for the body
        Shape[] lines = new Shape[12];

        lines[0] = new Line2D.Double(internalCoords[0].getX(),internalCoords[0].getY(),getWidth() / 2,((getHeight() - BODY_SIZE) / 2.0) + 20);
        lines[1] = new Line2D.Double(internalCoords[1].getX(),internalCoords[1].getY(),internalCoords[1].getX()-OFFSET_A_END,internalCoords[1].getY()+OFFSET_B_END);
        lines[11] = new Line2D.Double(internalCoords[11].getX(),internalCoords[11].getY(),internalCoords[11].getX()+OFFSET_A_END,internalCoords[11].getY()+OFFSET_B_END);

        lines[3] = new Line2D.Double(internalCoords[3].getX(),internalCoords[3].getY(),((getWidth() + BODY_SIZE) / 2.0) - 20, getHeight() / 2);
        lines[2] = new Line2D.Double(internalCoords[2].getX(),internalCoords[2].getY(),internalCoords[2].getX()-OFFSET_B_END,internalCoords[2].getY()+OFFSET_A_END);
        lines[4] = new Line2D.Double(internalCoords[4].getX(),internalCoords[4].getY(),internalCoords[4].getX()-OFFSET_B_END,internalCoords[4].getY()-OFFSET_A_END);

        lines[6] = new Line2D.Double(internalCoords[6].getX(),internalCoords[6].getY(),getWidth() / 2,((getHeight() + BODY_SIZE) / 2.0) - 20);
        lines[5] = new Line2D.Double(internalCoords[5].getX(),internalCoords[5].getY(),internalCoords[5].getX()-OFFSET_A_END,internalCoords[5].getY()-OFFSET_B_END);
        lines[7] = new Line2D.Double(internalCoords[7].getX(),internalCoords[7].getY(),internalCoords[7].getX()+OFFSET_A_END,internalCoords[7].getY()-OFFSET_B_END);

        lines[9] = new Line2D.Double(internalCoords[9].getX(),internalCoords[9].getY(),((getWidth() - BODY_SIZE) / 2.0) + 20, getHeight() / 2);
        lines[8] = new Line2D.Double(internalCoords[8].getX(),internalCoords[8].getY(),internalCoords[8].getX()+OFFSET_B_END,internalCoords[8].getY()-OFFSET_A_END);
        lines[10] = new Line2D.Double(internalCoords[10].getX(),internalCoords[10].getY(),internalCoords[10].getX()+OFFSET_B_END,internalCoords[10].getY()+OFFSET_A_END);

        g2.setStroke(new BasicStroke(3));
        for(Shape s : lines) {
            g2.draw(s);
        }


        //not sure why it works, but has something to do with how paintComponent is called
        timeString.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date())); //updates the timeString
        dateString.setText(new SimpleDateFormat("EEE, MMM d").format(new Date())); //updates the dateString
    }
}
