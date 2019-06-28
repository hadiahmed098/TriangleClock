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

    public ClockBody() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        timeString = new JLabel(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        timeString.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeString.setFont(new Font(timeString.getFont().getName(), timeString.getFont().getStyle(),45));

        //dateString = new JLabel(new SimpleDateFormat("EEE, d MMM").format(new Date()));
        //dateString.setAlignmentX(Component.CENTER_ALIGNMENT);
        //dateString.setFont(new Font(dateString.getFont().getName(), dateString.getFont().getStyle(),15));

        add(Box.createRigidArea(new Dimension(10,10)));
        add(timeString);
        //add(dateString);
        //TODO fix the labeling
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;

        g2.setStroke(new BasicStroke(5));
        Shape body = new Ellipse2D.Double((getWidth() - BODY_SIZE) / 2,(getHeight() - BODY_SIZE) / 2 ,BODY_SIZE,BODY_SIZE); //Place the body in the X center of the frame
        g2.draw(body);

        //Center Point Drawing
        g2.fillOval(getWidth()/2 - 5, getHeight()/2 - 5,10,10);

        //Hour lines for the body
        Shape[] lines = new Shape[12];

        lines[0] = new Line2D.Double(getWidth() / 2,((getHeight() - BODY_SIZE) / 2.0),getWidth() / 2,((getHeight() - BODY_SIZE) / 2.0) + 20);
        lines[1] = new Line2D.Double((getWidth() / 2.0)+OFFSET_A,((getHeight() - BODY_SIZE) / 2.0)+OFFSET_B,getWidth()/2,getHeight()/2);
        lines[11] = new Line2D.Double((getWidth() / 2.0)-OFFSET_A,((getHeight() - BODY_SIZE) / 2.0)+OFFSET_B,getWidth()/2,getHeight()/2);

        lines[3] = new Line2D.Double(((getWidth() + BODY_SIZE) / 2.0), getHeight() / 2,((getWidth() + BODY_SIZE) / 2.0) - 20, getHeight() / 2);
        lines[2] = new Line2D.Double(((getWidth() + BODY_SIZE) / 2.0)-OFFSET_B,(getHeight() / 2.0)-OFFSET_A,getWidth()/2,getHeight()/2);
        lines[4] = new Line2D.Double(((getWidth() + BODY_SIZE) / 2.0)-OFFSET_B,(getHeight() / 2.0)+OFFSET_A,getWidth()/2,getHeight()/2);

        lines[6] = new Line2D.Double(getWidth() / 2,((getHeight() + BODY_SIZE) / 2.0),getWidth() / 2,((getHeight() + BODY_SIZE) / 2.0) - 20);
        lines[5] = new Line2D.Double((getWidth() / 2.0)+OFFSET_A,((getHeight() + BODY_SIZE) / 2.0)-OFFSET_B,getWidth()/2,getHeight()/2);
        lines[7] = new Line2D.Double((getWidth() / 2.0)-OFFSET_A,((getHeight() + BODY_SIZE) / 2.0)-OFFSET_B,getWidth()/2,getHeight()/2);

        lines[9] = new Line2D.Double(((getWidth() - BODY_SIZE) / 2.0), getHeight() / 2,((getWidth() - BODY_SIZE) / 2.0) + 20, getHeight() / 2);
        lines[8] = new Line2D.Double(((getWidth() - BODY_SIZE) / 2.0)+OFFSET_B,(getHeight() / 2.0)+OFFSET_A,getWidth()/2,getHeight()/2);
        lines[10] = new Line2D.Double(((getWidth() - BODY_SIZE) / 2.0)+OFFSET_B,(getHeight() / 2.0)-OFFSET_A,getWidth()/2,getHeight()/2);

        g2.setStroke(new BasicStroke(3));
        for(Shape s : lines) {
            g2.draw(s);
        }


        //not sure why it works, but has something to do with how paintComponent is called
        timeString.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date())); //updates the timeString
        //dateString.setText(new SimpleDateFormat("EEE, d MMM").format(new Date())); //updates the dateString
    }
}
