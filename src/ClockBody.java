import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockBody extends JPanel {

    private JLabel timeString;
    private JLabel dateString;

    public ClockBody() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        timeString = new JLabel(new SimpleDateFormat("HH:mm:ss a").format(new Date()));
        timeString.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateString = new JLabel(new SimpleDateFormat("EEE, d MMM").format(new Date()));
        dateString.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0,35)));
        add(timeString);
        add(dateString);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;

        Shape body = new Ellipse2D.Double(100,100,300,300);
        g2.draw(body);


        //not sure why it works, but has something to do with how paintComponent is called
        timeString.setText(new SimpleDateFormat("HH:mm:ss a").format(new Date())); //updates the timeString
        dateString.setText(new SimpleDateFormat("EEE, d MMM").format(new Date())); //updates the dateString
    }
}
