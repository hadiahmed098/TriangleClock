import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TriangleClock extends JFrame {
    public static void main(String[] args) {
        new TriangleClock("Triangle Clock");
    }

    public TriangleClock(String title) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);

        ClockBody clockBody = new ClockBody();
        add(clockBody);

        setSize(500,500);
        setVisible(true);

        while(true) {   //Repaints the frame constantly to force update the timestamp on the frame. Calling
            repaint();  //JLabel's setText by itself was for some reason creating a new JLabel on top of the old one.
        }               //This way lets the Swing Manager handle the repainting and labeling of JLabel.
    }
}
